package atos.academiajava.bookstore.repository;

import atos.academiajava.bookstore.entity.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    Optional<BookModel> findByDescription(String description);
    Optional<BookModel> findByAuthor(String authorName);
}
