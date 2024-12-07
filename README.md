# 📚 System Zarządzania Biblioteką 

## 🌟 Opis Projektu

Nowoczesny system zarządzania biblioteką oparty na architekturze REST API, umożliwiający kompleksowe zarządzanie autorami i ich publikacjami.

## ✨ Kluczowe Funkcje

### 👥 Zarządzanie Autorami
- Dodawanie nowych autorów
- Aktualizacja danych autorów
- Przeglądanie listy autorów
- Szczegółowe informacje o autorach
- Pełny widok dorobku autorów

### 📖 Zarządzanie Książkami
- Dodawanie nowych książek
- Aktualizacja informacji o książkach
- Przeglądanie katalogu książek
- Szczegółowe informacje o publikacjach
- Powiązanie książek z autorami

## 🚀 Endpointy API

### Autorzy `/api/authors`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| `GET` | `/api/authors` | Lista autorów |
| `GET` | `/api/authors/with-books` | Autorzy z ich książkami |
| `GET` | `/api/authors/{id}` | Szczegóły autora |
| `GET` | `/api/authors/{id}/with-books` | Autor ze swoimi książkami |
| `POST` | `/api/authors` | Dodaj autora |
| `PUT` | `/api/authors/{id}` | Aktualizuj autora |
| `DELETE` | `/api/authors/{id}` | Usuń autora |

### Książki `/api/books`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| `GET` | `/api/books` | Lista książek |
| `GET` | `/api/books/with-authors` | Książki z autorami |
| `GET` | `/api/books/{id}` | Szczegóły książki |
| `GET` | `/api/books/{id}/with-author` | Książka z autorem |
| `POST` | `/api/books` | Dodaj książkę |
| `PUT` | `/api/books/{id}` | Aktualizuj książkę |
| `DELETE` | `/api/books/{id}` | Usuń książkę |

## 🛠️ Technologie
- **Backend:** Spring Boot
- **Baza danych:** MySQL
- **Testowanie:** 
  - JUnit (testy jednostkowe)
  - Postman (testowanie API)

## 📦 Wymagania
- Java 11+
- MySQL
- XAMPP (opcjonalnie)

## 🔧 Konfiguracja Bazy Danych
```sql
CREATE DATABASE library_management;
USE library_management;

-- Tworzenie tabel
CREATE TABLE author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    birth_date DATE
);

CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publication_year INT,
    genre VARCHAR(50),
    author_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);
```

## 🌈 Przykładowe Dane
```sql
-- Dodawanie przykładowych autorów
INSERT INTO author (first_name, last_name, email, birth_date) 
VALUES 
    ('Jan', 'Kowalski', 'jan.kowalski@example.com', '1980-05-15'),
    ('Anna', 'Nowak', 'anna.nowak@example.com', '1990-12-22');

-- Dodawanie przykładowych książek
INSERT INTO book (title, isbn, publication_year, genre, author_id) 
VALUES 
    ('Przygody Informatyka', '9788301234567', 2020, 'Informatyka', 1),
    ('Świat Programowania', '9788309876543', 2019, 'Technologia', 2);
```

## 🧪 Testowanie API za pomocą Postmana
### 📥 Import Kolekcji Testów
W repozytorium znajduje się plik `library-api-collection.json`, który można bezpośrednio zaimportować do Postmana:

1. Otwórz Postmana
2. Kliknij `Import` w lewym górnym rogu
3. Przeciągnij plik `library-api-collection.json`, wklej jego zawartość lub wybierz go ręcznie

### 📋 Dostępne Zestawy Testów
#### Testy Autorów
- `Get All Authors`: Pobiera listę wszystkich autorów
- `Get Author by ID`: Pobiera szczegóły konkretnego autora
- `Create Author`: Tworzy nowego autora
- `Update Author`: Aktualizuje dane autora
- `Delete Author`: Usuwa autora

#### Testy Książek
- `Get All Books`: Pobiera listę wszystkich książek
- `Get Book by ID`: Pobiera szczegóły konkretnej książki
- `Create Book`: Tworzy nową książkę
- `Update Book`: Aktualizuje dane książki
- `Delete Book`: Usuwa książkę
- `Get Books with Authors`: Pobiera książki wraz z informacjami o autorach
- `Get Book by ID with Author`: Pobiera szczegóły książki wraz z informacjami o autorze

### ✅ Automatyczne Testy
Każdy endpoint posiada wbudowane testy sprawdzające:
- Poprawność kodu odpowiedzi HTTP
- Strukturę zwracanych danych
- Zgodność danych z oczekiwaniami

### 🚀 Uruchomienie Testów
1. Upewnij się, że aplikacja jest uruchomiona lokalnie
2. W Postmanie wybierz kolekcję `Library API Tests`
3. Kliknij `Run Collection` aby uruchomić wszystkie testy
4. Przejrzyj wyniki testów w panelu uruchomieniowym