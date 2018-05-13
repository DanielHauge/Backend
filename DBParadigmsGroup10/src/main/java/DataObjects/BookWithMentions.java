package DataObjects;

import Interfaces.DataObject;
import DataObjects.Json.DataSerializer;

public class BookWithMentions extends DataSerializer implements DataObject {

    private final String bookTitle;
    private final String author;
    private final int mentionCount;

    public BookWithMentions(String title, String author, int mentions){
        this.bookTitle = title;
        this.author = author;
        this.mentionCount = mentions;
    }

}
