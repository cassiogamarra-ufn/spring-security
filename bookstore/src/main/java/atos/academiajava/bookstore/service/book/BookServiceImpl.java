package atos.academiajava.bookstore.service.book;

import atos.academiajava.bookstore.dto.BookDto;
import atos.academiajava.bookstore.dto.MessageResponseDto;
import atos.academiajava.bookstore.entity.BookModel;
import atos.academiajava.bookstore.exception.NotFoundException;
import atos.academiajava.bookstore.mapper.BookMapper;
import atos.academiajava.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto findById(Long id) throws NotFoundException {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        return bookMapper.toDto(book);
    }

    @Override
    public List<BookModel> findAll() throws NotFoundException{
        var bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public BookDto findByDescription(String description) throws NotFoundException{
        var book = bookRepository.findByDescription(description)
                .orElseThrow(() -> new NotFoundException());

        return bookMapper.toDto(book);
    }

    @Override
    public BookDto findByAuthor(String author) throws NotFoundException{
        var book = bookRepository.findByAuthor(author)
                .orElseThrow(() -> new NotFoundException());

        return bookMapper.toDto(book);
    }

    @Override
    public MessageResponseDto create(BookDto dto) {
        var exists = bookRepository.findByDescription(dto.getDescription()).isPresent();
        if (exists) {
          return MessageResponseDto.builder()
                  .message("Já existe um livro cadastrado com esse nome.")
                  .build();
        }

        var bookToSave = bookMapper.toModel(dto);

        bookRepository.save(bookToSave);
        return MessageResponseDto.builder().message("Livro cadastrado com sucesso.").build();
    }

    @Override
    public void update(long id, BookDto dto) throws NotFoundException {
        bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        var bookToSave = bookMapper.toModel(dto);
        bookToSave.setId(id);
        bookRepository.save(bookToSave);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        bookRepository.delete(book);
    }
}
