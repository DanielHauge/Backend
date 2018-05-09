package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class BooksByCity implements DataObject {

    public BookWithMentions[] All;

    public BooksByCity(BookWithMentions[] all){
        this.All = all;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
