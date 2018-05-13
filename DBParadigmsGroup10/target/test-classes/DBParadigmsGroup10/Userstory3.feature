Feature: Userstory 3

  # As a user, i want to enter an authors name and get all books by that author and a map of with plots of all cities mentioned in any of the books so that i can find a book that i want to read.

  Scenario: William Shakespeare Example
    Given a User gives a author name "William Shakespeare"
    When the user ask for all cities which is mentioned in books by that author
    Then the user will get a map with all cities mentioned in those books plotted on that map.