package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class BooksByAuthor extends DataSerializer implements DataObject {

    private final String authorName;
    public final Book[] books;


    public BooksByAuthor(String authorName, Book[] books){
        this.authorName = authorName;
        this.books = books;
    }

}
