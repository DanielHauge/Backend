package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class BooksByVicenety implements DataObject {

    public CityAndBooks[] AllInVicenety;

    public BooksByVicenety(CityAndBooks[] all){
        this.AllInVicenety = all;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
