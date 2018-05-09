package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class CityAndBooks implements DataObject {

    public String cityName;
    public double latitude;
    public double longitude;
    public Book[] books;

    public CityAndBooks(String name, double lat, double lon, Book[] b){
        this.cityName = name;
        this.latitude = lat;
        this.longitude = lon;
        this.books = b;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
