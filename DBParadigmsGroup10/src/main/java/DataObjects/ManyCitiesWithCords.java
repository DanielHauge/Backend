package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class ManyCitiesWithCords implements DataObject {

    public CityWithCords[] Cities;

    public ManyCitiesWithCords(CityWithCords[] allcities){
        this.Cities = allcities;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
