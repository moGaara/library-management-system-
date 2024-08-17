package com.Library.libraryManagement.controller;


import com.Library.libraryManagement.dto.BookDTO;
import com.Library.libraryManagement.dto.PatronDTO;
import com.Library.libraryManagement.entitie.Patron;
import com.Library.libraryManagement.service.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController
{

    private final PatronService patronService;

    @GetMapping("{id}")
    public ResponseEntity<?> getPatron(@PathVariable int id)
    {
        try
        {
            Patron patron = patronService.getPatron(id);
            Map<String, String> patronMap = transformPatronToMap(patron);
            return ResponseEntity.ok().body(patronMap);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllPatrons()
    {
        try
        {
            List<Patron> patronList = patronService.getAllPatrons();
            List<Map<String, String>> patronMapList = new ArrayList<>();


            for (Patron patron : patronList)
            {

                Map<String, String> patronToMap = transformPatronToMap(patron);
                patronMapList.add(patronToMap);

            }
            return ResponseEntity.ok().body(patronMapList);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }


    @PostMapping
    public ResponseEntity<?> addPatron(@Valid@RequestBody PatronDTO patronDTO)
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
    public  ResponseEntity<?> deletePatron(@PathVariable int id)
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
    public ResponseEntity<?> updatePatron(@Valid@RequestBody PatronDTO patronDTO, @PathVariable int id)
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

    private Map<String, String> transformPatronToMap(Patron patron)
    {
        Map<String, String> bookMap = new HashMap<>();
        bookMap.put("Name", patron.getName());
        bookMap.put("Email", patron.getEmail());
        bookMap.put("Number", patron.getNumber());

        return bookMap;
    }

}
