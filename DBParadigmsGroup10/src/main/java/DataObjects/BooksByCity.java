package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;


public class BooksByCity extends DataSerializer implements DataObject {

    public final BookWithMentions[] books;

    public BooksByCity(BookWithMentions[] books){
        this.books = books;
    }

}
