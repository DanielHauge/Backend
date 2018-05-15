Feature: Userstory 2

  # As a user, i want to enter a book title, and the application plots all cities mentioned in this book onto a map, so that I can get a visualization of books onto the map.

  Scenario: The Hunting of the Snark Example
    Given a user gives a bookid 5
    When the user ask for all cities mentioned in that book
    Then the user will get a map with the city "Falmouth" from the book plotted on that map.