package DataObjects;

import DataObjects.Json.DataSerializer;
import Interfaces.DataObject;

public class Fulllog extends DataSerializer implements DataObject {

    public final log[] logs;

    public Fulllog(log[] l){
        this.logs = l;
    }

}
