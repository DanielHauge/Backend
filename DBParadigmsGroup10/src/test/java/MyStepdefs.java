import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class MyStepdefs {




    @Given("^a user picks \"([^\"]*)\" as a city$")
    public void aUserPicksAsACity(String arg0) {
        // Write code here that turns the phrase above into concrete actions

        System.out.println(arg0);
    }

    @When("^the user ask for all book titles with corresponding authors that is mentioned in that city$")
    public void theUserAskForAllBookTitlesWithCorrespondingAuthorsThatIsMentionedInThatCity() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the user will get all books with corresponding authors that is mentioned in that city, which sums up to (\\d+) books$")
    public void theUserWillGetAllBooksWithCorrespondingAuthorsThatIsMentionedInThatCityWhichSumsUpToBooks(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a user gives a book title \"([^\"]*)\"$")
    public void aUserGivesABookTitle(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the user ask for all cities mentioned in that book$")
    public void theUserAskForAllCitiesMentionedInThatBook() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^the user ask for all those cities plotted on a map$")
    public void theUserAskForAllThoseCitiesPlottedOnAMap() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the user will get a map with all cities mentioned in the book plotted on that map\\.$")
    public void theUserWillGetAMapWithAllCitiesMentionedInTheBookPlottedOnThatMap() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a User gives a author name \"([^\"]*)\"$")
    public void aUserGivesAAuthorName(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the user ask for all books by that author$")
    public void theUserAskForAllBooksByThatAuthor() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^the user ask for all cities mentioned in those books$")
    public void theUserAskForAllCitiesMentionedInThoseBooks() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the user will get a map with all cities mentioned in those books plotted on that map\\.$")
    public void theUserWillGetAMapWithAllCitiesMentionedInThoseBooksPlottedOnThatMap() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a User gives a location \"([^\"]*)\"$")
    public void aUserGivesALocation(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the user ask for all books that mentions cities near that location$")
    public void theUserAskForAllBooksThatMentionsCitiesNearThatLocation() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the user gets all books mentioning a city near that location$")
    public void theUserGetsAllBooksMentioningACityNearThatLocation() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
