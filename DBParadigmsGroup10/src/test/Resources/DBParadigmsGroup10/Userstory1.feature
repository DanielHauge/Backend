Feature: UserStory 1

  # As a user, I want to enter a city, and the application returns all book titles with with corresponding authors, that mentions this specific city, so that I can find the book that I want to read

  Scenario: Falmouth Example
    Given a user picks 3576260 as a city
    When the user ask for all book titles with corresponding authors that is mentioned in that city
    Then the user will get all books with corresponding authors that is mentioned in that city, which is "The Hunting of the Snark"


