package DataAcessors;

import Benchmarker.BenchmarkLog;
import Benchmarker.BenchmarkTimer;
import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataObjects.*;
import Interfaces.DataAccessor;
import Main.Main;
import org.neo4j.driver.v1.*;

import java.util.ArrayList;

public class Neo4jDataAcessor implements DataAccessor {

    private final Driver driver;

    public Neo4jDataAcessor(Driver driver){
        this.driver = driver;
    }


    @Override
    public AllCities GetAllCities() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allcities, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");
        Session session = driver.session();
        StatementResult result = session.run("MATCH (x:city) return x.CityId as id, x.name as name;");
        timer.stop("Query");
        timer.start("Tranforming");
        ArrayList<City> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new City(rec.get("id").asInt(),rec.get("name").asString()));
        }
        session.close();
        timer.stop("Tranforming");
        Main.Logger.Savelog(log);
        return new AllCities(res.toArray(new City[0]));
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.booksbycity, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (b:book)-[m:MENTIONS]->(:city{CityId:"+cityid+"}) return b.title as title, b.author as author, m.amount as amount;");
        timer.stop("Query");
        timer.start("Tranforming");
        ArrayList<BookWithMentions> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new BookWithMentions(rec.get("title").asString(), rec.get("author").asString(), rec.get("amount").asInt()));
        }
        session.close();
        timer.stop("Tranforming");
        Main.Logger.Savelog(log);
        return new BooksByCity(res.toArray(new BookWithMentions[0]));
    }

    @Override
    public AllBooks GetAllBooks() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allbooks, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (b:book) return b.BookId as id, b.title as title;");
        timer.stop("Query");
        timer.start("Tranforming");
        ArrayList<Book> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new Book(rec.get("id").asInt(), rec.get("title").asString()));
        }
        session.close();
        timer.stop("Tranforming");
        Main.Logger.Savelog(log);
        return new AllBooks(res.toArray(new Book[0]));
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.citiesbybook, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (:book{BookId:"+bookid+"})-[:MENTIONS]-(c:city) return c.name as name, c.location.latitude as lat, c.location.longitude as lon;");
        timer.stop("Query");
        timer.start("Tranforming");
        ArrayList<CityWithCords> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new CityWithCords(rec.get("name").asString(), rec.get("lat").asDouble(), rec.get("lon").asDouble()));
        }
        session.close();
        timer.stop("Tranforming");
        Main.Logger.Savelog(log);
        return new ManyCitiesWithCords(res.toArray(new CityWithCords[0]));
    }

    @Override
    public AllAuthors GetAllAuthors() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allauthors, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (a:book) return DISTINCT a.author as author;");
        timer.stop("Query");
        timer.start("Tranforming");
        ArrayList<Author> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new Author(rec.get("author").asString()));
        }
        session.close();
        timer.stop("Tranforming");
        Main.Logger.Savelog(log);
        return new AllAuthors(res.toArray(new Author[0]));
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.bookbyauthor, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (a:book) where a.author CONTAINS '" + author + "' return a.title as title, a.BookId as id");
        timer.stop("Query");
        timer.start("Tranforming");
        ArrayList<Book> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new Book(rec.get("id").asInt(),rec.get("title").asString()));
        }
        session.close();
        timer.stop("Tranforming");
        Main.Logger.Savelog(log);
        return new BooksByAuthor(author,res.toArray(new Book[0]));
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.citybybook, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("PreQuery");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (:book{BookId:"+bookid+"})-[:MENTIONS]-(c:city) return c.name as name, c.location.latitude as lat, c.location.longitude as lon;");
        timer.stop("PreQuery");
        timer.start("Transform-Query");
        ArrayList<CityWithCords> res = new ArrayList<>();
        while (result.hasNext()){
            Record rec = result.next();
            res.add(new CityWithCords(rec.get("name").asString(), rec.get("lat").asDouble(), rec.get("lon").asDouble()));
        }
        timer.stop("Transform-Query");
        timer.start("Transform-EndQuery");
        result = session.run("MATCH (a:book{BookId:"+bookid+"}) return a.title as title;");
        String title = "Could not find title";
        if (result.hasNext()){
            Record rec = result.next();
            title = rec.get("title").asString();
        }
        session.close();
        timer.stop("Transform-EndQuery");
        Main.Logger.Savelog(log);
        return new CityByBook(bookid, title, res.toArray(new CityWithCords[0]));
    }

    /*
    MATCH (c:city)-[:MENTIONS]-(b:book)
WITH c, b, distance(point({latitude:52.38, longitude:11.47}), point({latitude:c.latitude, longitude:c.longitude}))
as dist WHERE dist <= 50000
RETURN c.name as name, c.latitude as lat, c.longitude as lon, b.BookId as id, b.title as title;
     */

    /*
    MATCH(c:city)-[:MENTIONS]-(b:book)
where distance(point({latitude:52.38, longitude:11.47}), c.location) <= 50000
RETURN c.name as name, c.latitude as lat, c.longitude as lon, b.BookId as id, b.title as title;
     */
/*
StatementResult result = session.run("MATCH(c:city)-[:MENTIONS]-(b:book)\n" +
                "where distance(point({latitude:" + lat + ", longitude:" + lon + "}), point({latitude:c.latitude, longitude:c.longitude})) <= " + km + "000\n" +
                "RETURN c.name as name, c.latitude as lat, c.longitude as lon, b.BookId as id, b.title as title;");
 */

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.vicenety1, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        BooksByVicenety booksByVicenety = null;
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH(c:city)-[:MENTIONS]-(b:book)\n" +
                "where distance(point({latitude:" + lat + ", longitude:" + lon + "}), c.location) <= " + km + "000\n" +
                "RETURN c.name as name, c.location.latitude as lat, c.location.longitude as lon, b.BookId as id, b.title as title;");
        timer.stop("Query");
        timer.start("Transform-query");
        ArrayList<CityAndBooks> cityAndBooks = new ArrayList<>();
        boolean doesExist = false;
        while (result.hasNext())
        {
            Record record = result.next();
            //CityAndBooks tempCity = new CityAndBooks(record.get("name").asString(), record.get("lat").asDouble(), record.get("lon").asDouble(), new Book[] {new Book(record.get("id").asInt(),record.get("title").asString())});
            CityWithCords tc = new CityWithCords(record.get("name").asString(), record.get("lat").asDouble(), record.get("lon").asDouble());
            Book bc = new Book(record.get("id").asInt(),record.get("title").asString());
            doesExist = false;
            /*
            if (cityAndBooks.size() == 0)
            {
                cityAndBooks.add(new CityAndBooks(tempCity.cityName, tempCity.latitude, tempCity.longitude , tempCity.books));
            }
            if (!cityAndBooks.contains(tempCity))
            {
                Book[] tb = new Book[cityAndBooks.indexOf(tempCity.books.length + 1) + 1];
                for (int i = 0; i < tempCity.books.length; i++) {
                    tb[i] = cityAndBooks.get(cityAndBooks.indexOf(tempCity.books)).books[i];
                }
                tb[tb.length-1] = tempCity.books[0];
                cityAndBooks.get(cityAndBooks.indexOf(tempCity.books.length)).books = tb;
                doesExist = true;
            }
            if (!doesExist)
            {
                cityAndBooks.add(new CityAndBooks(tempCity.cityName, tempCity.latitude, tempCity.longitude , tempCity.books));
            }
            */

            for (CityAndBooks CaB: cityAndBooks
                    ) {
                if (CaB.cityName.equals(tc.cityName))
                {
                    Book[] tb = new Book[CaB.books.length+1];
                    for (int j = 0; j < CaB.books.length; j++) {
                        tb[j] = CaB.books[j];
                    }
                    tb[tb.length-1] = bc;
                    CaB.books = tb;
                    doesExist = true;
                }
            }
            if (!doesExist)
            {
                cityAndBooks.add(new CityAndBooks(tc.cityName, tc.lat, tc.lng,new Book[0]));
            }

        }
        booksByVicenety = new BooksByVicenety(cityAndBooks.toArray(new CityAndBooks[0]));
        session.close();
        timer.stop("Transform-query");
        Main.Logger.Savelog(log);
        return booksByVicenety;
                /*
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.vicenety1, DBMS.neo4j);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Query");

        Session session = driver.session();
        StatementResult result = session.run("MATCH (c:city) WITH c, distance(   point({latitude:"+lat+", longitude:"+lon+"}), point({latitude:c.latitude, longitude:c.longitude})  ) as dist WHERE dist<="+km+"000  RETURN c.name as name, c.latitude as lat, c.longitude as lon;");
        timer.stop("Query");
        timer.start("Transform-query");
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
        timer.stop("Transform-query");
        Main.Logger.Savelog(log);
        return new BooksByVicenety(res.toArray(new CityAndBooks[0]));
         */
    }

    @Override
    public void close() {
        driver.close();
    }
}
