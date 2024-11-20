package com.library.books.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?:(?=.{17}$)97[89][-\\s]\\d{1,5}[-\\s]\\d{1,7}[-\\s]\\d{1,6}[-\\s]\\d|97[89]\\d{10}|(?=.{13}$)\\d{1,5}[-\\s]\\d{1,7}[-\\s]\\d{1,6}[-\\s]\\d|\\d{10}|\\d{9}[X])$",
            message = "Invalid ISBN format. Please use ISBN-10 or ISBN-13 format")
    private String isbn;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be after 1000")
    @Max(value = 9999, message = "Publication year must be a valid 4-digit year")
    private Integer publicationYear;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}
