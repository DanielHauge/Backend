package Main;

import Interfaces.DataAccessor;
import org.springframework.boot.SpringApplication;

public class Main {

    public static DataAccessor DA;
    public static DBConnection Conn;

    public static void main(String[] args) throws Exception{

        /*
        if (args[0] == "neo4j"){
            DA = new Neo4jDataAccessor();
        }
        */
        
        SpringApplication.run(SpringBootController.class, new String[]{});
    }


}
