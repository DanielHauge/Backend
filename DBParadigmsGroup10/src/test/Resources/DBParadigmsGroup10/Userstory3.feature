Feature: Userstory 3

  # As a user, i want to enter an authors name and get all books by that author and a map of with plots of all cities mentioned in any of the books so that i can find a book that i want to read.

  Scenario: Lewis Carroll Example
    Given a User gives a author name "Lewis Carroll"
    When the user ask for all cities which is mentioned in books by that author
    Then the user will get a map with all cities mentioned in the books by "Lewis Carroll" plotted on that map which includes "Falmouth".