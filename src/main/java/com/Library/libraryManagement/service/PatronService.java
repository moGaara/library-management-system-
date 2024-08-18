package com.Library.libraryManagement.service;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.dto.PatronDTO;
import com.Library.libraryManagement.entitie.Book;
import com.Library.libraryManagement.entitie.Patron;
import com.Library.libraryManagement.exceptions.ConflictException;
import com.Library.libraryManagement.exceptions.PhoneNumberLenght;
import com.Library.libraryManagement.repository.PatronRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PatronService
{
    private final PatronRepository patronRepository;






    @Cacheable(value = "patrons", key = "#id")
    public Patron getPatron(int id)
    {
        Optional<Patron> optionalPatron = patronRepository.findById(id);

        if(optionalPatron.isEmpty())throw new NoSuchElementException("Patron with id: " + id + " is not found.");

        return optionalPatron.get();

    }



    @Cacheable(value = "patrons")
    public List<Patron> getAllPatrons()
    {
        List<Patron> patronList =patronRepository.findAll();

        if(patronList.isEmpty())throw new RuntimeException("There are no Patrons here!");

        return patronList;
    }



    @Transactional
    @CachePut(value = "patrons", key = "#patronDTO.email")
    public void addPatron(@Valid PatronDTO patronDTO)
    {


        Pattern gmailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$");


        if(patronRepository.existsByEmail(patronDTO.getEmail()))
            throw new ConflictException("A patron with the same email already exists!");
        if(patronRepository.existsByName(patronDTO.getName()))
            throw new ConflictException("A patron with the same name already exists!");

        if(patronRepository.existsByNumber(patronDTO.getNumber()))
            throw new ConflictException("A patron with the same number already exists!");

        Matcher matcher = gmailPattern.matcher(patronDTO.getEmail());
        if (!matcher.matches())
        {
            throw new IllegalArgumentException("Invalid Gmail address format!");
        }


        if(patronDTO.getNumber().length() != 11 )
            throw new PhoneNumberLenght("phone number length is not accepted please enter a 11 digit number.");


        Patron patron = new Patron();
        patron.setEmail(patronDTO.getEmail());
        patron.setName(patronDTO.getName());
        patron.setNumber(patronDTO.getNumber());

        try {
            patronRepository.save(patron);
        }
        catch (DataAccessException exception)
        {
            throw new RuntimeException("Failed to save the patron to the database.", exception);
        }


    }



    @Transactional
    @CachePut(value = "patrons", key = "#id")
    public void updatePatron(@Valid PatronDTO patronDTO, int id) {

        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isEmpty())
        {
            throw new NoSuchElementException("Patron with id: " + id + " is not found.");
        }

        Pattern gmailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$");

        Patron patron = optionalPatron.get();

        if(patronDTO.getNumber().length() != 11 )
            throw new PhoneNumberLenght("phone number length is not accepted please enter a 11 digit number.");

        if (patronDTO.getEmail() != null && !patronDTO.getEmail().isEmpty())
        {
            if (patronRepository.existsByEmail(patronDTO.getEmail()) &&
                    !patron.getEmail().equals(patronDTO.getEmail())) {
                throw new ConflictException("A patron with the same email already exists!");
            }

            Matcher matcher = gmailPattern.matcher(patronDTO.getEmail());
            if (!matcher.matches())
            {
                throw new IllegalArgumentException("Invalid Gmail address format!");
            }
            patron.setEmail(patronDTO.getEmail());
        }


        if (patronDTO.getName() != null && !patronDTO.getName().isEmpty()) {
            if (patronRepository.existsByName(patronDTO.getName()) &&
                    !patron.getName().equals(patronDTO.getName())) {
                throw new ConflictException("A patron with the same name already exists!");
            }
            patron.setName(patronDTO.getName());
        }


        if (patronDTO.getNumber() != null && !patronDTO.getNumber().isEmpty()) {
            if (patronRepository.existsByNumber(patronDTO.getNumber()) &&
                    !patron.getNumber().equals(patronDTO.getNumber())) {
                throw new ConflictException("A patron with the same number already exists!");
            }
            patron.setNumber(patronDTO.getNumber());
        }


        try {
            patronRepository.save(patron);
        } catch (DataAccessException exception) {
            throw new RuntimeException("Failed to save the patron to the database.", exception);
        }
    }



    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(int id)
    {
        Optional<Patron> optionalPatron = patronRepository.findById(id);

        if (optionalPatron.isEmpty())
        {
            throw new NoSuchElementException("Patron with id: " + id + " is not found.");
        }

        try {
            patronRepository.deleteById(id);
        } catch (DataAccessException exception) {
            throw new RuntimeException("Failed to delete the patron from the database.", exception);
        }
    }



}
