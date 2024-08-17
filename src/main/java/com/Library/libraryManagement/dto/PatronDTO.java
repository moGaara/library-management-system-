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
public class PatronDTO
{

    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be empty")
    String name;

    @NotNull(message = "email should not be null")
    @NotEmpty(message = "email should not be empty")
    String email;

    @NotNull(message = "number should not be null")
    @NotEmpty(message = "number should not be empty")
    String number;
}
