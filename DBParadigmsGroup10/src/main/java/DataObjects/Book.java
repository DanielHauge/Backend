package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class Book extends DataSerializer implements DataObject {

    private final int bookId;
    private final String bookTitle;

    public Book(int id, String title){
        this.bookId = id;
        this.bookTitle = title;
    }

}
