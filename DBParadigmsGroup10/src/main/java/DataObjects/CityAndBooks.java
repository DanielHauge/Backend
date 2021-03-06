package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class CityAndBooks extends DataSerializer implements DataObject {

    public final String cityName;
    public final double latitude;
    public final double longitude;
    public Book[] books;

    public CityAndBooks(String name, double lat, double lon, Book[] b){
        this.cityName = name;
        this.latitude = lat;
        this.longitude = lon;
        this.books = b;
    }

}
