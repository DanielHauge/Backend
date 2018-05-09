package DataObjects;

import Interfaces.DataObject;

public class CityWithCords implements DataObject {

    public String cityName;
    public double latitude;
    public double longitude;

    public CityWithCords(String name, double lat, double lon){
        this.cityName = name;
        this.latitude = lat;
        this.longitude = lon;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
