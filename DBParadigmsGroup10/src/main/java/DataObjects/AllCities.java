package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class AllCities implements DataObject {

    public City[] Allcities;

    public AllCities(City[] all){
        this.Allcities = all;
    }

    @Override
    public String SerializeToJson()
    {
        return new Gson().toJson(this);
    }
}
