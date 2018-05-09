package DataObjects;

import Interfaces.DataObject;

public class AllAuthors implements DataObject {

    public String[] allAuthors;

    public AllAuthors(String[] a){
        this.allAuthors = a;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
