package com.library.books.mapper;

import org.springframework.stereotype.Component;
import com.library.books.dto.*;
import com.library.books.models.*;

@Component
public class BookMapper {
    public BookDTO toDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setGenre(book.getGenre());
        dto.setAuthorId(book.getAuthor().getId());
        return dto;
    }

    public BookWithAuthorDTO toBookWithAuthorDto(Book book) {
        BookWithAuthorDTO dto = new BookWithAuthorDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setGenre(book.getGenre());
        dto.setAuthorId(book.getAuthor().getId());
        
        AuthorDTO authorDto = new AuthorDTO();
        authorDto.setId(book.getAuthor().getId());
        authorDto.setFirstName(book.getAuthor().getFirstName());
        authorDto.setLastName(book.getAuthor().getLastName());
        authorDto.setEmail(book.getAuthor().getEmail());
        
        dto.setAuthor(authorDto);
        return dto;
    }

    public Book toEntity(BookDTO dto, Author author) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setGenre(dto.getGenre());
        book.setAuthor(author);
        return book;
    }
}

