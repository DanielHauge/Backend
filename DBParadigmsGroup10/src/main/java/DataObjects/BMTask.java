package DataObjects;

import DataObjects.Json.DataSerializer;
import Interfaces.DataObject;

public class BMTask extends DataSerializer implements DataObject {

    public String task;
    public int time;

    public BMTask(String t, int time){
        this.task = t;
        this.time = time;
    }

}
