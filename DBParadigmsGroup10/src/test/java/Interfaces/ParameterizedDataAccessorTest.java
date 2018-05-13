package Interfaces;

import DataAcessors.Neo4jDataAcessor;
import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

@RunWith(value = Parameterized.class)
public class ParameterizedDataAccessorTest {

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(
                new RedisDataAcessor(System.getenv("DBIP"))
                //new Neo4jDataAcessor(GraphDatabase.driver("bolt://"+System.getenv("DBIP")+":7687", AuthTokens.basic("neo4j","class"))),
                //new PostgresDataAcessor("jdbc:postgresql://"+System.getenv("DBIP")+":5432/postgres", "postgres", "")
        );
    }




    public ParameterizedDataAccessorTest(DataAccessor dataAccessor){
        this.DA = dataAccessor;
    }

    private static DataAccessor DA;
    private final long Expectedtime = 2000;

    @AfterClass
    public static void CrashDown(){
        DA.close();
    }

    @Test
    public void getAllCities() {
        long time = System.currentTimeMillis();
        AllCities a =  DA.GetAllCities();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(a.AllCities.length,is(48377));
    }

    @Test
    public void getBooksByCity() {
        long time = System.currentTimeMillis();
        BooksByCity booksByCity = DA.GetBooksByCity(2624341);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByCity.books.length,is(1)); // TEST SENERE FORDI DEN ER NOK FORKERT!
    }

    @Test
    public void getAllBooks() {
        long time = System.currentTimeMillis();
        AllBooks all = DA.GetAllBooks();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(all.AllBooks.length, is(37227));
    }

    @Test
    public void getCitiesBybook() {
        long time = System.currentTimeMillis();
        ManyCitiesWithCords Manycities = DA.GetCitiesBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(Manycities.cities.length,is(94));
    }

    @Test
    public void getAllAuthors() {
        long time = System.currentTimeMillis();
        AllAuthors allAuthors = DA.GetAllAuthors();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(allAuthors.AllAuthors.length,is(14569)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    public void getBookByAuthor() {
        long time = System.currentTimeMillis();
        BooksByAuthor booksByAuthor = DA.GetBookByAuthor("William Shakespeare");
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByAuthor.books.length,is(21)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    public void getCityBybook() {
        long time = System.currentTimeMillis();
        CityByBook cityByBook = DA.GetCityBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(cityByBook.cities.length, is(94)); /////// TEST SENERE! FORDI DEN ER NOK FORKERT!

    }

    @Test
    public void getBooksInVicenety1() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 100);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.Vicenety.length, is(112));
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getBooksInVicenety2() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 50);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.Vicenety.length, is(25));
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getBooksInVicenety3() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 20);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.Vicenety.length, is(3));
        assertThat(time, is(lessThan(Expectedtime)));
    }
}