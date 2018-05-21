package DataAcessors;

import Benchmarker.BenchmarkLog;
import DataObjects.*;
import Interfaces.DataAccessor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.*;

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;


public class MongoDataAcessor implements DataAccessor {

    private MongoDatabase db;

    public MongoDataAcessor(String hostname){
        //Use hostname instead in the end.
        MongoClient client = new MongoClient(hostname);
        db = client.getDatabase("mydb");

    }

    @Override
    public AllCities GetAllCities() {



        MongoCollection<Document> col = db.getCollection("cities");
        MongoCursor<Document> cursor = col.find().iterator();
        ArrayList<City> allcities = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                allcities.add(new City(doc.getInteger("Cityid"), String.valueOf(doc.get("Name"))));
            }} finally {cursor.close();
        }


        return new AllCities(allcities.toArray(new City[0]));

    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {


        ArrayList<BookWithMentions> res = new ArrayList<>();
        MongoCollection<Document> mentions = db.getCollection("mentions");

        MongoCursor<Document> cursor = mentions.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("Cityid", cityid)),
                        Aggregates.lookup("books", "Bookid", "Bookid", "book")
                )
        ).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                ArrayList<Document> Inner = (ArrayList<Document>) doc.get("book");
                res.add(new BookWithMentions(Inner.get(0).getString("Title"), Inner.get(0).getString("Author"), doc.getInteger("Amount")));
            }} finally {cursor.close();
        }



        return new BooksByCity(res.toArray(new BookWithMentions[0]));
    }

    @Override
    public AllBooks GetAllBooks() {

        MongoCollection<Document> books = db.getCollection("books");
        MongoCursor<Document> cursor = books.find().iterator();
        ArrayList<Book> allbooks = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                allbooks.add(new Book(doc.getInteger("Bookid"), String.valueOf(doc.get("Title"))));
            }} finally {cursor.close();
        }

        return new AllBooks(allbooks.toArray(new Book[0]));
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        ArrayList<CityWithCords> res = new ArrayList<>();

        MongoCollection<Document> mentions = db.getCollection("mentions");

        MongoCursor<Document> cursor = mentions.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("Bookid", bookid)),
                        Aggregates.lookup("cities", "Cityid", "Cityid", "city")
                )
        ).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                ArrayList<Document> Inner = (ArrayList<Document>) doc.get("city");
                res.add(new CityWithCords(Inner.get(0).getString("Name"), Inner.get(0).getDouble("Latitude"), Inner.get(0).getDouble("Longitude")));
            }} finally {cursor.close();
        }


        return new ManyCitiesWithCords(res.toArray(new CityWithCords[0]));

    }

    @Override
    public AllAuthors GetAllAuthors() {

        MongoCollection<Document> books = db.getCollection("books");
        MongoCursor<Document> cursor = books.find().iterator();
        HashSet<String> authors = new HashSet<>();
        ArrayList<Author> allauthors = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                authors.add(doc.getString("Author"));
            }} finally {cursor.close();
        }
        authors.forEach(s -> {
            allauthors.add(new Author(s));
        });

        return new AllAuthors(allauthors.toArray(new Author[0]));
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        MongoCollection<Document> books = db.getCollection("books");
        MongoCursor<Document> cursor = books.find(eq("Author", author)).iterator();
        ArrayList<Book> allbooks = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                allbooks.add(new Book(doc.getInteger("Bookid"), doc.getString("Author")));
            }} finally {cursor.close();
        }

        return new BooksByAuthor(author, allbooks.toArray(new Book[0]));
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {

        ArrayList<CityWithCords> cits = new ArrayList<>();
        MongoCollection<Document> mentions = db.getCollection("mentions");
        MongoCursor<Document> cursor = mentions.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("Bookid", bookid)),
                        Aggregates.lookup("cities", "Cityid", "Cityid", "city")
                )
        ).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                ArrayList<Document> Inner = (ArrayList<Document>) doc.get("city");
                cits.add(new CityWithCords(Inner.get(0).getString("Name"), Inner.get(0).getDouble("Latitude"), Inner.get(0).getDouble("Longitude")));
            }} finally {cursor.close();
        }

        MongoCollection<Document> book = db.getCollection("books");
        Document doc = book.find(eq("Bookid", bookid)).first();

        return new CityByBook(bookid, doc.getString("Title"), cits.toArray(new CityWithCords[0]));
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        return null;
    }

    @Override
    public void close() {

    }
}
