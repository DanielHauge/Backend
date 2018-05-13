Feature: Userstory 2

  # As a user, i want to enter a book title, and the application plots all cities mentioned in this book onto a map, so that I can get a visualization of books onto the map.

  Scenario: Salute to Adventurers Example
    Given a user gives a bookid 1
    When the user ask for all cities mentioned in that book
    Then the user will get a map with all cities mentioned in the book plotted on that map.