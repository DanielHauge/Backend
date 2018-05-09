package Interfaces;

import DataObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DataAccessorTest {

    DataAccessor DA;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllCities() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        AllCities a =  DA.GetAllCities();
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(a.Allcities.length==48377);
    }

    @Test
    void getBooksByCity() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByCity booksByCity = DA.GetBooksByCity(2624341);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(booksByCity.All.length==1); // TEST SENERE FORDI DEN ER NOK FORKERT!
    }

    @Test
    void getAllBooks() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        AllBooks all = DA.GetAllBooks();
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(all.AllBooks.length==37227);
    }

    @Test
    void getCitiesBybook() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        ManyCitiesWithCords Manycities = DA.GetCitiesBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(Manycities.Cities.length==94);
    }

    @Test
    void getAllAuthors() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        AllAuthors allAuthors = DA.GetAllAuthors();
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(allAuthors.allAuthors.length==14569); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    void getBookByAuthor() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByAuthor booksByAuthor = DA.GetBookByAuthor("William Shakespeare");
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(booksByAuthor.books.length==21);  //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    void getCityBybook() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        CityByBook cityByBook = DA.GetCityBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(cityByBook.cities.length==94); /////// TEST SENERE! FORDI DEN ER NOK FORKERT!

    }

    @Test
    void getBooksInVicenety1() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByVicenety vicenety = DA.GetBooksInVicenety(11.47, 52.38, 100);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(vicenety.AllInVicenety.length==112);
    }

    @Test
    void getBooksInVicenety2() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByVicenety vicenety = DA.GetBooksInVicenety(11.47, 52.38, 50);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(vicenety.AllInVicenety.length==25);
    }

    @Test
    void getBooksInVicenety3() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByVicenety vicenety = DA.GetBooksInVicenety(11.47, 52.38, 20);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertTrue(time< 2000);
        assertTrue(vicenety.AllInVicenety.length==5);
    }
}