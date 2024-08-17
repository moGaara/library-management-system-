package com.Library.libraryManagement.service;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.entitie.Book;
import com.Library.libraryManagement.exceptions.ConflictException;
import com.Library.libraryManagement.repository.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;




    @Cacheable(value = "books", key = "#id")
    public Book getBook(int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty())throw new NoSuchElementException("Book with id: " + id + " is not found.");

        return optionalBook.get();

    }

    @Cacheable(value = "books")
    public List<Book> getAllBooks()
    {
        List<Book> bookList = bookRepository.findAll();

        if(bookList.isEmpty())throw new RuntimeException("There is no books in the library!");

        return bookList;


    }



    @Transactional
    @CachePut(value = "books", key = "#bookDTO.id")
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
    @CacheEvict(value = "books", key = "#id")
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
    @CachePut(value = "books", key = "#id")
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



}
