package com.library.books.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 50, message = "Imię nie może być dłuższe niż 50 znaków")
    private String firstName;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(max = 50, message = "Nazwisko nie może być dłuższe niż 50 znaków")
    private String lastName;

    @Email(message = "Nieprawidłowy format email")
    private String email;

    private LocalDate birthDate;
}