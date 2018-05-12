package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class AllCities extends DataSerializer implements DataObject {

    public final City[] AllCities;

    public AllCities(City[] all){
        this.AllCities = all;
    }

}
