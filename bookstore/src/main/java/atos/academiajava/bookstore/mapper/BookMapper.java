package atos.academiajava.bookstore.mapper;

import atos.academiajava.bookstore.dto.BookDto;
import atos.academiajava.bookstore.entity.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookModel toModel(BookDto dto);
    BookDto toDto(BookModel model);
}
