package com.Library.libraryManagement.entitie;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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


    @NotNull(message = "title should not be null")
    @NotEmpty(message = "title should not be empty")
    private String title;

    @NotNull(message = "author should not be null")
    @NotEmpty(message = "author should not be empty")
    private String author;


    @NotNull(message = "publishYear should not be null")
    @NotEmpty(message = "publishYear should not be empty")
    private String publishYear;



}
