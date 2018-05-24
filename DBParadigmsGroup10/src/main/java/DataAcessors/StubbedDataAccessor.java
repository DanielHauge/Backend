package DataAcessors;

import DataObjects.*;
import Interfaces.DataAccessor;

public class StubbedDataAccessor implements DataAccessor {

    @Override
    public AllCities GetAllCities() {
        City city = new City(3576260,"Falmouth");
        return new AllCities(new City[]{city});
    }

    @Override
    public BooksByCity GetBooksByCity(int cityid) {
        //cityid = 3576260;
        BookWithMentions bookWithMentions = new BookWithMentions("The Hunting of the Snark","Lewis Carroll",1);
        return new BooksByCity(new BookWithMentions[]{bookWithMentions});
    }

    @Override
    public AllBooks GetAllBooks() {
        Book book = new Book(5,"The Hunting of the Snark");
        return new AllBooks(new Book[]{book});
    }

    @Override
    public ManyCitiesWithCords GetCitiesBybook(int bookid) {
        CityWithCords cityWithCords = new CityWithCords("Falmouth",17.02741,-61.78136);
        return new ManyCitiesWithCords(new CityWithCords[]{cityWithCords});
    }

    @Override
    public AllAuthors GetAllAuthors() {
        Author[] authors = new Author[14];
        authors[0] = new Author("Jefferson Thomas");
        authors[1] = new Author("Lewis Carroll");
        authors[2] = new Author("Charles Dodgson");
        authors[3] = new Author("Central Intelligence Agency");
        authors[4] = new Author("Herman Melville");
        authors[5] = new Author("James M. Barrie");
        authors[6] = new Author("Alexander Hamilton");
        authors[7] = new Author("Longfellow");
        authors[8] = new Author("United States");
        authors[9] = new Author("John Milton");
        authors[10] = new Author("John Fitzgerald");
        authors[11] = new Author("Lincoln Abraham");
        authors[12] = new Author("Founding Fathers");
        authors[13] = new Author("Henry Patrick");
        return new AllAuthors(authors);
    }

    @Override
    public BooksByAuthor GetBookByAuthor(String author) {
        author = "Lewis Carroll";
        Book book = new Book(5,"The Hunting of the Snark");
        Book book1 = new Book(4,"Through the Looking-Glass");
        Book book2 = new Book(3,"Alice's Adventures in Wonderland");
        return new BooksByAuthor(author,new Book[]{book,book1,book2});
    }

    @Override
    public CityByBook GetCityBybook(int bookid) {
        //3576260
        CityWithCords cityWithCords = new CityWithCords("Falmouth", 17.02741,-61.78136);
        return new CityByBook(5,"The Hunting of the Snark",new CityWithCords[]{cityWithCords});
    }

    @Override
    public BooksByVicenety GetBooksInVicenety(double lat, double lon, int km) {
        //lat 17.02741
        //lon -61.78136
        // range 10
        Book book = new Book(5,"The Hunting of the Snark");
        CityAndBooks cityAndBooks = new CityAndBooks("Falmouth",17.02741,-61.78136,new Book[]{book});
        return new BooksByVicenety(new CityAndBooks[]{cityAndBooks});
    }

    @Override
    public void close() {

    }
}
