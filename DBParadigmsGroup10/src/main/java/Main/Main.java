package Main;

import DataAcessors.RedisDataAcessor;
import Interfaces.DataAccessor;
import org.springframework.boot.SpringApplication;

public class Main {

    public static DataAccessor DA;

    public static void main(String[] args){

        /*
        if (args[0] == "neo4j"){
            DA = new Neo4jDataAccessor();
        }
        */

        DA = new RedisDataAcessor(System.getenv("DBIP"));

        SpringApplication.run(SpringBootController.class);
    }


}
