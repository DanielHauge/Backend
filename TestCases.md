# Test cases

## Blackbox tests

TestCase | expected
:---------:|:----------:
book-1 | null
book1 | "Salute to Adventures" by "John Buchan"
mentionsbycity of 2624341 | one book with id of 16923
mentionsbybook of 1 | amount of id's in return = 94
allbooks | amount of books = 37227
allcities | amount of city names = 48377
allauthors | amount of authors = 14569
AllBooksByAuthor by "William Shakespeare" | amount of books = 21
Vicenity at 11.47:long 52.38:lat 100km | amount of cities = 112
Vicenity at 11.47:long 52.38:lat 50km | amount of cities = 25
Vicenity at 11.47:long 52.38:lat 20km | amount of cities = 5

## Whitebox tests

All functionality will have a responsetime below 1 seconds.

