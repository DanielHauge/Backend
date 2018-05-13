package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class CityByBook extends DataSerializer implements DataObject {


    private final int bookId;
    private final String bookTitle;
    public final CityWithCords[] cities;

    public CityByBook(int id, String title, CityWithCords[] cits){
        this.bookId = id;
        this.bookTitle = title;
        this.cities = cits;
    }

}
