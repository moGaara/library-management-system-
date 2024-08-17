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
public class BookDTO
{

    @NotNull(message = "title should not be null")
    @NotEmpty(message = "title should not be empty")
    String title;

    @NotNull(message = "author should not be null")
    @NotEmpty(message = "author should not be empty")
    String author;


    @NotNull(message = "publish year should not be null")
    @NotEmpty(message = "publish year should not be empty")
    String publishYear;

}
