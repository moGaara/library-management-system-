package com.Library.libraryManagement.controller;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok().body(bookService.getBook(id));
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
            return ResponseEntity.ok().body(bookService.getAllBooks());
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }



    }



    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO)
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

}
