

package Main;

import DataAcessors.RedisDataAcessor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



public class WebInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        Main.DA = new RedisDataAcessor(System.getenv("DBIP"));
        return application.sources(Main.class);
    }
}

