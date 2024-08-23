# Library Management System

A comprehensive Library Management System built with Spring Boot and Hibernate to manage library operations such as book lending, inventory management, and user authentication.

## API Documentation
The API allows interaction with the Library Management System programmatically. Below is a list of the available endpoints and their usage.

Get Book
- Get /api/books/{id}
- getting the details of specific book by sending the id.
- Response: it will retreive the title, author and puplish year.

Get all books
- Get /api/books
- getting the details of all books in the system
- Response: it will retreive the title, author and puplish year.


Add Book
- Post /api/books
- Add book to the library
- in the request send this book details as follow:
  {
    "title" : "Your Book Title",
    "author" : "The author",
    "publishYear" : "2020"
  }


Delete Book
- Delete /api/books/{id}
- delete a book by sending the id


update a book
-PUT /api/books/{id}
-Updating the details of a book by sending the ID
-  in the request send this book details as follow:
  {
    "title" : "Your Book Title",
    "author" : "The author",
    "publishYear" : "2020"
  }


Get Patron
- Get /api/patrons/{id}
- getting the details of specific patron by sending the id.
- Response: it will retreive the title, author and puplish year.

Get all Patrons
- Get /api/patrons
- getting the details of all patrons in the system
- Response: it will retreive the title, author and puplish year.


Add Patron
- Post /api/patrons
- Add patron to the system
- in the request send this patron details as follow:
  {
      "name" : "Your name",
    "email" : "test@gmail.com",
    "number" : "phone number"
  }


Delete Patron
- Delete /api/patrons/{id}
- delete a patron by sending the id


update a patron
-PUT /api/patrons/{id}
-Updating the details of a patron by sending the ID
-  in the request send this book details as follow:
{
      "name" : "Your name",
    "email" : "test@gmail.com",
    "number" : "phone number"
  }


  


