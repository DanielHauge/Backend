# API Protocol

## 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

### Request - get all cities

Used for the autocomplete feature

`GET: /api/cities`

### Response 1

```json
[
  {
    "id": 12345,
    "cityName": "Copenhagen"
  },
  {
    "id": 12346,
    "cityName": "Helsingor"
  },
  {
    "id": 12347,
    "cityName": "Aarhus"
  },
  {..}
]
```

### Request - get all book titles for a city

`GET /api/books/bycity/{cityId}`

#### Response

```json
[
  {
    "bookTitle": "some book", 
    "author": "James Someone",
    "mentionCount": 3
  },
  {
    "bookTitle": "some book", 
    "author": "James Someone",
    "mentionCount": 4
  },
  {
    "bookTitle": "some book", 
    "author": "James Someone",
    "mentionCount": 5
  },
  {..}
]
```

## 2. Given a book title, your application plots all cities mentioned in this book onto a map

### Request - get all book titles and their ids

`GET: /api/books`

#### Response

```json
[
  {
    "bookId": 12345,
    "bookTitle": "Some book 1"
  },
  {
    "bookId": 12346,
    "bookTitle": "Some book 2"
  },
  {
    "bookId": 12347,
    "bookTitle": "Some book 3"
  },
  {..}
]
```

> We use this to let the user choose from all the books available with an autocomplete feature

### Request - Get all cities mentioned in a book with their coordinates

`GET: /api/cities/bybook/{bookId}`

#### Response

```json
[
  {
    "cityName": "Copenhagen",
    "latitude": 1.213312,
    "longitude": 1.21321
  },
  {
    "cityName": "Stockholm",
    "latitude": 1.213312,
    "longitude": 1.21321
  },
  {
    "cityName": "Amsterdam",
    "latitude": 1.213312,
    "longitude": 1.21321
  },
  {..}
]
```

> The response then for a specific book will be plotted onto a map

## 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

### Request - get all authors

`GET: /api/authors`

#### Response

```json
[
  "Author One",
  "Author Two",
  ...
]
```

### Request - get all books by an author

`GET: /api/books/byauthor/{authorName}`

#### Response

```json
{
  "authorName": "Shakespeare",
  "books": [
    {
      "bookId": 123,
      "bookTitle": "Book 1",
    },
    {
      "bookId": 124,
      "bookTitle": "Book 2",
    },
    {..}
  ]
}
```

### Request - get all cities mentioned in a book

`GET: /api/cities/bybook/{bookId}`

#### Response

```json
{
  "bookId": 123,
  "bookTitle": "Some Title",
  "cities": [
    {
      "cityName": "Copenhagen",
      "latitude": 1.213312,
      "longitude": 1.21321
    },
    {
      "cityName": "Stockholm",
      "latitude": 1.213312,
      "longitude": 1.21321
    },
    {
      "cityName": "Amsterdam",
      "latitude": 1.213312,
      "longitude": 1.21321
    },
    {..}
  ]
}
```

## 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation

### Request - get all books mentioning the city in vicinity of geolocation

`GET: /api/books/bylocation/{longitude}/{latitude}`

#### Response

```json
[
  {
    "cityName": "Copenhagen",
    "latitude": 1.213312,
    "longitude": 1.21321,
    "books": [
      {
        "bookId": 123,
        "bookTitle": "Book 1",
      },
      {
        "bookId": 124,
        "bookTitle": "Book 2",
      },
      {..}
  ]
  },
  {
    "cityName": "Brodnby",
    "latitude": 1.213312,
    "longitude": 1.21321,
        "books": [
      {
        "bookId": 123,
        "bookTitle": "Book 1",
      },
      {
        "bookId": 124,
        "bookTitle": "Book 2",
      },
      {..}
  },
  {..}
]
```
