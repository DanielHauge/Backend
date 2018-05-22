package DataAcessors;

import Benchmarker.BenchmarkLog;
import Benchmarker.BenchmarkTimer;
import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataObjects.*;
import java.sql.*;
import java.util.ArrayList;

import Interfaces.DataAccessor;
import Main.Main;

public class PostgresDataAcessor implements DataAccessor {

    Connection conn;

    public PostgresDataAcessor(String address, String username, String password) {

        //String jdbcUrl = "jdbc:postgresql://192.168.33.11:5432/postgres";

        try {
            this.conn = DriverManager.getConnection(address, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AllCities GetAllCities() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allcities, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        AllCities allCities = null;
        try {
            ArrayList<City> cityArrayList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("Select id, name from cities;");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                cityArrayList.add(new City(result.getInt("id"),result.getString("name")));
            }
            allCities = new AllCities(cityArrayList.toArray(new City[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        timer.stop("Transforming");
        Main.Logger.Savelog(log);

        return allCities;
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.booksbycity, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        BooksByCity booksByCity = null;

        try {
            ArrayList<BookWithMentions> bookWithMentions = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("select title, author, amount from books join mentions on (books.id = mentions.bookid) where mentions.cityid = " + cityid + ";");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                bookWithMentions.add(new BookWithMentions(result.getString("title"),result.getString("author"),result.getInt("amount")));
            }
            booksByCity = new BooksByCity(bookWithMentions.toArray(new BookWithMentions[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return booksByCity;
    }

    @Override
    public AllBooks GetAllBooks() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allbooks, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        AllBooks allBooks = null;
        try {
            ArrayList<Book> bookArrayList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("Select id, title from books;");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                bookArrayList.add(new Book(result.getInt("id"),result.getString("title")));
            }
            allBooks = new AllBooks(bookArrayList.toArray(new Book[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return allBooks;
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.citiesbybook, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        ManyCitiesWithCords manyCitiesWithCords = null;
        try {
            ArrayList<CityWithCords> cityWithCords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("select cities.name, cities.latitude, cities.longitude from cities\n"+
                    "    join mentions on (cities.id = mentions.cityid)\n"+
                    "    join books on (mentions.bookid = books.id)\n"+
                    "    where books.id = " + bookid + ";");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                cityWithCords.add(new CityWithCords(result.getString("name"),result.getDouble("latitude"),result.getDouble("longitude")));
            }
            manyCitiesWithCords = new ManyCitiesWithCords(cityWithCords.toArray(new CityWithCords[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return manyCitiesWithCords;
    }

    @Override
    public AllAuthors GetAllAuthors() {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.allauthors, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        AllAuthors allAuthors = null;
        try {
            ArrayList<Author> listofAuthors = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("Select distinct author from books;");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                listofAuthors.add(new Author(result.getString("author")));
            }
            allAuthors = new AllAuthors(listofAuthors.toArray(new Author[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return allAuthors;
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.bookbyauthor, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        BooksByAuthor booksByAuthor = null;
        try {
            ArrayList<Book> bookArrayList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("select * from books where author like '%" + author + "%';");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                bookArrayList.add(new Book(result.getInt("id"), result.getString("title")));
            }
            booksByAuthor = new BooksByAuthor(author, bookArrayList.toArray(new Book[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.start("Transforming");
        Main.Logger.Savelog(log);
        return booksByAuthor;
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.citybybook, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");

        CityByBook cityByBook = null;
        String bookTitle = null;
        try {

            ArrayList<CityWithCords> cityWithCords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            ResultSet result = stmt.executeQuery("select books.id, books.title, cities.name, cities.latitude, cities.longitude from books\n"+
                    "    join mentions on (books.id = mentions.bookid)\n"+
                    "    join cities on (mentions.cityid = cities.id)\n"+
                    "    where books.id = " + bookid + ";");
            timer.stop("Query");
            timer.start("Transforming");
            while (result.next())
            {
                bookTitle = result.getString("title");
                cityWithCords.add(new CityWithCords(result.getString("name"), result.getDouble("latitude"), result.getDouble("longitude")));
            }

            cityByBook = new CityByBook(bookid,bookTitle, cityWithCords.toArray(new CityWithCords[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return cityByBook;
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        BenchmarkLog log = Main.Logger.CreateNewLog(Query.vicenety1, DBMS.postgres);
        BenchmarkTimer timer = log.GetTimer();
        timer.start("Initialize");
        BooksByVicenety booksByVicenety = null;
        try {
            ArrayList<CityAndBooks> cityAndBooks = new ArrayList<>();

            Statement stmt = conn.createStatement();
            timer.stop("Initialize");
            timer.start("Query");
            /*
            select cities.name, cities.latitude, cities.longitude, books.id, books.title from cities
            join mentions on (cities.id = mentions.cityid)
            join books on (mentions.bookid = books.id)
            where earth_box(ll_to_earth(52.38, 11.47), 50000) @> ll_to_earth(latitude, longitude)
            and earth_distance(ll_to_earth(52.38, 11.47), ll_to_earth(latitude, longitude)) < 50000;
             */

            ResultSet result = stmt.executeQuery("select cities.name, cities.latitude, cities.longitude, books.id, books.title from cities\n" +
                    "    join mentions on (cities.id = mentions.cityid)\n" +
                    "    join books on (mentions.bookid = books.id)\n" +
                    "    where earth_box(ll_to_earth(52.38, 11.47), 50000) @> ll_to_earth(latitude, longitude)\n" +
                    "    and earth_distance(ll_to_earth(" + lat + ", " + lon + "), ll_to_earth(latitude, longitude)) < " + km + "000;");

            timer.stop("Query");
            timer.start("Transforming");
            boolean doesExist = false;
            while (result.next())
            {
                CityWithCords tc = new CityWithCords(result.getString("name"), result.getDouble("latitude"), result.getDouble("longitude"));
                Book bc = new Book(result.getInt("id"),result.getString("title"));
                doesExist = false;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Transforming");
        Main.Logger.Savelog(log);
        return booksByVicenety;
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
