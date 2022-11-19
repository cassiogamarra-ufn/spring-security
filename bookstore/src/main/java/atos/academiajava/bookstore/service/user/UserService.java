package atos.academiajava.bookstore.service.user;

import atos.academiajava.bookstore.dto.MessageResponseDto;
import atos.academiajava.bookstore.dto.UserDto;

public interface UserService {

    MessageResponseDto create(UserDto dto);
}
