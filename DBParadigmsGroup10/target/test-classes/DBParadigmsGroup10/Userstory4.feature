Feature: Userstory 4

  # As a user, i want to give a location and get all books which mentions a city in the vicinity of that location, so i can find a book that i want to read.

  Scenario: 10,30 Example
    Given a User gives a location 52.38,11.47
    When the user ask for all books that mentions cities near that location in a radius of 50 km
    Then the user gets all books mentioning a city near that location