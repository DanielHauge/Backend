Feature: UserStory 1

  # As a user, I want to enter a city, and the application returns all book titles with with corresponding authors, that mentions this specific city, so that I can find the book that I want to read

  Scenario: Frederiksund Example
    Given a user picks 2621912 as a city
    When the user ask for all book titles with corresponding authors that is mentioned in that city
    Then the user will get all books with corresponding authors that is mentioned in that city, which is "Glimpses of Three Coasts", "Poems and Songs", "Pelle the Conqueror· Complete"


