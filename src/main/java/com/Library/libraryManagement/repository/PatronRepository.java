package com.Library.libraryManagement.repository;

import com.Library.libraryManagement.entitie.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer>
{

}
