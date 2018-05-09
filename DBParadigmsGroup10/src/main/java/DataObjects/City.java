package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class City implements DataObject {


    public int id;
    public String cityName;

    public City(int id, String name){
        this.id = id;
        this.cityName = name;
    }


    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
