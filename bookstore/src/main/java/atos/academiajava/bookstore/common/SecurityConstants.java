package atos.academiajava.bookstore.common;

public class SecurityConstants {

    public static final String SECRET = "EEw%7H![C}+z#'!h)'kS{SP{&kNQHf+]5G+w!#0hPoxa&=qyHa";
    public static final String ISSUER = "cursoatos";
    public static final long EXPIRATION_TIME = 900_000; //15 minutos
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_ENDPOINT = "/api/v1/auth";
    public static final String REGISTER_ENDPOINT = "/api/v1/users";

    public static String[] SWAGGER_ENDPOINTS = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}
