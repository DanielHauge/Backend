package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class CityAndBooks extends DataSerializer implements DataObject {

    private final String cityName;
    private final double latitude;
    private final double longitude;
    private final Book[] books;

    public CityAndBooks(String name, double lat, double lon, Book[] b){
        this.cityName = name;
        this.latitude = lat;
        this.longitude = lon;
        this.books = b;
    }

}
