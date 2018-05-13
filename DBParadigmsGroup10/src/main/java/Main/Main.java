package Main;

import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import Interfaces.DataAccessor;
import org.springframework.boot.SpringApplication;

public class Main {

    public static DataAccessor DA;

    public static void main(String[] args){

<<<<<<< HEAD
        DA = new RedisDataAcessor(System.getenv("DBIP"));
=======
        /*
        if (args[0] == "neo4j"){
            DA = new Neo4jDataAccessor();
        }
        */

        //DA = new RedisDataAcessor(System.getenv("DBIP"));
        DA = new PostgresDataAcessor("jdbc:postgresql://"+"192.168.33.11"+":5432/postgres", "postgres", "");
>>>>>>> e6845af34c6c168d49ad3d7eed404ec178de5909

        SpringApplication.run(SpringBootController.class);
    }


}
