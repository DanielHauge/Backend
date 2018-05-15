package Main;

import DataAcessors.Neo4jDataAcessor;
import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import Interfaces.DataAccessor;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.SpringApplication;

public class Main {

    public static DataAccessor DA;

    public static void main(String[] args){

        //DA = new RedisDataAcessor(System.getenv("DBIP"));
        /*
        if (args[0] == "neo4j"){
            DA = new Neo4jDataAccessor();
        }
        */

        //DA = new RedisDataAcessor(System.getenv("DBIP"));
        //DA = new PostgresDataAcessor("jdbc:postgresql://"+"192.168.33.11"+":5432/postgres", "postgres", "");
        DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://" + System.getenv("DBIP") + ":7687", AuthTokens.basic("neo4j", "class")));

        SpringApplication.run(SpringBootController.class);
    }


}
