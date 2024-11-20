package com.library.books.mapper;

import org.springframework.stereotype.Component;
import com.library.books.dto.*;
import com.library.books.models.*;

import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    public AuthorDTO toDto(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setEmail(author.getEmail());
        dto.setBirthDate(author.getBirthDate());
        return dto;
    }

    public AuthorWithBooksDTO toAuthorWithBooksDto(Author author) {
        AuthorWithBooksDTO dto = new AuthorWithBooksDTO();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setEmail(author.getEmail());
        dto.setBirthDate(author.getBirthDate());
        dto.setBooks(author.getBooks().stream()
            .map(this::toBookDto)
            .collect(Collectors.toList()));
        return dto;
    }

    public Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setEmail(dto.getEmail());
        author.setBirthDate(dto.getBirthDate());
        return author;
    }

    private BookDTO toBookDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setGenre(book.getGenre());
        dto.setAuthorId(book.getAuthor().getId());
        return dto;
    }
}