package atos.academiajava.bookstore.config.security;

import atos.academiajava.bookstore.filter.TokenAuthenticationFilter;
import atos.academiajava.bookstore.repository.UserRepository;
import atos.academiajava.bookstore.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static atos.academiajava.bookstore.common.SecurityConstants.*;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //Habilita a definição por rota de quem pode acessar
public class WebSecurityConfig {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, AUTH_ENDPOINT).permitAll() //Permite todas as chamadas para POST de login
                .antMatchers(SWAGGER_ENDPOINTS).permitAll() //Permite todas as chamadas para o Swagger
                .antMatchers(HttpMethod.POST, REGISTER_ENDPOINT).permitAll() //Permite todas as chamadas para o POST de cadastro
                .anyRequest().authenticated()//Define que todas as demais chamadas são autenticadas
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//"Desliga" a criação de sessões pelo Spring Security
                .and()
                .addFilterBefore( //Adiciona o filtro do Token antes de cada chamada
                        new TokenAuthenticationFilter(tokenService, userRepository),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
