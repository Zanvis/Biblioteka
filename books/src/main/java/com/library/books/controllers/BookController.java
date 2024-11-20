package com.library.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.books.dto.BookDTO;
import com.library.books.dto.BookWithAuthorDTO;
import com.library.books.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:8080")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Long id, 
            @Valid @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id);
        BookDTO updatedBook = bookService.updateBook(bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/with-author")
    public ResponseEntity<BookWithAuthorDTO> getBookByIdWithAuthor(@PathVariable Long id) {
        BookWithAuthorDTO bookWithAuthor = bookService.getBookByIdWithAuthor(id);
        return ResponseEntity.ok(bookWithAuthor);
    }
    @GetMapping("/with-authors")
    public ResponseEntity<List<BookWithAuthorDTO>> getAllBooksWithAuthors() {
        List<BookWithAuthorDTO> booksWithAuthors = bookService.getAllBooksWithAuthors();
        return ResponseEntity.ok(booksWithAuthors);
    }
}