package DataObjects;

import DataObjects.Json.DataSerializer;
import Interfaces.DataObject;

public class log extends DataSerializer implements DataObject {
    public String database;
    public String query;
    public BMTask[] tasks;
    public int inall;

    public log(String db, String q, BMTask[] tasks, int inall){
        this.database = db;
        this.query = q;
        this.tasks = tasks;
        this.inall = inall;
    }


}
