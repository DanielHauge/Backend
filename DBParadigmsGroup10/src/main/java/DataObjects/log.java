package DataObjects;

import DataObjects.Json.DataSerializer;
import Interfaces.DataObject;

public class log extends DataSerializer implements DataObject {
    public final String database;
    public final String query;
    public final BMTask[] tasks;
    public final int inall;

    public log(String db, String q, BMTask[] tasks, int inall){
        this.database = db;
        this.query = q;
        this.tasks = tasks;
        this.inall = inall;
    }


}
