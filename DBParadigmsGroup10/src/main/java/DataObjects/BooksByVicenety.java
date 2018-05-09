package DataObjects;

import Interfaces.DataObject;

public class BooksByVicenety implements DataObject {

    public CityAndBooks[] AllInVicenety;

    public BooksByVicenety(CityAndBooks[] all){
        this.AllInVicenety = all;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
