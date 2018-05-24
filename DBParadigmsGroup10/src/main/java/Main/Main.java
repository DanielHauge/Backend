package Main;

import Benchmarker.BenchmarkDurationFactoryImpl;
import Benchmarker.BenchmarkLogFactoryImpl;
import Benchmarker.BenmarkLoggerImpl;
import Benchmarker.enums.DBMS;
import DataAcessors.PostgresDataAcessor;
import Interfaces.DataAccessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Main {

    public static DataAccessor DA;
    public static BenmarkLoggerImpl Logger;
    public static DBMS DB;

    public static void main(String[] args){

        //DA = new RedisDataAcessor(System.getenv("DBIP"));
        DB = DBMS.postgres;
        DA = new PostgresDataAcessor("jdbc:postgresql://"+"192.168.33.11"+":5432/postgres", "postgres", "");
        //DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://" + System.getenv("DBIP") + ":7687", AuthTokens.basic("neo4j", "class")));

        Logger = new BenmarkLoggerImpl(new BenchmarkLogFactoryImpl(), new BenchmarkDurationFactoryImpl());

        SpringApplication.run(SpringBootController.class);
    }


}
