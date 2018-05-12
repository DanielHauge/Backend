package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class AllBooks extends DataSerializer implements DataObject {

    public final Book[] AllBooks;

    public AllBooks(Book[] all){
        this.AllBooks = all;
    }

}
