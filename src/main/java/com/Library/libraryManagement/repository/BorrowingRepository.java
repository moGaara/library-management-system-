package com.Library.libraryManagement.repository;

import com.Library.libraryManagement.entitie.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<BorrowingRecord,Integer>
{
    BorrowingRecord findBorrowingRecordByBookIdAndPatronId(int bookId, int patronId);
}
