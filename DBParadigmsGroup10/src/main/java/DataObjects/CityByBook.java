package DataObjects;

import Interfaces.DataObject;

public class CityByBook implements DataObject {


    public int bookId;
    public String bookTitle;
    public CityWithCords[] cities;

    public CityByBook(int id, String title, CityWithCords[] cits){
        this.bookId = id;
        this.bookTitle = title;
        this.cities = cits;
    }

    @Override
    public String SerializeToJson() {
        return null;
    }
}
