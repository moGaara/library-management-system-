package com.Library.libraryManagement.controller;

import com.Library.libraryManagement.dto.BorrowingDTO;
import com.Library.libraryManagement.entitie.Book;
import com.Library.libraryManagement.service.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingController
{
    private final BorrowingService borrowingService;


    @PostMapping("/borrow/{bookId}/patron/{patronId}/")
    public ResponseEntity<?> borrow(@PathVariable int bookId, @PathVariable int patronId,@Valid @RequestBody BorrowingDTO borrowingDTO)
    {
        try
        {
            borrowingDTO.setBookId(bookId);
            borrowingDTO.setPatronId(patronId);
            borrowingService.borrow(borrowingDTO);
            return ResponseEntity.ok().body("borrowing performed perfectly.");
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}/")
    public ResponseEntity<?> returnBook(@PathVariable int bookId, @PathVariable int patronId)
    {
        try
        {

            BorrowingDTO borrowingDTO = new BorrowingDTO(bookId,patronId,"");
            borrowingService.returnBook(borrowingDTO);
            return ResponseEntity.ok().body("returning book performed perfectly.");
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
