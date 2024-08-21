package com.Library.libraryManagement.service;

import com.Library.libraryManagement.dto.PatronDTO;
import com.Library.libraryManagement.entitie.Patron;
import com.Library.libraryManagement.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
class PatronServiceTest
{
    private final PatronService patronService;

    private  final PatronRepository patronRepository;


    @Test
    public void testAddPatron()
    {
        Patron patron = Patron.builder().email("test@gmail.com").number("012345678910").name("test").build();


    }


}