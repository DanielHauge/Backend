package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class City extends DataSerializer implements DataObject {


    private final int id;
    private final String cityName;

    public City(int id, String name){
        this.id = id;
        this.cityName = name;
    }



}
