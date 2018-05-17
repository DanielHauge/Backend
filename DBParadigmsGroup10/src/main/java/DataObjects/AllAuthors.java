package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class AllAuthors extends DataSerializer implements DataObject {

    public final Author[] AllAuthors;

    public AllAuthors(Author[] a){
        this.AllAuthors = a;
    }

}
