package Main;

import Interfaces.DataObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static Main.Main.DA;

@Controller
@EnableAutoConfiguration
public class SpringBootController {


    @RequestMapping(value="/api/cities", produces = {"application/json"})
    @ResponseBody
    String AllCities(){
        DataObject result = DA.GetAllCities();
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
        DataObject BooksByVicenety = DA.GetBooksInVicenety(latitude, longitude, 50);
        return BooksByVicenety.SerializeToJson();
    }


}
