package DataObjects;

import Interfaces.DataObject;

public class BooksByCity implements DataObject {

    public BookWithMentions[] All;

    public BooksByCity(BookWithMentions[] all){
        this.All = all;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
