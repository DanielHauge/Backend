package Main;

import DataAcessors.RedisDataAcessor;
import Interfaces.DataAccessor;
import org.springframework.boot.SpringApplication;

public class Main {

    public static DataAccessor DA;

    public static void main(String[] args){

        DA = new RedisDataAcessor(System.getenv("DBIP"));

        SpringApplication.run(SpringBootController.class);
    }


}
