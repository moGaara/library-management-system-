package com.Library.libraryManagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO
{


    private int bookId;



    private int patronId;

    @NotNull(message = "returning date should not be null")
    @NotEmpty(message = "returning date should not be empty")
    private  String ReturnDate;
}
