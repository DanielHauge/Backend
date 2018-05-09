package DataObjects;

import Interfaces.DataObject;

public class AllCities implements DataObject {

    public City[] Allcities;

    public AllCities(City[] all){
        this.Allcities = all;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
