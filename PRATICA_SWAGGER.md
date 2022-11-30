![header](https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg)
## Swagger
![Badge Curso ATOS 2022 - Cássio Gamarra](https://img.shields.io/badge/Curso%20ATOS%202022-C%C3%A1ssio%20Gamarra-blue)

# Índice 

* [Índice](#índice)
* [Adicionando o Swagger em nosso projeto](#adicionando-o-swagger-em-nosso-projeto)
* [Permitindo o acesso com o Spring Security](#permitindo-o-acesso-com-o-spring-security)
* [Mais implementações](#mais-implementações)

## Adicionando o Swagger em nosso projeto

Adicione as dependencias do Spring Fox no arquivo pom.xml

```
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.9.2</version>
</dependency>
```

Adicione as dependencias para ter o Swagger UI no projeto: 

```
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.9.2</version>
</dependency>
```

Após isso, crie um arquivo SwaggerConfig.java e adicione o seguinte:

```
@Configuration //Diz que é uma configuração
@EnableSwagger2 //Ativa o Swagger 2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

## Permitindo o acesso com o Spring Security
Caso em seu projeto tenha uma implementação do Spring Security, é necessário algumas configurações adicionais para permitir a visualização do Swagger UI:

Recomendo a criação de um array de strings contendo os endpoints relacionados ao Swagger:
```
public static String[] SWAGGER_ENDPOINTS = { //Endpoints do Swagger
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
```

Após isso, na configuração do Spring Security, adicione um novo antMatchers permitindo os endpoints do Swagger, conforme o exemplo abaixo:
```
.antMatchers(SWAGGER_ENDPOINTS).permitAll()
```

Com isso teremos o Swagger configurado no nosso projeto, para mais implementações, confira os links abaixo.

## Mais implementações

Exemplos no site Baeldung (em inglês) [Setting Up Swagger 2 with a Spring REST API Using Springfox ⏱️](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

Exemplos no TreinaWeb: [Documentando uma API Spring Boot com o Swagger ⏱️](https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger)
