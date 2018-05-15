package DBParadigmsGroup10;

import DataAcessors.RedisDataAcessor;
import DataObjects.*;
import Interfaces.DataAccessor;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;


public class MyStepdefs {

    int cityid;
    int bookid;
    double lat;
    double lon;
    String author;
    DataAccessor DA = new RedisDataAcessor(System.getenv("DBIP"));
    Object result;

    @Given("^a user picks (\\d+) as a city$")
    public void aUserPicksAsACity(int arg0) {
        this.cityid = arg0;
    }

    @When("^the user ask for all book titles with corresponding authors that is mentioned in that city$")
    public void theUserAskForAllBookTitlesWithCorrespondingAuthorsThatIsMentionedInThatCity() {
        result = DA.GetBooksByCity(this.cityid);
    }

    @Then("^the user will get all books with corresponding authors that is mentioned in that city, which sums up to (\\d+) books$")
    public void theUserWillGetAllBooksWithCorrespondingAuthorsThatIsMentionedInThatCityWhichSumsUpToBooks(int arg0) {
        assertTrue(((BooksByCity)result).books.length== arg0);
    }

    @Given("^a user gives a bookid (\\d+)$")
    public void aUserGivesABookid(int arg0){
        this.bookid = arg0;
    }

    @When("^the user ask for all cities mentioned in that book$")
    public void theUserAskForAllCitiesMentionedInThatBook() {
        this.result = DA.GetCitiesBybook(bookid);
    }

    @Then("^the user will get a map with all cities mentioned in the book plotted on that map\\.$")
    public void theUserWillGetAMapWithAllCitiesMentionedInTheBookPlottedOnThatMap() {
        assertTrue(((ManyCitiesWithCords)result).cities.length == 13);
    }

    @Given("^a User gives a author name \"([^\"]*)\"$")
    public void aUserGivesAAuthorName(String arg0) {
        this.author = arg0;
    }

    @When("^the user ask for all cities which is mentioned in books by that author$")
    public void theUserAskForAllCitiesWhichIsMentionedInBooksByThatAuthor(){
        this.result = DA.GetBookByAuthor(this.author);
    }


    @Then("^the user will get a map with all cities mentioned in those books plotted on that map\\.$")
    public void theUserWillGetAMapWithAllCitiesMentionedInThoseBooksPlottedOnThatMap() {
        assertTrue(((BooksByAuthor)result).books.length==21);
    }



    @When("^the user ask for all books that mentions cities near that location in a radius of (\\d+) km$")
    public void theUserAskForAllBooksThatMentionsCitiesNearThatLocationInARadiusOfKm(int arg0){
        this.result = DA.GetBooksInVicenety(this.lat, this.lon, arg0);
    }

    @Then("^the user gets all books mentioning a city near that location$")
    public void theUserGetsAllBooksMentioningACityNearThatLocation() {
        assertTrue(((BooksByVicenety)result).Vicenety.length==25);
    }


    @Given("^a User gives a location (\\d+)\\.(\\d+),(\\d+)\\.(\\d+)$")
    public void aUserGivesALocation(int arg0, int arg1, int arg2, int arg3){
        this.lat = Double.parseDouble(Integer.toString(arg0)+"."+Integer.toString(arg1));
        this.lon = Double.parseDouble(Integer.toString(arg2)+"."+Integer.toString(arg3));
    }

    @Then("^the user will get all books with corresponding authors that is mentioned in that city, which is \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void theUserWillGetAllBooksWithCorrespondingAuthorsThatIsMentionedInThatCityWhichIs(String arg0, String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }
}
