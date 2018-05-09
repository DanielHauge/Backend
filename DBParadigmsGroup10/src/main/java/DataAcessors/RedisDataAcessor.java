package DataAcessors;

import DataObjects.*;
import Interfaces.DataAccessor;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisDataAcessor implements DataAccessor {

    Jedis jedis;

    public RedisDataAcessor(){
        this.jedis = new Jedis("192.168.33.11");
        System.out.println(jedis.ping());
    }

    @Override
    public AllCities GetAllCities() {
        Set<String> qres = jedis.smembers("allcities");
        ArrayList<City> cities = new ArrayList<>();
        for (String city : qres) {
            String[] split = city.split("_");
            int id = Integer.parseInt(split[0]);
            String cityname = "";
            try{
                cityname = split[1];
            }catch (Exception e){System.out.println("Found exception");}

            cities.add(new City(id, cityname));
        }
        City[] allcits = cities.toArray(new City[0]);

        return new AllCities(allcits);
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        Set<String> qres = jedis.smembers("M_city-book:"+cityid);
        ArrayList<BookWithMentions> res = new ArrayList<>();
        for (String book : qres) {
            String[] split = book.split("-");
            String title = jedis.get("book_title:"+split[0]);
            String author = jedis.get("book_author:"+split[0]);
            res.add(new BookWithMentions(title,author, Integer.parseInt(split[1])));
        }

        return new BooksByCity(res.toArray(new BookWithMentions[0]));
    }

    @Override
    public AllBooks GetAllBooks() {
        Set<String> qres = jedis.smembers("allbooks");
        ArrayList<Book> res = new ArrayList<>();
        for (String book : qres) {
            String[] split = book.split("_");
            try{
                res.add(new Book(Integer.parseInt(split[0]), split[1]));
            }catch (Exception e){System.out.println(book + " Was the problem");}

        }
        return new AllBooks(res.toArray(new Book[0]));
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        Set<String> qres = jedis.smembers("M_book-city:"+bookid);
        ArrayList<CityWithCords> res = new ArrayList<>();
        for (String city : qres) {
            int cityid = Integer.parseInt(city.split("-")[0]);
            String cityname = jedis.get("city_name:"+cityid);

            List<GeoCoordinate> place = jedis.geopos("geospartial", String.valueOf(cityid));

            res.add(new CityWithCords(cityname,place.get(0).getLatitude(), place.get(0).getLongitude()));
        }

        return new ManyCitiesWithCords(res.toArray(new CityWithCords[0]));
    }

    @Override
    public AllAuthors GetAllAuthors() {
        Set<String> qres = jedis.smembers("allauthors");
        return new AllAuthors(qres.toArray(new String[0]));
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        Set<String> qres = jedis.smembers("author-book:"+author+"");
        ArrayList<Book> res = new ArrayList<>();
        for (String bookid : qres) {
            int id = Integer.parseInt(bookid);
            res.add(new Book(id, jedis.get("book_title:"+id)));
        }
        return new BooksByAuthor(author, res.toArray(new Book[0]));
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {

        String booktitle = jedis.get("book_title:"+bookid);
        Set<String> qres = jedis.smembers("M_book-city:"+bookid);
        ArrayList<CityWithCords> res = new ArrayList<>();
        for (String returned : qres) {
            String cityid = returned.split("-")[0];
            List<GeoCoordinate> place = jedis.geopos("geospartial", cityid);
            res.add(new CityWithCords(jedis.get("city_name:"+cityid),place.get(0).getLatitude(), place.get(0).getLongitude()));
        }

        return new CityByBook(bookid, booktitle, res.toArray(new CityWithCords[0]));
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        jedis.geoadd("geospartial", lon, lat, "tempplace");
        List<GeoRadiusResponse> responses = jedis.georadiusByMember("geospartial", "tempplace", km, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withCoord());
        ArrayList<CityAndBooks> res = new ArrayList<>();
        for (GeoRadiusResponse invic : responses) {
            double latd = invic.getCoordinate().getLatitude();
            double lond = invic.getCoordinate().getLongitude();
            String cityid = invic.getMemberByString();

            if (!cityid.equals("tempplace")){
                //System.out.println(cityid);
                ArrayList<Book> b = new ArrayList<>();
                Set<String> res2 = jedis.smembers("M_city-book:"+cityid);
                for (String bookids : res2) {
                    String bid = bookids.split("-")[0];
                    b.add(new Book(Integer.parseInt(bid), jedis.get("book_title:"+bid)));
                }
                res.add(new CityAndBooks(jedis.get("city_name:"+cityid), latd, lond, b.toArray(new Book[0])));
            } else
            {
                //System.out.println("Found temmplace! Does not add.");
            }
        }


        jedis.zrem("geospartial", "tempplace");

        return new BooksByVicenety(res.toArray(new CityAndBooks[0]));
    }
}
