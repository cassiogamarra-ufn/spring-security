package atos.academiajava.bookstore.service.book;

import atos.academiajava.bookstore.dto.BookDto;
import atos.academiajava.bookstore.dto.MessageResponseDto;
import atos.academiajava.bookstore.entity.BookModel;
import atos.academiajava.bookstore.exception.NotFoundException;

import java.util.List;

public interface BookService {

    BookDto findById(Long id) throws NotFoundException;
    List<BookModel> findAll() throws NotFoundException;
    BookDto findByDescription(String description) throws NotFoundException;
    BookDto findByAuthor(String author) throws NotFoundException;
    MessageResponseDto create(BookDto dto);
    void update(long id, BookDto dto) throws NotFoundException;
    void delete(long id) throws NotFoundException;
}
