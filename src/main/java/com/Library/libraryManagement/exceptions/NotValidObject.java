package com.Library.libraryManagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor

public class NotValidObject extends RuntimeException
{
    private final Set<String> errors;
}
