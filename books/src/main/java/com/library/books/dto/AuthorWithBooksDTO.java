package com.library.books.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthorWithBooksDTO extends AuthorDTO {
    private List<BookDTO> books;
}
