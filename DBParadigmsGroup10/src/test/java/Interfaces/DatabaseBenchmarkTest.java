package Interfaces;

import DataAcessors.Neo4jDataAcessor;
import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseBenchmarkTest {


    private static DataAccessor DA;

    private static int[] bookids;
    private static int[] cityids;
    private static double[][] cords;
    private static String[] authors;

    @AfterAll
    static void CrashDown(){
        DA.close();
    }

    @BeforeAll
    static void setUp() {
        //DA = new RedisDataAcessor(System.getenv("DBIP"));
        DA = new PostgresDataAcessor("jdbc:postgresql://"+"192.168.33.11"+":5432/postgres", "postgres", "");
        //DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://"+System.getenv("DBIP")+":7687", AuthTokens.basic("neo4j","class")));


        bookids = new int[]{4421, 9980, 1, 16543, 37128};
        cityids = new int[]{2618425, 2800866, 1819729, 3587266, 2950159};
        cords = new double[][]{ {49.89999975254307, 2.3000022768974304 }, {28.538340619838415, -81.37923806905746}, {33.4483793942478,-112.07404106855392 }, {40.76078125849779,-111.8910500407219}, {49.89999975254307,2.3000022768974304}  };
        authors = new String[]{"William Shakspeare", "Charles Darwin", "Jane Austen", "Isaac Newton", "Larry Evans"};
    }


    @Test
    void getAllCities() {
        System.out.println("getAllCities!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetAllCities();
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();


    }

    @Test
    void getBooksByCity() {


        System.out.println("getBooksByCity");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetBooksByCity(cityids[i]);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();

    }

    @Test
    void getAllBooks() {


        System.out.println("getAllBooks!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetAllBooks();
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getCitiesBybook() {


        System.out.println("getCitiesBybook!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetCitiesBybook(bookids[i]);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getAllAuthors() {


        System.out.println("getAllAuthors!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetAllAuthors();
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getBookByAuthor() {


        System.out.println("getBookByAuthor!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetBookByAuthor(authors[i]);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getCityBybook() {


        System.out.println("getCityBybook!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            //DA.GetCityBybook(bookids[i]);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getBooksInVicenety1() {


        System.out.println("getBooksInVicenety1!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetBooksInVicenety(cords[i][0], cords[i][1], 100);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getBooksInVicenety2() {
        System.out.println("getBooksInVicenety2!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetBooksInVicenety(cords[i][0], cords[i][1], 50);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }

    @Test
    void getBooksInVicenety3() {

        System.out.println("getBooksInVicenety3!");
        long[] measurements = new long[5];
        for (int i = 4; i>=0; i--){
            long now = System.currentTimeMillis();
            DA.GetBooksInVicenety(cords[i][0], cords[i][1], 20);
            long after = System.currentTimeMillis()-now;
            measurements[i] = after;
            System.out.println(after+"ms");
        }

        System.out.println();
        Arrays.sort(measurements);
        System.out.println("Median: " +measurements[2]+"ms");
        long average = 0;
        for (long measurement : measurements) {
            average += measurement;
        }
        System.out.println("Average: "+average/5 + "ms");
        System.out.println();
    }
}