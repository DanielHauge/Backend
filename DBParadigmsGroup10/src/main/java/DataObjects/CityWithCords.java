package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class CityWithCords extends DataSerializer implements DataObject {

    private final String cityName;
    private final double latitude;
    private final double longitude;

    public CityWithCords(String name, double lat, double lon){
        this.cityName = name;
        this.latitude = lat;
        this.longitude = lon;
    }


}
