package com.Library.libraryManagement.repository;

import com.Library.libraryManagement.entitie.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
    boolean existsBooksByAuthor(String author);
    boolean existsBooksByTitle(String title);
}
