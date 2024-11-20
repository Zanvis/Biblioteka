package com.library.books.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.books.dto.*;
import com.library.books.service.AuthorService;
import com.library.books.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({AuthorController.class, BookController.class})
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthorDTO createTestAuthorDTO() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setFirstName("John");
        authorDTO.setLastName("Doe");
        authorDTO.setEmail("john@example.com");
        authorDTO.setBirthDate(LocalDate.of(1980, 1, 1));
        return authorDTO;
    }

    private BookDTO createTestBookDTO() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Test Book");
        bookDTO.setIsbn("1234567890");
        bookDTO.setPublicationYear(2024);
        bookDTO.setGenre("Fantasy");
        bookDTO.setAuthorId(1L);
        return bookDTO;
    }

    @Test
    void getAllAuthors_ShouldReturnListOfAuthors() throws Exception {
        List<AuthorDTO> authors = Arrays.asList(createTestAuthorDTO());
        when(authorService.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(authors)));
    }

    @Test
    void getAuthorById_ShouldReturnAuthor() throws Exception {
        AuthorDTO author = createTestAuthorDTO();
        when(authorService.getAuthorById(1L)).thenReturn(author);

        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(author)));
    }

    @Test
    void createAuthor_ShouldReturnCreatedAuthor() throws Exception {
        AuthorDTO authorDTO = createTestAuthorDTO();
        when(authorService.createAuthor(any(AuthorDTO.class))).thenReturn(authorDTO);

        mockMvc.perform(post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(authorDTO)));
    }

    @Test
    void updateAuthor_ShouldReturnUpdatedAuthor() throws Exception {
        AuthorDTO authorDTO = createTestAuthorDTO();
        when(authorService.updateAuthor(eq(1L), any(AuthorDTO.class))).thenReturn(authorDTO);

        mockMvc.perform(put("/api/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(authorDTO)));
    }

    @Test
    void deleteAuthor_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        List<BookDTO> books = Arrays.asList(createTestBookDTO());
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    void getBookById_ShouldReturnBook() throws Exception {
        BookDTO book = createTestBookDTO();
        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        BookDTO bookDTO = createTestBookDTO();
        when(bookService.createBook(any(BookDTO.class))).thenReturn(bookDTO);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookDTO)));
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() throws Exception {
        BookDTO bookDTO = createTestBookDTO();
        when(bookService.updateBook(any(BookDTO.class))).thenReturn(bookDTO);

        mockMvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookDTO)));
    }

    @Test
    void deleteBook_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllBooksWithAuthors_ShouldReturnBooksWithAuthors() throws Exception {
        BookWithAuthorDTO bookWithAuthorDTO = new BookWithAuthorDTO();
        List<BookWithAuthorDTO> booksWithAuthors = Arrays.asList(bookWithAuthorDTO);
        when(bookService.getAllBooksWithAuthors()).thenReturn(booksWithAuthors);

        mockMvc.perform(get("/api/books/with-authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(booksWithAuthors)));
    }

    @Test
    void getBookByIdWithAuthor_ShouldReturnBookWithAuthor() throws Exception {
        BookWithAuthorDTO bookWithAuthorDTO = new BookWithAuthorDTO();
        when(bookService.getBookByIdWithAuthor(1L)).thenReturn(bookWithAuthorDTO);

        mockMvc.perform(get("/api/books/1/with-author"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookWithAuthorDTO)));
    }

    @Test
    void getAllAuthorsWithBooks_ShouldReturnAuthorsWithBooks() throws Exception {
        AuthorWithBooksDTO authorWithBooksDTO = new AuthorWithBooksDTO();
        List<AuthorWithBooksDTO> authorsWithBooks = Arrays.asList(authorWithBooksDTO);
        when(authorService.getAllAuthorsWithBooks()).thenReturn(authorsWithBooks);

        mockMvc.perform(get("/api/authors/with-books"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(authorsWithBooks)));
    }

    @Test
    void getAuthorByIdWithBooks_ShouldReturnAuthorWithBooks() throws Exception {
        AuthorWithBooksDTO authorWithBooksDTO = new AuthorWithBooksDTO();
        when(authorService.getAuthorByIdWithBooks(1L)).thenReturn(authorWithBooksDTO);

        mockMvc.perform(get("/api/authors/1/with-books"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(authorWithBooksDTO)));
    }
}