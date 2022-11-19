package atos.academiajava.bookstore.service.token;

import atos.academiajava.bookstore.entity.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

import static atos.academiajava.bookstore.common.SecurityConstants.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(Authentication authentication) { //Responsável pela geração do token
        UserModel userModel = (UserModel) authentication.getPrincipal();

        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRATION_TIME);

        var jwt = Jwts.builder()
                .setIssuer(ISSUER) //Define quem está criando o token
                .setSubject(userModel.getUserId().toString()) //Define a quem se refere o token
                .setIssuedAt(now) //Define quando o token foi criado
                .setExpiration(exp) //Define a expiração do token
                .signWith(SignatureAlgorithm.HS256, SECRET) //Define qual o algoritmo de assinatura e a nossa secret
                .compact(); //Transforma em uma string

        return jwt;
    }

    @Override
    public boolean isTokenValid(String token) { //Responsável pela validação do token
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Long getTokenId(String token) { //Responsável por buscar o Subject (a quem se refere) o token
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return Long.valueOf(body.getSubject());
    }
}
