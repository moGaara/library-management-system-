package com.Library.libraryManagement.controller;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.entitie.Book;
import com.Library.libraryManagement.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController
{
    private final BookService bookService;

    @GetMapping("{id}")
    public ResponseEntity<?> getBook(@PathVariable int id)
    {
        try
        {
            Book book = bookService.getBook(id);
            Map<String, String> bookMap = transformbookToMap(book);
            return ResponseEntity.ok().body(bookMap);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllBooks()
    {
        try
        {
            List<Book> bookList = bookService.getAllBooks();
            List<Map<String, String>> booksMapList = new ArrayList<>();


            for (Book book : bookList)
            {

                Map<String, String> bookMap = transformbookToMap(book);
                booksMapList.add(bookMap);
            }
            return ResponseEntity.ok().body(booksMapList);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }



    }



    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO bookDTO)
    {
        try
        {
            bookService.addBook(bookDTO);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBook(@PathVariable int id)
    {
        try
        {
            bookService.deleteBook(id);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO, @PathVariable int id)
    {
        try
        {
            bookService.updateBook(bookDTO,id);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok().build();

    }



    private Map<String, String> transformbookToMap(Book book) {
        Map<String, String> bookMap = new HashMap<>();
        bookMap.put("Title", book.getTitle());
        bookMap.put("Author", book.getAuthor());
        bookMap.put("Publish Year", book.getPublishYear());

        return bookMap;
    }

}
