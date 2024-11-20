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
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;

    @Transactional
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        if (authorRepository.existsByEmail(authorDTO.getEmail())) {
            throw new IllegalArgumentException("Email jest już zajęty");
        }
        Author author = authorMapper.toEntity(authorDTO);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono autora o ID: " + id));
        return authorMapper.toDto(author);
    }

    public AuthorWithBooksDTO getAuthorWithBooks(Long id) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono autora o ID: " + id));
        return authorMapper.toAuthorWithBooksDto(author);
    }

    @Transactional
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono autora o ID: " + id));
        
        if (!existingAuthor.getEmail().equals(authorDTO.getEmail()) &&
            authorRepository.existsByEmail(authorDTO.getEmail())) {
            throw new IllegalArgumentException("Email jest już zajęty");
        }
        
        existingAuthor.setFirstName(authorDTO.getFirstName());
        existingAuthor.setLastName(authorDTO.getLastName());
        existingAuthor.setEmail(authorDTO.getEmail());
        existingAuthor.setBirthDate(authorDTO.getBirthDate());
        
        return authorMapper.toDto(authorRepository.save(existingAuthor));
    }

    @Transactional
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Nie znaleziono autora o ID: " + id);
        }
        authorRepository.deleteById(id);
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
            .map(authorMapper::toDto)
            .collect(Collectors.toList());
    }
    
    public List<AuthorWithBooksDTO> getAllAuthorsWithBooks() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
            .map(authorMapper::toAuthorWithBooksDto)
            .collect(Collectors.toList());
    }

    public AuthorWithBooksDTO getAuthorByIdWithBooks(Long id) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor nie został znaleziony"));
        return authorMapper.toAuthorWithBooksDto(author);
    }
}

