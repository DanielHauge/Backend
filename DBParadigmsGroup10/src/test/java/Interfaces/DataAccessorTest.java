package Interfaces;

import DataAcessors.MongoDataAcessor;
import DataAcessors.Neo4jDataAcessor;
import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import Interfaces.DataAccessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;


import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

//@RunWith(Parameterized.class)
class DataAccessorTest {


    /*
    @Parameters
    public static Collection input() {
        return Arrays.asList(1,2);
    }

    @Parameter(0)
    public int DAtype;

    private int datype;

// new Neo4jDataAcessor(GraphDatabase.driver("bolt://192.168.33.11:7687", AuthTokens.basic("neo4j","class"))),
//                new RedisDataAcessor()
    public DataAccessorTest(int a){
        this.datype = a;
    }
    */




    public static DataAccessor DA;
    long Expectedtime = 2000;


    @AfterAll
    static void CrashDown(){
        DA.close();
    }

    @BeforeAll
    static void setUp() {
        DA = new RedisDataAcessor();
        //DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://192.168.33.11:7687", AuthTokens.basic("neo4j","class")));

        /*
        System.out.println("DATYPE WAS: "+datype);
        if (datype==1){
            this.DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://192.168.33.11:7687", AuthTokens.basic("neo4j","class")));
        } else if (datype==2){
            DA = new RedisDataAcessor();
        } else{
            DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://192.168.33.11:7687", AuthTokens.basic("neo4j","class")));
        }

        // else if (datype=="sql"){
        //            DA = new PostgresDataAcessor();
        //        } else if (datype=="doc"){
        //            DA = new MongoDataAcessor();
        //        }
        */

    }


    @Test
    void getAllCities() {
        long time = System.currentTimeMillis();
        AllCities a =  DA.GetAllCities();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(a.Allcities.length,is(48377));
    }

    @Test
    void getBooksByCity() {
        long time = System.currentTimeMillis();
        BooksByCity booksByCity = DA.GetBooksByCity(2624341);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(booksByCity.All.length,is(1)); // TEST SENERE FORDI DEN ER NOK FORKERT!
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
        assertThat(Manycities.Cities.length,is(94));
    }

    @Test
    void getAllAuthors() {
        long time = System.currentTimeMillis();
        AllAuthors allAuthors = DA.GetAllAuthors();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
        assertThat(allAuthors.allAuthors.length,is(14567)); //// TEST SENERE, FORDI DEN ER NOK FORKERT!!!
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
        assertThat(vicenety.AllInVicenety.length, is(112));
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    void getBooksInVicenety2() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 50);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.AllInVicenety.length, is(25));
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    void getBooksInVicenety3() {
        long time = System.currentTimeMillis();
        BooksByVicenety vicenety = DA.GetBooksInVicenety(52.38, 11.47, 20);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(vicenety.AllInVicenety.length, is(3));
        assertThat(time, is(lessThan(Expectedtime)));
    }
}