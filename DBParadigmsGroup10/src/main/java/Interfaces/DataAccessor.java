package Interfaces;

import DataObjects.*;

public interface DataAccessor {

        // User story 1
        AllCities GetAllCities();

        BooksByCity GetBooksByCity(int cityid);


        // User story 2
        AllBooks GetAllBooks();

        ManyCitiesWithCords GetCitiesBybook(int bookid);


        // User story 3
        AllAuthors GetAllAuthors();

        BooksByAuthor GetBookByAuthor(String author);

        CityByBook GetCityBybook(int bookid);


        // User story 4
        BooksByVicenety GetBooksInVicenety(double lat, double lon, int km);

        void close();


}
