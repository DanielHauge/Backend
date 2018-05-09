package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class BooksByAuthor implements DataObject {

    public String authorName;
    public Book[] books;


    public BooksByAuthor(String authorName, Book[] books){
        this.authorName = authorName;
        this.books = books;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
