package atos.academiajava.bookstore.service.auth;

import atos.academiajava.bookstore.dto.LoginDto;
import atos.academiajava.bookstore.dto.TokenDto;
import atos.academiajava.bookstore.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final TokenService tokenService;

    @Autowired
    public AuthServiceImpl(AuthenticationConfiguration authenticationConfiguration, TokenService tokenService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.tokenService = tokenService;
    }

    @Override
    public TokenDto login(LoginDto dto) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        );

        Authentication authentication = authenticationConfiguration.getAuthenticationManager()
                .authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken(authentication);
        return TokenDto.builder().token(token).build();
    }
}
