package DataAcessors;

import Benchmarker.BenchmarkLog;
import Benchmarker.BenchmarkTimer;
import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataObjects.*;
import Interfaces.DataAccessor;
import Main.Main;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisDataAcessor implements DataAccessor {

    private final Jedis jedis;

    public RedisDataAcessor(String IP){
        this.jedis = new Jedis(IP);
        System.out.println(jedis.ping());
    }

    @Override
    public AllCities GetAllCities() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allcities, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");
        Set<String> qres = jedis.smembers("allcities");
        timer.stop("Query");
        timer.start("Transforming");
        ArrayList<City> cities = new ArrayList<>();
        for (String city : qres) {
            String[] split = city.split("_");
            int id = Integer.parseInt(split[0]);
            String cityname = "";
            try{
                cityname = split[1];
            }catch (Exception e){System.out.println("Found exception"+city);}

            cities.add(new City(id, cityname));
        }
        City[] allcits = cities.toArray(new City[0]);

        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return new AllCities(allcits);
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.booksbycity, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");
        Set<String> qres = jedis.smembers("M_city-book:"+cityid);
        timer.stop("Query");
        timer.start("Subqueries-transform");
        ArrayList<BookWithMentions> res = new ArrayList<>();
        for (String book : qres) {
            String[] split = book.split("_");
            String title = jedis.get("book_title:"+split[0]);
            String author = jedis.get("book_author:"+split[0]);
            res.add(new BookWithMentions(title,author, Integer.parseInt(split[1])));
        }
        timer.stop("Subqueries-transform");
        Main.Logger.Savelog(log);
        return new BooksByCity(res.toArray(new BookWithMentions[0]));
    }

    @Override
    public AllBooks GetAllBooks() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allbooks, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");
        Set<String> qres = jedis.smembers("allbooks");
        timer.stop("Query");
        timer.start("Transforming");
        ArrayList<Book> res = new ArrayList<>();
        for (String book : qres) {
            String[] split = book.split("_");
            try{
                res.add(new Book(Integer.parseInt(split[0]), split[1]));
            }catch (Exception e){System.out.println(book + " Was the problem");}

        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return new AllBooks(res.toArray(new Book[0]));
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.citiesbybook, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("PreQuery");
        Set<String> qres = jedis.smembers("M_book-city:"+bookid);
        timer.stop("PreQuery");
        timer.start("Query-transforming");
        ArrayList<CityWithCords> res = new ArrayList<>();
        for (String city : qres) {
            int cityid = Integer.parseInt(city.split("_")[0]);
            String cityname = jedis.get("city_name:"+cityid);

            List<GeoCoordinate> place = jedis.geopos("geospartial", String.valueOf(cityid));

            res.add(new CityWithCords(cityname,place.get(0).getLatitude(), place.get(0).getLongitude()));
        }

        timer.stop("Query-transforming");
        Main.Logger.Savelog(log);
        return new ManyCitiesWithCords(res.toArray(new CityWithCords[0]));
    }

    @Override
    public AllAuthors GetAllAuthors() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allauthors, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");
        Set<String> qres = jedis.smembers("allauthors");
        timer.stop("Query");
        timer.start("Transforming");
        ArrayList<Author> res = new ArrayList<>();
        for (String qre : qres) {
            res.add(new Author(qre));
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return new AllAuthors(res.toArray(new Author[0]));
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.bookbyauthor, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("PreQuery");
        Set<String> qres = jedis.smembers("author-book:"+author+"");
        timer.stop("PreQuery");
        timer.start("Query-transforming");
        ArrayList<Book> res = new ArrayList<>();
        for (String bookid : qres) {
            int id = Integer.parseInt(bookid);
            res.add(new Book(id, jedis.get("book_title:"+id)));
        }
        timer.stop("Query-transforming");
        Main.Logger.Savelog(log);
        return new BooksByAuthor(author, res.toArray(new Book[0]));
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.citybybook, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("PreQuery");
        String booktitle = jedis.get("book_title:"+bookid);
        timer.stop("PreQuery");
        timer.start("Query");
        Set<String> qres = jedis.smembers("M_book-city:"+bookid);
        timer.stop("Query");
        timer.start("Query-transforming");
        ArrayList<CityWithCords> res = new ArrayList<>();
        for (String returned : qres) {
            String cityid = returned.split("_")[0];
            List<GeoCoordinate> place = jedis.geopos("geospartial", cityid);
            res.add(new CityWithCords(jedis.get("city_name:"+cityid),place.get(0).getLatitude(), place.get(0).getLongitude()));
        }
        timer.stop("Query-transforming");
        Main.Logger.Savelog(log);
        return new CityByBook(bookid, booktitle, res.toArray(new CityWithCords[0]));
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.vicenety1, DBMS.redis);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("AddTempPos");

        jedis.geoadd("geospartial", lon, lat, "tempplace");
        timer.stop("AddTempPos");
        timer.start("Query");
        List<GeoRadiusResponse> responses = jedis.georadiusByMember("geospartial", "tempplace", km, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withCoord());
        timer.stop("Query");
        ArrayList<CityAndBooks> res = new ArrayList<>();
        timer.start("Query-transforming");
        for (GeoRadiusResponse invic : responses) {
            double latd = invic.getCoordinate().getLatitude();
            double lond = invic.getCoordinate().getLongitude();
            String cityid = invic.getMemberByString();

            if (!cityid.equals("tempplace")){
                //System.out.println(cityid);
                ArrayList<Book> b = new ArrayList<>();
                Set<String> res2 = jedis.smembers("M_city-book:"+cityid);
                for (String bookids : res2) {
                    String bid = bookids.split("_")[0];
                    b.add(new Book(Integer.parseInt(bid), jedis.get("book_title:"+bid)));
                }
                res.add(new CityAndBooks(jedis.get("city_name:"+cityid), latd, lond, b.toArray(new Book[0])));
            }
        }

        timer.stop("Query-transforming");
        timer.start("RemovingTempPlace");
        jedis.zrem("geospartial", "tempplace");
        timer.stop("RemovingTempPlace");
        Main.Logger.Savelog(log);

        return new BooksByVicenety(res.toArray(new CityAndBooks[0]));
    }

    @Override
    public void close() {
        jedis.close();
    }
}
