package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class Book implements DataObject {

    public int bookId;
    public String bookTitle;

    public Book(int id, String title){
        this.bookId = id;
        this.bookTitle = title;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
