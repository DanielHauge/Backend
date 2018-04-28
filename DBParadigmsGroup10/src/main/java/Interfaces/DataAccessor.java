package Interfaces;

public interface DataAccessor {


    /**
     * @param city
     * @return
     */
    Book[] GetBooksByCityMention(String city);

    /**
     * @param title
     * @return
     */
    Book[] GetBooksByTitle(String title);

    /**
     *
     * @param title
     * @return
     */
    Book[] GetBooksByAuthor(String title);

    /**
     *
     * @param title
     * @return
     */
    MapData MapDataByTittle(String title);

    /**
     *
     * @param author
     * @return
     */
    MapData MapDataByAuthor(String author);

    /**
     *
     * @param p
     * @return
     */
    Book[] GetBooksByVicinety(Point p);

    /**
     *
     * @param city
     * @return
     */
    int GetMentionsInAllBooks(String city);

    /**
     *
     * @return
     */
    MapData GetMapPlotWithAmountOfMentionsForAllBooks();





}
