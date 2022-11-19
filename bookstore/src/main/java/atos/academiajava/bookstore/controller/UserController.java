package atos.academiajava.bookstore.controller;

import atos.academiajava.bookstore.dto.MessageResponseDto;
import atos.academiajava.bookstore.dto.UserDto;
import atos.academiajava.bookstore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public MessageResponseDto create(@RequestBody @Valid UserDto dto) {
        return userService.create(dto);
    }
}
