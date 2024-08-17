package com.Library.libraryManagement.entitie;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.mapping.Set;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String title;


    private String author;



    private String publishYear;


    @OneToMany(mappedBy = "book")
    private List<BorrowingRecord> borrowingRecords;

}
