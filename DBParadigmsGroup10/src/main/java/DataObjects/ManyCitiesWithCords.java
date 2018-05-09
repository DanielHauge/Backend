package DataObjects;

import Interfaces.DataObject;

public class ManyCitiesWithCords implements DataObject {

    public CityWithCords[] Cities;

    public ManyCitiesWithCords(CityWithCords[] allcities){
        this.Cities = allcities;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
