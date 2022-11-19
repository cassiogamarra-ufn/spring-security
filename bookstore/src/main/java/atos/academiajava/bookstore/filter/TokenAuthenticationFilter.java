package atos.academiajava.bookstore.filter;

import atos.academiajava.bookstore.entity.UserModel;
import atos.academiajava.bookstore.repository.UserRepository;
import atos.academiajava.bookstore.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static atos.academiajava.bookstore.common.SecurityConstants.HEADER_STRING;
import static atos.academiajava.bookstore.common.SecurityConstants.TOKEN_PREFIX;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request); //Busca o token na nossa requisição
        boolean tokenValid = tokenService.isTokenValid(tokenFromHeader); //Verifica se o token é valido e autentica
        if (tokenValid) {
            this.authenticate(tokenFromHeader);
        }

        filterChain.doFilter(request, response); //Da continuidade na requisição
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING); //Busca o token
        if (token == null || token.isEmpty() || !token.startsWith(TOKEN_PREFIX)) { //Verifica se ele existe
            return null;
        }

        return token.substring(TOKEN_PREFIX.length()); //Retorna apenas o token, sem o Bearer do inicio
    }

    private void authenticate(String tokenFromHeader) { //Valida se nosso usuário pode autenticar na aplicação
        Long id = tokenService.getTokenId(tokenFromHeader);

        Optional<UserModel> optionalUserModel = userRepository.findById(id);

        if (optionalUserModel.isPresent()) {
            UserModel user = optionalUserModel.get();

            var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());
            SecurityContextHolder.getContext()
                    .setAuthentication(usernamePasswordAuthToken); //Seta a autenticação do usuário atual
        }
    }
}
