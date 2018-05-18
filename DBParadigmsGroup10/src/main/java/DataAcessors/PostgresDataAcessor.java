package DataAcessors;

import Benchmarker.BenchmarkLog;
import Benchmarker.BenchmarkTimer;
import DataObjects.*;
import java.sql.*;
import java.util.ArrayList;

import Interfaces.DataAccessor;

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
        AllCities allCities = null;
        try {
            ArrayList<City> cityArrayList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select id, name from cities;");
            while (result.next())
            {
                cityArrayList.add(new City(result.getInt("id"),result.getString("name")));
            }
            allCities = new AllCities(cityArrayList.toArray(new City[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCities;
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        BooksByCity booksByCity = null;

        try {
            ArrayList<BookWithMentions> bookWithMentions = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select title, author, amount from books join mentions on (books.id = mentions.bookid) where mentions.cityid = " + cityid + ";");
            while (result.next())
            {
                bookWithMentions.add(new BookWithMentions(result.getString("title"),result.getString("author"),result.getInt("amount")));
            }
            booksByCity = new BooksByCity(bookWithMentions.toArray(new BookWithMentions[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksByCity;
    }

    @Override
    public AllBooks GetAllBooks() {
        AllBooks allBooks = null;
        try {
            ArrayList<Book> bookArrayList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select id, title from books;");
            while (result.next())
            {
                bookArrayList.add(new Book(result.getInt("id"),result.getString("title")));
            }
            allBooks = new AllBooks(bookArrayList.toArray(new Book[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        ManyCitiesWithCords manyCitiesWithCords = null;
        try {
            ArrayList<CityWithCords> cityWithCords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select cities.name, cities.latitude, cities.longitude from cities\n"+
                    "    join mentions on (cities.id = mentions.cityid)\n"+
                    "    join books on (mentions.bookid = books.id)\n"+
                    "    where books.id = " + bookid + ";");
            while (result.next())
            {
                cityWithCords.add(new CityWithCords(result.getString("name"),result.getDouble("latitude"),result.getDouble("longitude")));
            }
            manyCitiesWithCords = new ManyCitiesWithCords(cityWithCords.toArray(new CityWithCords[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manyCitiesWithCords;
    }

    @Override
    public AllAuthors GetAllAuthors() {
        AllAuthors allAuthors = null;
        try {
            ArrayList<Author> listofAuthors = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select distinct author from books;");
            while (result.next())
            {
                listofAuthors.add(new Author(result.getString("author")));
            }
            allAuthors = new AllAuthors(listofAuthors.toArray(new Author[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAuthors;
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        BooksByAuthor booksByAuthor = null;
        try {
            ArrayList<Book> bookArrayList = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from books where author = '" + author + "';");
            while (result.next())
            {
                bookArrayList.add(new Book(result.getInt("id"), result.getString("title")));
            }
            booksByAuthor = new BooksByAuthor(author, bookArrayList.toArray(new Book[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksByAuthor;
    }

    @Override
    public CityByBook GetCityBybook(int bookid, BenchmarkLog log) {
        BenchmarkTimer timer = log.GetTimer();

        CityByBook cityByBook = null;
        String bookTitle = null;
        try {
            timer.start("Query");

            ArrayList<CityWithCords> cityWithCords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select books.id, books.title, cities.name, cities.latitude, cities.longitude from books\n"+
                    "    join mentions on (books.id = mentions.bookid)\n"+
                    "    join cities on (mentions.cityid = cities.id)\n"+
                    "    where books.id = " + bookid + ";");
            timer.stop("Query");
            timer.start("Read");
            while (result.next())
            {
                bookTitle = result.getString("title");
                cityWithCords.add(new CityWithCords(result.getString("name"), result.getDouble("latitude"), result.getDouble("longitude")));
            }

            cityByBook = new CityByBook(bookid,bookTitle, cityWithCords.toArray(new CityWithCords[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer.stop("Read");
        return cityByBook;
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        BooksByVicenety booksByVicenety = null;
        try {
            ArrayList<CityAndBooks> cityAndBooks = new ArrayList<>();

            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("select cities.name, cities.latitude, cities.longitude, books.id, books.title from cities\n" +
                    "    join mentions on (cities.id = mentions.cityid)\n" +
                    "    join books on (mentions.bookid = books.id)\n" +
                    "    where geodistance(latitude, longitude, " + lat + ", " + lon + ") < " + km + ";");

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
