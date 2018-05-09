package DataObjects;

import Interfaces.DataObject;

public class Book implements DataObject {

    public int bookId;
    public String bookTitle;

    public Book(int id, String title){
        this.bookId = id;
        this.bookTitle = title;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
