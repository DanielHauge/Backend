package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class BooksByVicenety extends DataSerializer implements DataObject {

    public final CityAndBooks[] Vicenety;

    public BooksByVicenety(CityAndBooks[] all){
        this.Vicenety = all;
    }


}
