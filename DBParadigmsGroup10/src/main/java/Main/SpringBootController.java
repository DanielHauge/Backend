package Main;

import Interfaces.JsonMachine;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class SpringBootController {


    @RequestMapping(value="/api/cities", produces = {"application/json"})
    @ResponseBody
    String AllCities(){
        return "[" +
                "  {" +
                "    \"id\": 12345," +
                "    \"cityName\": \"Copenhagen\"" +
                "  }," +
                "  {" +
                "    \"id\": 12346," +
                "    \"cityName\": \"Helsingor\"" +
                "  }," +
                "  {" +
                "    \"id\": 12347," +
                "    \"cityName\": \"Aarhus\"" +
                "  }" +
                "]";
    }

    @RequestMapping(value="/api/books/bycity/{CityId}", produces = {"application/json"})
    @ResponseBody
    String ByCity(@PathVariable("CityId") int id){
        return "[\n" +
                "  {\n" +
                "    \"bookTitle\": \"some book\", \n" +
                "    \"author\": \"James Someone\",\n" +
                "    \"mentionCount\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"bookTitle\": \"some book\", \n" +
                "    \"author\": \"James Someone\",\n" +
                "    \"mentionCount\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"bookTitle\": \"some book\", \n" +
                "    \"author\": \"James Someone\",\n" +
                "    \"mentionCount\": "+id+"\n" +
                "  }\n" +
                "]";
    }

    @RequestMapping(value="/api/books", produces = {"application/json"})
    @ResponseBody
    String AllBooks(){
        return "[\n" +
                "  {\n" +
                "    \"bookId\": 12345,\n" +
                "    \"bookTitle\": \"Some book 1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"bookId\": 12346,\n" +
                "    \"bookTitle\": \"Some book 2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"bookId\": 12347,\n" +
                "    \"bookTitle\": \"Some book 3\"\n" +
                "  }\n" +
                "]";
    }

    @RequestMapping(value="/api/cities/bybook/{bookId}", produces = {"application/json"})
    @ResponseBody
    String CitiesByBook(@PathVariable("bookId") int bookId){

        return "[\n" +
                "  {\n" +
                "    \"cityName\": \"Copenhagen\",\n" +
                "    \"latitude\": 1.213312,\n" +
                "    \"longitude\": 1.21321\n" +
                "  },\n" +
                "  {\n" +
                "    \"cityName\": \"Stockholm\",\n" +
                "    \"latitude\": 1.213312,\n" +
                "    \"longitude\": 1.21321\n" +
                "  },\n" +
                "  {\n" +
                "    \"cityName\": \"Amsterdam\",\n" +
                "    \"latitude\": 1.213312,\n" +
                "    \"longitude\": 1.21321\n" +
                "  }\n" + bookId +
                "]";
    }

    @RequestMapping(value="/api/authors", produces = {"application/json"})
    @ResponseBody
    String allauthors(){
        return "[\n" +
                "  \"Author One\",\n" +
                "  \"Author Two\"\n" +
                "]";
    }

    @RequestMapping(value = "/api/books/byauthor/{authorName}", produces = {"application/json"})
    @ResponseBody
    String BooksByauthor(@PathVariable("authorName") String author){

        return "{\n" +
                "  \"authorName\": \""+author+"\",\n" +
                "  \"books\": [\n" +
                "    {\n" +
                "      \"bookId\": 123,\n" +
                "      \"bookTitle\": \"Book 1\",\n" +
                "    },\n" +
                "    {\n" +
                "      \"bookId\": 124,\n" +
                "      \"bookTitle\": \"Book 2\",\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @RequestMapping(value="/api/books/bylocation/{longitude}/{latitude}", produces = {"application/json"})
    @ResponseBody
    String BooksByLocation(@PathVariable("longitude") double longitude, @PathVariable("latitude") double latitude){
        return "[\n" +
                "  {\n" +
                "    \"cityName\": \"Copenhagen\",\n" +
                "    \"latitude\": "+latitude+",\n" +
                "    \"longitude\": "+longitude+"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cityName\": \"Brodnby\",\n" +
                "    \"latitude\": "+latitude+",\n" +
                "    \"longitude\": "+longitude+"\n" +
                "  },\n" +
                "  {\n" +
                "    \"cityName\": \"Amager\",\n" +
                "    \"latitude\": "+latitude+",\n" +
                "    \"longitude\": "+longitude+"\n" +
                "  }\n" +
                "]";
    }


}
