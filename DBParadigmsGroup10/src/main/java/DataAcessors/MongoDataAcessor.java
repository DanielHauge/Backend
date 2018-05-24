package DataAcessors;

import DataObjects.*;
import Interfaces.DataAccessor;

import com.mongodb.MongoClient;

import com.mongodb.client.*;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.*;


public class MongoDataAcessor implements DataAccessor {

    private final MongoDatabase db;

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
                res.add(new BookWithMentions(String.valueOf(Inner.get(0).get("Title")), String.valueOf(Inner.get(0).get("Author")), doc.getInteger("Amount")));
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
                Document city = ((ArrayList<Document>) doc.get("city")).get(0);
                ArrayList<Double> coords = (ArrayList<Double>) ((Document) city.get("location")).get("coordinates");
                res.add(new CityWithCords(String.valueOf(city.get("Name")), coords.get(0), coords.get(1)));

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
        authors.forEach(s -> allauthors.add(new Author(s)));

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
                Document city = ((ArrayList<Document>) doc.get("city")).get(0);
                ArrayList<Double> coords = (ArrayList<Double>) ((Document) city.get("location")).get("coordinates");
                cits.add(new CityWithCords(city.getString("Name"), coords.get(0), coords.get(1)));
            }} finally {cursor.close();
        }

        MongoCollection<Document> book = db.getCollection("books");
        Document doc = book.find(eq("Bookid", bookid)).first();

        return new CityByBook(bookid, doc.getString("Title"), cits.toArray(new CityWithCords[0]));
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        ArrayList<CityAndBooks> res = new ArrayList<>();



        Point refPoint = new Point(new Position(11.47, 52.38));

        MongoCollection<Document> cities = db.getCollection("cities");
        MongoCursor<Document> cursor = cities.aggregate(
                Arrays.asList(

                        Aggregates.match(Filters.nearSphere("location", refPoint, 20000.0, 0.0)),
                        Aggregates.lookup("mentions", "Cityid", "Cityid", "Ments"),
                        Aggregates.lookup("books", "Ments.Bookid", "Bookid", "Books"),
                        Aggregates.project(Projections.fields(
                                Projections.excludeId(),
                                Projections.include("Name", "location", "Books")
                        ))
                )
        ).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                ArrayList<Book> allbooks = new ArrayList<>();
                ArrayList<Double> coords = (ArrayList<Double>) ((Document)doc.get("location")).get("coordinates");
                ArrayList<Document> books = (ArrayList<Document>) doc.get("Books");
                books.forEach(b -> allbooks.add(new Book(b.getInteger("Bookid"), b.getString("Title"))));
                res.add(new CityAndBooks(doc.getString("Name"), coords.get(0), coords.get(1), allbooks.toArray(new Book[0])));
            }} finally {cursor.close();
        }



        /*
        db.cities.aggregate([
    {
        $geoNear: {
            near: { type: "Point", coordinates: [11.47, 52.38] },
            distanceField: "dist.calculated",
            maxDistance: 20000,
            includeLocs: "location",
            spherical: true
        }
    },
    {
        $lookup:
        {
            from: "mentions",
            localField: "Cityid",
            foreignField: "Cityid",
            as: "Ments"

        }
    },
    {
        $lookup:
        {
            from: "books",
            localField: "Ments.Bookid",
            foreignField: "Bookid",
            as: "Books"
        }
    },
    {
        $project: {

            Name: 1,
            location: 1,
            Books: 1
            }
    }

])

         */






        return new BooksByVicenety(null);
    }

    @Override
    public void close() {

    }
}
