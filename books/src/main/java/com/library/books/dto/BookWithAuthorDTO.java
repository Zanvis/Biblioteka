package com.library.books.dto;

import lombok.Data;

@Data
public class BookWithAuthorDTO extends BookDTO {
    private AuthorDTO author;
}