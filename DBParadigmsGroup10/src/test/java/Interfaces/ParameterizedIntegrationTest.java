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
public class ParameterizedIntegrationTest {

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(
                new RedisDataAcessor(System.getenv("DBIP"))
                //new Neo4jDataAcessor(GraphDatabase.driver("bolt://"+System.getenv("DBIP")+":7687", AuthTokens.basic("neo4j","class"))),
                //new PostgresDataAcessor("jdbc:postgresql://"+System.getenv("DBIP")+":5432/postgres", "postgres", "")
        );
    }




    public ParameterizedIntegrationTest(DataAccessor dataAccessor){
        this.DA = dataAccessor;
    }

    private static DataAccessor DA;

    @AfterClass
    public static void CrashDown(){
        DA.close();
    }

    @Test
    public void getAllCities() {
        AllCities a =  DA.GetAllCities();
        assertThat(a.AllCities.length,is(48377));
    }

    @Test
    public void getBooksByCity() {
        BooksByCity booksByCity = DA.GetBooksByCity(2624341);
        assertThat(booksByCity.books.length,is(0)); // TEST SENERE FORDI DEN ER NOK FORKERT!
    }

    @Test
    public void getAllBooks() {
        AllBooks all = DA.GetAllBooks();
        assertThat(all.AllBooks.length, is(37221));
    }

    @Test
    public void getCitiesBybook() {
        ManyCitiesWithCords Manycities = DA.GetCitiesBybook(1);
        assertThat(Manycities.cities.length,is(13));
    }

    @Test
    public void getAllAuthors() {
        AllAuthors allAuthors = DA.GetAllAuthors();
        assertThat(allAuthors.AllAuthors.length,is(14790)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    public void getBookByAuthor() {
        BooksByAuthor booksByAuthor = DA.GetBookByAuthor("William Shakespeare");
        assertThat(booksByAuthor.books.length,is(21)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
    }

    @Test
    public void getCityBybook() {
        CityByBook cityByBook = DA.GetCityBybook(1);
        assertThat(cityByBook.cities.length, is(13)); /////// TEST SENERE! FORDI DEN ER NOK FORKERT!
    }

    @Test
    public void getBooksInVicenety1() {
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 100);
        assertThat(vicenety.Vicenety.length, is(112));
    }

    @Test
    public void getBooksInVicenety2() {
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 50);
        assertThat(vicenety.Vicenety.length, is(25));
    }

    @Test
    public void getBooksInVicenety3() {
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 20);
        assertThat(vicenety.Vicenety.length, is(3));
    }
}