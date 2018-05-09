package Interfaces;

import DataObjects.*;

public interface DataAccessor {

        // User story 1
        public AllCities GetAllCities();

        public BooksByCity GetBooksByCity(int cityid);


        // User story 2
        public AllBooks GetAllBooks();

        public ManyCitiesWithCords GetCitiesBybook(int bookid);


        // User story 3
        public AllAuthors GetAllAuthors();

        public BooksByAuthor GetBookByAuthor(String author);

        public CityByBook GetCityBybook(int bookid);


        // User story 4
        public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km);




}
