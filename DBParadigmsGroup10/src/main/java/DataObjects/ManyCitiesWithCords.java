package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class ManyCitiesWithCords extends DataSerializer implements DataObject {

    public final CityWithCords[] cities;

    public ManyCitiesWithCords(CityWithCords[] allcities){
        this.cities = allcities;
    }


}
