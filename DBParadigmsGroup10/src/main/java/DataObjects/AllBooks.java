package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class AllBooks implements DataObject {

    public Book[] AllBooks;

    public AllBooks(Book[] all){
        this.AllBooks = all;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
