package Interfaces;

import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import Interfaces.DataAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataAccessorTest {

    long Expectedtime = 2000;
    DataAccessor DA;

    @BeforeEach
    void setUp() {
        DA = new RedisDataAcessor();

    }

    @Test
    void getAllCities() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        AllCities a =  DA.GetAllCities();
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(a.Allcities.length,is(48377));
    }

    @Test
    void getBooksByCity() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByCity booksByCity = DA.GetBooksByCity(2624341);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByCity.All.length,is(1)); // TEST SENERE FORDI DEN ER NOK FORKERT!
    }

    @Test
    void getAllBooks() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        AllBooks all = DA.GetAllBooks();
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(all.AllBooks.length, is(37227));
    }

    @Test
    void getCitiesBybook() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        ManyCitiesWithCords Manycities = DA.GetCitiesBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(Manycities.Cities.length,is(94));
    }

    @Test
    void getAllAuthors() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        AllAuthors allAuthors = DA.GetAllAuthors();
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(allAuthors.allAuthors.length,is(14569)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    void getBookByAuthor() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByAuthor booksByAuthor = DA.GetBookByAuthor("William Shakespeare");
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByAuthor.books.length,is(21)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    void getCityBybook() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        CityByBook cityByBook = DA.GetCityBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(cityByBook.cities.length, is(94)); /////// TEST SENERE! FORDI DEN ER NOK FORKERT!

    }

    @Test
    void getBooksInVicenety1() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 100);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(vicenety.AllInVicenety.length, is(112));
    }

    @Test
    void getBooksInVicenety2() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 50);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(vicenety.AllInVicenety.length, is(25));
    }

    @Test
    void getBooksInVicenety3() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 20);
        time = System.currentTimeMillis()-time;
        System.out.println(time);
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(vicenety.AllInVicenety.length, is(3));
    }
}