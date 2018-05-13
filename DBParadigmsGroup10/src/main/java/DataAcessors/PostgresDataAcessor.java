package DataAcessors;

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
            ArrayList<String> listofAuthors = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select distinct author from books;");
            while (result.next())
            {
                listofAuthors.add(result.getString("author"));
            }
            allAuthors = new AllAuthors(listofAuthors.toArray(new String[0]));
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
    public CityByBook GetCityBybook(int bookid) {
        CityByBook cityByBook = null;
        String bookTitle = null;
        try {
            ArrayList<CityWithCords> cityWithCords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select books.id, books.title, cities.name, cities.latitude, cities.longitude from books\n"+
                    "    join mentions on (books.id = mentions.bookid)\n"+
                    "    join cities on (mentions.cityid = cities.id)\n"+
                    "    where books.id = " + bookid + ";");
            while (result.next())
            {
                bookTitle = result.getString("title");
                cityWithCords.add(new CityWithCords(result.getString("name"), result.getDouble("latitude"), result.getDouble("longitude")));
            }
            cityByBook = new CityByBook(bookid,bookTitle, cityWithCords.toArray(new CityWithCords[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityByBook;
    }

    /*
    select name, latitude, longitude from cities where geodistance(longitude, latitude, 12.47, 55.38) < 30;
     */

    /*
    select cities.name, cities.latitude, cities.longitude, books.id, books.title from cities
    join mentions on (cities.id = mentions.cityid)
    join books on (mentions.bookid = books.id)
    where geodistance(latitude, longitude, 52.38, 12.47) < 15;
     */

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        BooksByVicenety booksByVicenety = null;
        try {
            //ArrayList<CityAndBooks> cityAndBooks = new ArrayList<>();
            CityAndBooks[] cityAndBooks = new CityAndBooks[0];
            Statement stmt = conn.createStatement();
            /*
            ResultSet result = stmt.executeQuery("CREATE OR REPLACE FUNCTION public.geodistance(alat double precision, alng double precision, blat double precision, blng double precision)\n" +
                    "  RETURNS double precision AS\n" + "$BODY$\n" + "SELECT asin(\n" + "  sqrt(\n" +
                    "    sin(radians($3-$1)/2)^2 +\n" + "    sin(radians($4-$2)/2)^2 *\n" + "    cos(radians($1)) *\n" + "    cos(radians($3))\n" +
                    "  )\n" + ") * 7926.3352 AS distance;\n" + "$BODY$\n" + "  LANGUAGE sql IMMUTABLE\n" + "  COST 100;");

            while (result.next()) { }
            */
            ResultSet result = stmt.executeQuery("select cities.name, cities.latitude, cities.longitude, books.id, books.title from cities\n" +
                    "    join mentions on (cities.id = mentions.cityid)\n" +
                    "    join books on (mentions.bookid = books.id)\n" +
                    "    where geodistance(latitude, longitude, " + lat + ", " + lon + ") < " + km + ";");

            /*
            while (result.next())
            {
                CityWithCords tc = new CityWithCords(result.getString("name"), result.getDouble("latitude"), result.getDouble("longitude"));
                Book bc = new Book(result.getInt("id"),result.getString("title"));
                boolean doesExist = false;
                for (int i = 0; i < cityAndBooks.length; i++) {
                    if (cityAndBooks[i].cityName == tc.cityName)
                    {
                        cityAndBooks[i].books[cityAndBooks[i].books.length+1] = bc;
                        doesExist = true;
                    }
                }
                if (!doesExist)
                {
                    cityAndBooks[cityAndBooks.length+1] = new CityAndBooks(tc.cityName, tc.latitude, tc.longitude,new Book[0]);
                }
            }
            */
            booksByVicenety = new BooksByVicenety(cityAndBooks);
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
