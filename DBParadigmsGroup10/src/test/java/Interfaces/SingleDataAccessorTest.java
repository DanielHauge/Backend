package Interfaces;

import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

class SingleDataAccessorTest {

    private static DataAccessor DA;
    private final long Expectedtime = 2000;

    @AfterAll
    static void CrashDown(){
        DA.close();
    }

    @BeforeAll
    static void setUp() {
        DA = new RedisDataAcessor(System.getenv("DBIP"));
    }


    @Test
    void getAllCities() {
        long time = System.currentTimeMillis();
        AllCities a =  DA.GetAllCities();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(a.AllCities.length,is(48377));
    }

    @Test
    void getBooksByCity() {
        long time = System.currentTimeMillis();
        BooksByCity booksByCity = DA.GetBooksByCity(2624341);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByCity.books.length,is(1)); // TEST SENERE FORDI DEN ER NOK FORKERT!
    }

    @Test
    void getAllBooks() {
        long time = System.currentTimeMillis();
        AllBooks all = DA.GetAllBooks();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(all.AllBooks.length, is(37228));
    }

    @Test
    void getCitiesBybook() {
        long time = System.currentTimeMillis();
        ManyCitiesWithCords Manycities = DA.GetCitiesBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(Manycities.cities.length,is(94));
    }

    @Test
    void getAllAuthors() {
        long time = System.currentTimeMillis();
        AllAuthors allAuthors = DA.GetAllAuthors();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(allAuthors.AllAuthors.length,is(14567)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    void getBookByAuthor() {
        long time = System.currentTimeMillis();
        BooksByAuthor booksByAuthor = DA.GetBookByAuthor("William Shakespeare");
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByAuthor.books.length,is(21)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    void getCityBybook() {
        long time = System.currentTimeMillis();
        CityByBook cityByBook = DA.GetCityBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(cityByBook.cities.length, is(94)); /////// TEST SENERE! FORDI DEN ER NOK FORKERT!

    }

    @Test
    void getBooksInVicenety1() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 100);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.Vicenety.length, is(112));
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    void getBooksInVicenety2() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 50);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.Vicenety.length, is(25));
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    void getBooksInVicenety3() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 20);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.Vicenety.length, is(3));
        assertThat(time, is(lessThan(Expectedtime)));
    }
}