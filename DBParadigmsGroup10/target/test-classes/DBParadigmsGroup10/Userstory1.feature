Feature: UserStory 1

  # As a user, I want to enter a city, and the application returns all book titles with with corresponding authors, that mentions this specific city, so that I can find the book that I want to read

  Scenario: Ballerup Example
    Given a user picks 2624341 as a city
    When the user ask for all book titles with corresponding authors that is mentioned in that city
    Then the user will get all books with corresponding authors that is mentioned in that city, which sums up to 1 books

  Scenario: Copenhagen Example
    Given a user picks 2618425 as a city
    When the user ask for all book titles with corresponding authors that is mentioned in that city
    Then the user will get all books with corresponding authors that is mentioned in that city, which sums up to 1915 books

