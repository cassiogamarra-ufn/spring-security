package atos.academiajava.bookstore.controller;

import atos.academiajava.bookstore.dto.BookDto;
import atos.academiajava.bookstore.dto.MessageResponseDto;
import atos.academiajava.bookstore.entity.BookModel;
import atos.academiajava.bookstore.exception.NotFoundException;
import atos.academiajava.bookstore.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Define que apenas os ADMINS tem acesso a rota
    @PostMapping
    public MessageResponseDto create(@RequestBody @Valid BookDto dto) {
        return bookService.create(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid BookDto dto) throws NotFoundException {
        bookService.update(id, dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        bookService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public BookDto findById(@PathVariable Long id) throws NotFoundException {
        return bookService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')") //Define que usuários e admins tem acesso
    @GetMapping("/search")
    public BookDto search(@RequestParam(required = false) String description, @RequestParam(required = false) String author) throws NotFoundException {

        if (description != null && (!description.isBlank() || !description.isEmpty())) {
            return bookService.findByDescription(description);
        }

        if (author != null && (!author.isBlank() || !author.isEmpty())) {
            return bookService.findByAuthor(author);
        }
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/all")
    public List<BookModel> findAll() throws NotFoundException{
        return bookService.findAll();
    }
}
