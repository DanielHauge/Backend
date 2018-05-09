package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class AllAuthors implements DataObject {

    public String[] allAuthors;

    public AllAuthors(String[] a){
        this.allAuthors = a;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
