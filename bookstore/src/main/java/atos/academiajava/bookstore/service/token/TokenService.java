package atos.academiajava.bookstore.service.token;

import org.springframework.security.core.Authentication;

public interface TokenService {

    public String generateToken(Authentication authentication);
    public boolean isTokenValid(String token);
    public Long getTokenId(String token);
}
