package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class AllAuthors extends DataSerializer implements DataObject {

    public final String[] AllAuthors;

    public AllAuthors(String[] a){
        this.AllAuthors = a;
    }

}
