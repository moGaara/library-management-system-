package com.Library.libraryManagement.service;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.entitie.Book;
import com.Library.libraryManagement.exceptions.ConflictException;
import com.Library.libraryManagement.repository.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;




    public Map<String, String> getBook(int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty())throw new NoSuchElementException("Book with id: " + id + " is not found.");

        Book book = optionalBook.get();

        Map<String, String> bookMap = transformbookToMap(book);

        return bookMap;

    }

    public List<Map<String, String>> getAllBooks()
    {
        List<Book> bookList = bookRepository.findAll();

        if(bookList.isEmpty())throw new RuntimeException("There is no books in the library!");

        List<Map<String, String>> booksMapList = new ArrayList<>();


        for (Book book : bookList)
        {
            try
            {
                Map<String, String> bookMap = transformbookToMap(book);
                booksMapList.add(bookMap);
            }
            catch (Exception exception)
            {
                throw new RuntimeException("Failed to transform book data.", exception);
            }
        }

        return booksMapList;
    }



    @Transactional
    public void addBook(@Valid BookDTO bookDTO)
    {

        if(bookRepository.existsBooksByAuthor(bookDTO.getAuthor()) &&
                bookRepository.existsBooksByTitle(bookDTO.getTitle()))
            throw new ConflictException("A book with the same title and author already exists!");




        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setPublishYear(bookDTO.getPublishYear());

        try {
            bookRepository.save(book);
        }
        catch (DataAccessException exception)
        {
            throw new RuntimeException("Failed to save the book to the database.", exception);
        }


    }

    @Transactional
    public void deleteBook(int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty())throw new NoSuchElementException("Book with id: " + id + " is not found.");

        try {
            bookRepository.deleteById(id);
        }
        catch (DataAccessException exception)
        {
            throw new RuntimeException("Failed to delete the book from the database.", exception);
        }

    }


    @Transactional
    public void updateBook(@Valid BookDTO bookDTO, int id)
    {

        if(bookRepository.existsBooksByAuthor(bookDTO.getAuthor()) &&
                bookRepository.existsBooksByTitle(bookDTO.getTitle()))
            throw new ConflictException("A book with the same title and author already exists!");

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty())throw new NoSuchElementException("Book with id: " + id + " is not found.");

        Book book = optionalBook.get();



        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setPublishYear(bookDTO.getPublishYear());

        try {
            bookRepository.save(book);
        }
        catch (DataAccessException exception)
        {
            throw new RuntimeException("Failed to save the book to the database.", exception);
        }


    }

    private Map<String, String> transformbookToMap(Book book) {
        Map<String, String> bookMap = new HashMap<>();
        bookMap.put("Title", book.getTitle());
        bookMap.put("Author", book.getAuthor());
        bookMap.put("Publish Year", book.getPublishYear());

        return bookMap;
    }

}
