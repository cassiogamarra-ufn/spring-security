package atos.academiajava.bookstore.mapper;

import atos.academiajava.bookstore.dto.UserDto;
import atos.academiajava.bookstore.entity.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel toModel(UserDto dto);
    UserDto toDto(UserModel model);
}
