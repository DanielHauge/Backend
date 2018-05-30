package DBParadigmsGroup10;

import DataAcessors.StubbedDataAccessor;
import DataObjects.*;
import Interfaces.DataAccessor;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.junit.Assert.assertTrue;


public class MyStepdefs {

    int cityid;
    int bookid;
    BooksByCity booksByCity;
    BookWithMentions bookWithMentions;
    ManyCitiesWithCords manyCitiesWithCords;
    BooksByAuthor booksByAuthor;
    CityByBook cityByBook;
    BooksByVicenety booksByVicenety;
    CityAndBooks cityAndBooks;
    String author;
    double lat;
    double lon;
    final DataAccessor DA = new StubbedDataAccessor();
    Object result;

    //-------------------------------------------------------------------------------------------------------------

    @Given("^a user picks (\\d+) as a city$")
    public void aUserPicksAsACity(int arg0) {
        this.cityid = arg0;
    }

    @When("^the user ask for all book titles with corresponding authors that is mentioned in that city$")
    public void theUserAskForAllBookTitlesWithCorrespondingAuthorsThatIsMentionedInThatCity() throws Throwable {
        this.booksByCity = DA.GetBooksByCity(this.cityid);
    }

    @Then("^the user will get all books with corresponding authors that is mentioned in that city, which is \"([^\"]*)\"$")
    public void theUserWillGetAllBooksWithCorrespondingAuthorsThatIsMentionedInThatCityWhichIs(String arg0) throws Throwable {
        this.bookWithMentions = this.booksByCity.books[0];
        assertTrue(this.bookWithMentions.bookTitle.equals(arg0));
    }

    //-------------------------------------------------------------------------------------------------------------

    @Given("^a user gives a bookid (\\d+)$")
    public void aUserGivesABookid(int arg0) throws Throwable {
        this.bookid = arg0;
    }

    @When("^the user ask for all cities mentioned in that book$")
    public void theUserAskForAllCitiesMentionedInThatBook() throws Throwable {
        this.manyCitiesWithCords = DA.GetCitiesBybook(this.bookid);
    }

    @Then("^the user will get a map with the city \"([^\"]*)\" from the book plotted on that map\\.$")
    public void theUserWillGetAMapWithTheCityFromTheBookPlottedOnThatMap(String arg0) throws Throwable {
        assertTrue(this.manyCitiesWithCords.cities[0].cityName.equals(arg0));

    }

    @Then("^the user will get a map with all cities mentioned in the book plotted on that map\\.$")
    public void theUserWillGetAMapWithAllCitiesMentionedInTheBookPlottedOnThatMap() {
        assertTrue(((ManyCitiesWithCords)result).cities.length == 13);
    }

    //-------------------------------------------------------------------------------------------------------------

    @Given("^a User gives a author name \"([^\"]*)\"$")
    public void aUserGivesAAuthorName(String arg0) throws Throwable {
        this.author = arg0;
    }

    @When("^the user ask for all cities which is mentioned in books by that author$")
    public void theUserAskForAllCitiesWhichIsMentionedInBooksByThatAuthor() throws Throwable {
        this.booksByAuthor = DA.GetBookByAuthor(this.author);
        this.cityByBook = DA.GetCityBybook(this.booksByAuthor.books[0].bookId);
    }

    @Then("^the user will get a map with all cities mentioned in the books by \"([^\"]*)\" plotted on that map which includes \"([^\"]*)\"\\.$")
    public void theUserWillGetAMapWithAllCitiesMentionedInTheBooksByPlottedOnThatMapWhichIncludes(String arg0, String arg1) throws Throwable {
        assertTrue(this.booksByAuthor.authorName.equals(arg0));
        assertTrue(this.cityByBook.cities[0].cityName.equals(arg1));
    }

    //-------------------------------------------------------------------------------------------------------------

    @Given("^a User gives a location (\\d+)\\.(\\d+),-(\\d+)\\.(\\d+)$")
    public void aUserGivesALocation(String arg0, String arg1, String arg2, String arg3) throws Throwable {
        this.lat = Double.parseDouble(String.format(arg0 + "." + arg1));
        this.lon = Double.parseDouble(String.format("-"+ arg2 + "." + arg3));
        double latReal = 17.02741;
        double lonReal = -61.78136;
        //assertThat(lat,is(latReal));
        //assertThat(lon,is(lonReal));
        assertTrue(lat == latReal);
        assertTrue(lon == lonReal);
    }

    @When("^the user ask for all books that mentions cities near that location in a radius of (\\d+) km$")
    public void theUserAskForAllBooksThatMentionsCitiesNearThatLocationInARadiusOfKm(int arg0) throws Throwable {
        this.booksByVicenety = DA.GetBooksInVicenety(this.lat,this.lon,arg0);
    }

    @Then("^the user gets all books mentioning a city near that location which is \"([^\"]*)\"$")
    public void theUserGetsAllBooksMentioningACityNearThatLocationWhichIs(String arg0) throws Throwable {
        assertTrue(this.booksByVicenety.Vicenety[0].books[0].bookTitle.equals(arg0));
    }

    //-------------------------------------------------------------------------------------------------------------

    @Then("^the user will get all books with corresponding authors that is mentioned in that city, which is \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void theUserWillGetAllBooksWithCorrespondingAuthorsThatIsMentionedInThatCityWhichIs(String arg0, String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }
}
