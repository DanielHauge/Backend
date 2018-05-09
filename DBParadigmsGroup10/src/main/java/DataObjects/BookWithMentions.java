package DataObjects;

import Interfaces.DataObject;

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
        return null;
    }
}
