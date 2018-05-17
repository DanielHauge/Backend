package DataObjects;

import DataObjects.Json.DataSerializer;
import Interfaces.DataObject;

public class Author extends DataSerializer implements DataObject {

    private final String title;

    public Author(String a){
        this.title = a;
    }

}
