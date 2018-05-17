package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class CityWithCords extends DataSerializer implements DataObject {

    public final String cityName;
    public final double lat;
    public final double lng;

    public CityWithCords(String name, double lat, double lon){
        this.cityName = name;
        this.lat = lat;
        this.lng = lon;
    }


}
