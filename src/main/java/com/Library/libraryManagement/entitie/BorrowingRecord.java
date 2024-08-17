package com.Library.libraryManagement.entitie;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;




    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;



}
