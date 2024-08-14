package com.Library.libraryManagement.service;


import com.Library.libraryManagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;


}
