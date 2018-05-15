Feature: Userstory 4

  # As a user, i want to give a location and get all books which mentions a city in the vicinity of that location, so i can find a book that i want to read.

  Scenario: 17.02741,-61.78136 Example
    Given a User gives a location 17.02741,-61.78136
    When the user ask for all books that mentions cities near that location in a radius of 10 km
    Then the user gets all books mentioning a city near that location which is "The Hunting of the Snark"