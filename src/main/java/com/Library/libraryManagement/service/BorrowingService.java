package com.Library.libraryManagement.service;

import com.Library.libraryManagement.dto.BorrowingDTO;
import com.Library.libraryManagement.entitie.Book;
import com.Library.libraryManagement.entitie.BorrowingRecord;
import com.Library.libraryManagement.entitie.Patron;
import com.Library.libraryManagement.repository.BorrowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BorrowingService
{

    private final BorrowingRepository borrowingRepository;
    private final BookService bookService;
    private final PatronService patronService;


    public BorrowingRecord borrow(BorrowingDTO borrowingDTO)
    {
        try
        {



            Patron patron = patronService.getPatron(borrowingDTO.getPatronId());



            Book book = bookService.getBook(borrowingDTO.getBookId());



            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            LocalDate returnDate = LocalDate.parse(borrowingDTO.getReturnDate());
            borrowingRecord.setReturnDate(returnDate);
            borrowingRecord.setBorrowDate(LocalDate.now());
            borrowingRecord.setStatus("Borrowed");

            borrowingRepository.save(borrowingRecord);
            return borrowingRecord;

        }
        catch (NoSuchElementException e)
        {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Borrowing operation failed: " + e.getMessage(), e);
        }
        catch (DateTimeParseException e)
        {

            System.err.println("Error: Invalid date format for return date: " + borrowingDTO.getReturnDate());
            throw new RuntimeException("Borrowing operation failed: Invalid date format for return date.", e);
        }
        catch (Exception e)
        {

            System.err.println("Error: An unexpected error occurred during the borrowing operation.");
            throw new RuntimeException("Borrowing operation failed due to an unexpected error.", e);
        }
    }

    public void returnBook(BorrowingDTO borrowingDTO)
    {

        BorrowingRecord borrowingRecord = borrowingRepository.findBorrowingRecordByBookIdAndPatronId(
                borrowingDTO.getBookId(), borrowingDTO.getPatronId());

        if(borrowingRecord.getReturnDate().isAfter(LocalDate.now()))borrowingRecord.setStatus("Overdue");

        borrowingRecord.setStatus("Returned");
        borrowingRecord.setReturnDate(LocalDate.now());

        borrowingRepository.save(borrowingRecord);

    }
}
