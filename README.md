# Library Management System

A comprehensive Library Management System built with Spring Boot and Hibernate to manage library operations such as book lending, inventory management, and user authentication.

## API Documentation
The API allows interaction with the Library Management System programmatically. Below is a list of the available endpoints and their usage.


Register
- POST /api/register
- Note: the system accepts only gmail accounts.
- Register a new user to the system by sending
  {
      "firstName" : "test",
      "lastName" : "test",
      "email" : "test@gmail.com",
      "password" : "pass"
  }
- Response: you will get a JWT token that you will need to send any other endpoint to be able to use them.

Login
- POST api/login
- Log in to the system using email and password.
- Response: you will get a JWT token that you will need to send any other endpoint to be able to use them.


Logout
- POST api/logout
- Logout by sending the JWT token.


Get Book
- GET /api/books/{id}
- getting the details of specific book by sending the id.
- Response: it will retreive the title, author and puplish year.

Get all books
- GET /api/books
- getting the details of all books in the system.
- Response: it will retreive the title, author and puplish year.


Add Book
- POST /api/books
- Add book to the library.
- in the request send this book details as follow:
  {
    "title" : "Your Book Title",
    "author" : "The author",
    "publishYear" : "2020"
  }


Delete Book
- DELETE /api/books/{id}
- delete a book by sending the id.


update a book
-PUT /api/books/{id}
-Updating the details of a book by sending the ID.
-  in the request send this book details as follow:
  {
    "title" : "Your Book Title",
    "author" : "The author",
    "publishYear" : "2020"
  }


Get Patron
- GET /api/patrons/{id}
- getting the details of specific patron by sending the id.
- Response: it will retreive the title, author and puplish year.

Get all Patrons
- Get /api/patrons
- getting the details of all patrons in the system.
- Response: it will retreive the title, author and puplish year.


Add Patron
- POST /api/patrons
- Add patron to the system.
- in the request send this patron details as follow:
  {
      "name" : "Your name",
    "email" : "test@gmail.com",
    "number" : "phone number"
  }


Delete Patron
- DELETE /api/patrons/{id}
- delete a patron by sending the id.


Update a patron
-PUT /api/patrons/{id}
-Updating the details of a patron by sending the ID.
-  in the request send this book details as follow:
{
      "name" : "Your name",
    "email" : "test@gmail.com",
    "number" : "phone number"
  }


Borrow a Book
-POST /api/borrow/{bookId}/patron/{patronId}
- Borrow  book by sending book and patrons IDs as path variables and returning date as follow:
  {
    "returnDate" : "2024-08-25"
  }

Return a Book
-  PUT /api/return/{bookId}/patron/{patronId}
-  Record the return of a borrowed book by a patron.


