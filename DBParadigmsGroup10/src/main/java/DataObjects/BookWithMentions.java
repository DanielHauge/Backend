package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class BookWithMentions extends DataSerializer implements DataObject {

    public final String bookTitle;
    public final String author;
    public final int mentionCount;

    public BookWithMentions(String title, String author, int mentions){
        this.bookTitle = title;
        this.author = author;
        this.mentionCount = mentions;
    }

}
