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

    private final AuthenticationConfiguration authenticationConfiguration; //Responsável pela parte de autenticação do Spring Security
    private final TokenService tokenService;

    @Autowired
    public AuthServiceImpl(AuthenticationConfiguration authenticationConfiguration, TokenService tokenService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.tokenService = tokenService;
    }

    @Override
    public TokenDto login(LoginDto dto) throws Exception { //Realiza o login do nosso usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        ); //Passa o usuário e senha para autenticação

        Authentication authentication = authenticationConfiguration.getAuthenticationManager()
                .authenticate(usernamePasswordAuthenticationToken); //Autentica o nosso usuário

        String token = tokenService.generateToken(authentication); //Gera o token JWT
        return TokenDto.builder().token(token).build();
    }
}
