package DataAcessors;

import DataObjects.*;
import Interfaces.DataAccessor;

public class StubbedDataAccessor implements DataAccessor {
    @Override
    public AllCities GetAllCities() {
        return null;
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        return null;
    }

    @Override
    public AllBooks GetAllBooks() {
        return null;
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        return null;
    }

    @Override
    public AllAuthors GetAllAuthors() {
        return null;
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        return null;
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {
        return null;
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        return null;
    }

    @Override
    public void close() {

    }
}
