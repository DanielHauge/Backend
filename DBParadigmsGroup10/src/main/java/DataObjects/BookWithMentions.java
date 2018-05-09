package DataObjects;

import Interfaces.DataObject;
import com.google.gson.Gson;

public class BookWithMentions implements DataObject {

    public String bookTitle;
    public String author;
    public int mentionCount;

    public BookWithMentions(String title, String author, int mentions){
        this.bookTitle = title;
        this.author = author;
        this.mentionCount = mentions;
    }

    @Override
    public String SerializeToJson() {
        return new Gson().toJson(this);
    }
}
