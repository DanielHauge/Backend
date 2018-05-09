package DataObjects;

import Interfaces.DataObject;

public class AllBooks implements DataObject {

    public Book[] AllBooks;

    public AllBooks(Book[] all){
        this.AllBooks = all;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
