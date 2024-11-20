package com.library.books.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.library.books.dto.*;
import com.library.books.mapper.*;
import com.library.books.models.*;
import com.library.books.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono książki o ID: " + id));
        return bookMapper.toDto(book);
    }

    public BookWithAuthorDTO getBookWithAuthor(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono książki o ID: " + id));
        return bookMapper.toBookWithAuthorDto(book);
    }


    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Autor nie został znaleziony"));
        
        Book book = bookMapper.toEntity(bookDTO, author);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        if (!bookRepository.existsById(bookDTO.getId())) {
            throw new RuntimeException("Książka nie została znaleziona");
        }

        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Autor nie został znaleziony"));

        Book book = bookMapper.toEntity(bookDTO, author);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Książka nie została znaleziona");
        }
        bookRepository.deleteById(id);
    }

    public List<BookWithAuthorDTO> getAllBooksWithAuthors() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
            .map(bookMapper::toBookWithAuthorDto)
            .collect(Collectors.toList());
    }

    public BookWithAuthorDTO getBookByIdWithAuthor(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Książka nie została znaleziona"));
        return bookMapper.toBookWithAuthorDto(book);
    }
}

