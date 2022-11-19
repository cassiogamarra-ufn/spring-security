package atos.academiajava.bookstore.service.auth;

import atos.academiajava.bookstore.dto.LoginDto;
import atos.academiajava.bookstore.dto.TokenDto;

public interface AuthService {

    public TokenDto login(LoginDto dto) throws Exception;
}
