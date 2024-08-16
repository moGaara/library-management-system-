package com.Library.libraryManagement.controller;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.dto.PatronDTO;
import com.Library.libraryManagement.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController
{

    private final PatronService patronService;

    @GetMapping("{id}")
    public ResponseEntity<?> getBook(@PathVariable int id)
    {
        try
        {
            return ResponseEntity.ok().body(patronService.getPatron(id));
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
            return ResponseEntity.ok().body(patronService.getAllPatrons());
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }


    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody PatronDTO patronDTO)
    {
        try
        {
            patronService.addPatron(patronDTO);
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
          patronService.deletePatron(id);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody PatronDTO patronDTO, @PathVariable int id)
    {
        try
        {
          patronService.updatePatron(patronDTO,id);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok().build();

    }

}
