package Interfaces;

import Benchmarker.BenchmarkLog;
import Benchmarker.BenchmarkLogImpl;
import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

@RunWith(value = Parameterized.class)
public class ParameterizedPerformanceTest {

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(
                new RedisDataAcessor(System.getenv("DBIP"))
                //new Neo4jDataAcessor(GraphDatabase.driver("bolt://"+System.getenv("DBIP")+":7687", AuthTokens.basic("neo4j","class"))),
                //new PostgresDataAcessor("jdbc:postgresql://"+System.getenv("DBIP")+":5432/postgres", "postgres", "")
        );
    }




    public ParameterizedPerformanceTest(DataAccessor dataAccessor){
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
        DA.GetAllCities();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getBooksByCity() {
        long time = System.currentTimeMillis();
        DA.GetBooksByCity(2624341);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getAllBooks() {
        long time = System.currentTimeMillis();
        DA.GetAllBooks();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getCitiesBybook() {
        long time = System.currentTimeMillis();
        DA.GetCitiesBybook(1);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getAllAuthors() {
        long time = System.currentTimeMillis();
        DA.GetAllAuthors();
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getBookByAuthor() {
        long time = System.currentTimeMillis();
        DA.GetBookByAuthor("William Shakespeare");
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getCityBybook() {
        long time = System.currentTimeMillis();
        //DA.GetCityBybook(1,);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));

    }

    @Test
    public void getBooksInVicenety1() {
        long time = System.currentTimeMillis();
        DA.GetBooksInVicenety(52.38, 11.47, 100);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getBooksInVicenety2() {
        long time = System.currentTimeMillis();
        DA.GetBooksInVicenety(52.38, 11.47, 50);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }

    @Test
    public void getBooksInVicenety3() {
        long time = System.currentTimeMillis();
        DA.GetBooksInVicenety(52.38, 11.47, 20);
        time = System.currentTimeMillis()-time;
        System.out.println(time+"ms");
        assertThat(time, is(lessThan(Expectedtime)));
    }
}