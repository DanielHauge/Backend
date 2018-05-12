package DataAcessors;

import DataObjects.*;
import Interfaces.DataAccessor;
import org.neo4j.driver.v1.*;

import java.util.ArrayList;

public class Neo4jDataAcessor implements DataAccessor {

    private final Driver driver;

    public Neo4jDataAcessor(Driver driver){
        this.driver = driver;
    }


    @Override
    public AllCities GetAllCities() {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (x:city) return x.CityId as id, x.name as name;");
        ArrayList<City> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new City(rec.get("id").asInt(),rec.get("name").asString()));
        }
        session.close();
        return new AllCities(res.toArray(new City[0]));
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (b:book)-[m:MENTIONS]->(:city{CityId:"+cityid+"}) return b.title as title, b.author as author, m.amount as amount;");
        ArrayList<BookWithMentions> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new BookWithMentions(rec.get("title").asString(), rec.get("author").asString(), rec.get("amount").asInt()));
        }
        session.close();
        return new BooksByCity(res.toArray(new BookWithMentions[0]));
    }

    @Override
    public AllBooks GetAllBooks() {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (b:book) return b.BookId as id, b.title as title;");
        ArrayList<Book> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new Book(rec.get("id").asInt(), rec.get("title").asString()));
        }
        session.close();
        return new AllBooks(res.toArray(new Book[0]));
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (:book{BookId:"+bookid+"})-[:MENTIONS]-(c:city) return c.name as name, c.latitude as lat, c.longitude as lon;");
        ArrayList<CityWithCords> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new CityWithCords(rec.get("name").asString(), rec.get("lat").asDouble(), rec.get("lon").asDouble()));
        }
        session.close();
        return new ManyCitiesWithCords(res.toArray(new CityWithCords[0]));
    }

    @Override
    public AllAuthors GetAllAuthors() {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (a:book) return DISTINCT a.author as author;");
        ArrayList<String> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(rec.get("author").asString());
        }
        session.close();
        return new AllAuthors(res.toArray(new String[0]));
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (a:book{author:\"William Shakespeare\"}) return a.title as title, a.BookId as id;");
        ArrayList<Book> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new Book(rec.get("id").asInt(),rec.get("title").asString()));
        }
        session.close();
        return new BooksByAuthor(author,res.toArray(new Book[0]));
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (:book{BookId:"+bookid+"})-[:MENTIONS]-(c:city) return c.name as name, c.latitude as lat, c.longitude as lon;");
        ArrayList<CityWithCords> res = new ArrayList<>();

        while (result.hasNext()){
            Record rec = result.next();
            res.add(new CityWithCords(rec.get("name").asString(), rec.get("lat").asDouble(), rec.get("lon").asDouble()));
        }
        result = session.run("MATCH (a:book{BookId:"+bookid+"}) return a.title as title;");
        String title = "Could not find title";
        if (result.hasNext()){
            Record rec = result.next();
            title = rec.get("title").asString();
        }
        session.close();
        return new CityByBook(bookid, title, res.toArray(new CityWithCords[0]));
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        Session session = driver.session();
        StatementResult result = session.run("MATCH (c:city) WITH c, distance(   point({latitude:"+lat+", longitude:"+lon+"}), point({latitude:c.latitude, longitude:c.longitude})  ) as dist WHERE dist<="+km+"000  RETURN c.name as name, c.latitude as lat, c.longitude as lon;");
        ArrayList<CityAndBooks> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            String city = rec.get("name").asString();
            ArrayList<Book> books = new ArrayList<>();
            Session session2 = driver.session();
            StatementResult result2 = session2.run("MATCH (b:book)-[:MENTIONS]-(:city{name:\""+city+"\"}) return b.BookId as id, b.title as title;");
            while (result2.hasNext()){
                Record rec2 = result2.next();
                books.add(new Book(rec2.get("id").asInt(), rec2.get("title").asString()));
            }
            res.add(new CityAndBooks(city, rec.get("lat").asDouble(), rec.get("lon").asDouble(), books.toArray(new Book[0])));
            session2.close();
        }
        session.close();
        return new BooksByVicenety(res.toArray(new CityAndBooks[0]));
    }

    @Override
    public void close() {
        driver.close();
    }
}
