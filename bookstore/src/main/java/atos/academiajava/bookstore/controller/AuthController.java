package atos.academiajava.bookstore.controller;

import atos.academiajava.bookstore.dto.LoginDto;
import atos.academiajava.bookstore.dto.TokenDto;
import atos.academiajava.bookstore.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static atos.academiajava.bookstore.common.SecurityConstants.AUTH_ENDPOINT;

@RestController
@CrossOrigin
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public TokenDto login(@RequestBody @Valid LoginDto dto) throws Exception {
        return authService.login(dto);
    }
}
