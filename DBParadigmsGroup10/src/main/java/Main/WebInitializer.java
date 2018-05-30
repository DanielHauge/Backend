

package Main;

import Benchmarker.BenchmarkDurationFactoryImpl;
import Benchmarker.BenchmarkLogFactoryImpl;
import Benchmarker.BenmarkLoggerImpl;
import Benchmarker.enums.DBMS;
import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



public class WebInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //Main.DA = new PostgresDataAcessor("jdbc:postgresql://"+"192.168.33.11"+":5432/postgres", "postgres", "");
        Main.DB = DBMS.redis;
        Main.DA = new RedisDataAcessor(System.getenv("DBIP"));

        Main.Logger = new BenmarkLoggerImpl(new BenchmarkLogFactoryImpl(), new BenchmarkDurationFactoryImpl());
        return application.sources(Main.class);
    }
}

