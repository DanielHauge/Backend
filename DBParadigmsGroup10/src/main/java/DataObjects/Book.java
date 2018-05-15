package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class Book extends DataSerializer implements DataObject {

    public final int bookId;
    public final String bookTitle;

    public Book(int id, String title){
        this.bookId = id;
        this.bookTitle = title;
    }

}
