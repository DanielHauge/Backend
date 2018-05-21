package Main;

import Benchmarker.BenchmarkLog;
import Benchmarker.BenchmarkTimer;
import Benchmarker.enums.DBMS;
import Benchmarker.enums.Query;
import DataAcessors.Neo4jDataAcessor;
import DataAcessors.PostgresDataAcessor;
import DataAcessors.RedisDataAcessor;
import Interfaces.DataObject;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static Main.Main.DA;
import static Main.Main.Logger;
import static Main.Main.DB;

@Controller
@EnableAutoConfiguration
class SpringBootController {


    @RequestMapping(value="/api/cities", produces = {"application/json"})
    @ResponseBody
    String AllCities(){
        long now = System.currentTimeMillis();
        DataObject result = DA.GetAllCities();
        System.out.println(System.currentTimeMillis()-now);
        return result.SerializeToJson();
    }

    @RequestMapping(value="/api/books/bycity/{CityId}", produces = {"application/json"})
    @ResponseBody
    String ByCity(@PathVariable("CityId") int id){
        DataObject result = DA.GetBooksByCity(id);
        return result.SerializeToJson();
    }

    @RequestMapping(value="/api/books", produces = {"application/json"})
    @ResponseBody
    String AllBooks(){
        DataObject allbooks = DA.GetAllBooks();
        return allbooks.SerializeToJson();
    }

    @RequestMapping(value="/api/cities/bybook/{bookId}", produces = {"application/json"})
    @ResponseBody
    String CitiesByBook(@PathVariable("bookId") int bookId){
        DataObject CityBybook = DA.GetCitiesBybook(bookId);
        return CityBybook.SerializeToJson();
    }

    @RequestMapping(value="/api/authors", produces = {"application/json"})
    @ResponseBody
    String allauthors(){
        DataObject Authors = DA.GetAllAuthors();
        return Authors.SerializeToJson();
    }

    @RequestMapping(value = "/api/books/byauthor/{authorName}", produces = {"application/json"})
    @ResponseBody
    String BooksByauthor(@PathVariable("authorName") String author){
        DataObject BooksByAuthor = DA.GetBookByAuthor(author);
        return BooksByAuthor.SerializeToJson();
    }

    @RequestMapping(value = "/api/citiesv/bybook/{bookId}", produces = {"application/json"})
    @ResponseBody
    String CitiesByBookWithExtra(@PathVariable("bookId") int id){
        DataObject CityByBookWithExtra = DA.GetCityBybook(id);
        return CityByBookWithExtra.SerializeToJson();
    }

    @RequestMapping(value="/api/books/bylocation/{longitude}/{latitude}", produces = {"application/json"})
    @ResponseBody
    String BooksByLocation(@PathVariable("longitude") double longitude, @PathVariable("latitude") double latitude){
        DataObject BooksByVicenety = DA.GetBooksInVicenety(latitude, longitude, 20);
        return BooksByVicenety.SerializeToJson();
    }

    @RequestMapping(value="/api/log", produces = {"application/json"})
    @ResponseBody
    String PrintLog(){
        return Logger.PrintLog().SerializeToJson();
    }

    @RequestMapping(value = "/api/setdb/{dbtype}/{ip}")
    @ResponseBody
    String SetDB(@PathVariable("dbtype") String dbtype, @PathVariable("ip") String ip){
        DA.close();
        String reply = "succes";
        try {
            switch (dbtype) {
                case "neo4j":
                    DB = DBMS.neo4j;
                    DA = new Neo4jDataAcessor(GraphDatabase.driver("bolt://" + ip + ":7687", AuthTokens.basic("neo4j", "class")));
                    break;
                case "postgres":
                    DB = DBMS.postgres;
                    DA = new PostgresDataAcessor("jdbc:postgresql://" + ip + ":5432/postgres", "postgres", "");
                    break;
                case "redis":
                    DB = DBMS.redis;
                    DA = new RedisDataAcessor(ip);
                    break;
                    default:
                        DB = DBMS.redis;
                        DA = new RedisDataAcessor(ip);
                        break;
            }
        } catch (Exception e){return "error";}

        return reply;

    }




}
