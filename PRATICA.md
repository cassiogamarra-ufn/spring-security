![header](https://user-images.githubusercontent.com/116769226/202857262-256420a0-1ec0-4fa7-8962-f1ffac12cc3b.png)
## Spring Security
![Badge Curso ATOS 2022 - Cássio Gamarra](https://img.shields.io/badge/Curso%20ATOS%202022-C%C3%A1ssio%20Gamarra-blue)

# Índice 

* [Índice](#índice)
* [Adicionando a autenticação personalizada em nosso projeto](#adicionando-a-autenticação-personalizada-em-nosso-projeto)
* [Criando a estrutura básica de usuário](#criando-a-estrutura-básica-de-usuário)
* [Criando o arquivo de constantes](#criando-o-arquivo-de-constantes)
* [Implementando o serviço de Token](#implementando-o-serviço-de-token)
* [Implementando o serviço de autenticação](#implementando-o-serviço-de-autenticação)
* [Implementando o filter de autenticação](#implementando-o-filter-de-autenticação)
* [Implementando a configuração](#implementando-a-configuração)

## Adicionando a autenticação personalizada em nosso projeto.
Para adicionar a autenticação personalizada, precisamos seguir alguns passos:
- Possuir a estrutura básica de usuário
- Possuir a estrutura básica de funções
- Adicionar a configuração do Spring Security
- Implementar a busca pelo usuário 
- Implementar a geração do token

Obs: os textos com 🔗 se referem aos links da classe implementada. Detalhes da implementação estarão comentados na classe.
Obs2: os serviços sempre possuem uma interface e uma implementação.

## Criando a estrutura básica de usuário 

Primeiramente, precisamos criar duas classes no nosso pacote entity: [**UserModel 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/entity/UserModel.java) e [**RoleModel 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/entity/RoleModel.java). A RoleModel possui um Enum para facilitar a manutenção de código, a implementação está aqui [**RoleName 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/enums/RoleName.java)

A primeira se refere ao usuário e a segunda as funções que esse usuário possui. As funções(RoleName) devem possuir o seguinte format: ROLE_NOMEFUNC.

Para facilitar a criação, no arquivo **application.properties** podemos adicionar a linha:
```
spring.jpa.hibernate.ddl-auto=update
```
Ela é responsável por informar ao JPA que deve criar/atualizar os itens na base de dados. Após adicionar essa linha, basta rodar o projeto para criar as tabelas. Após isso é possível comentar a mesma.

O próximo passo é implementar a service e a controller responsáveis pelo cadastro do nosso usuário, para isso vamos implementar o seguinte:
- [**UserDto 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/dto/UserDto.java)
- [**UserService 🔗**](https://github.com/cassiogamarra-ufn/spring-security/tree/main/bookstore/src/main/java/atos/academiajava/bookstore/service/user)
- [**UserController 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/controller/UserController.java)

Com isso temos a base de criação do usuário pronta, podemos partir para os próximos passos.

## Criando o arquivo de constantes
Com um arquivo de constantes, podemos centralizar no código alguns itens que serão utilizados diversas vezes: [**SecurityConstants 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/common/SecurityConstants.java)
Nele definimos constantes para criação do token e também endpoints que serão comuns na aplicação.

## Implementando o serviço de Token
Nesse passo devemos implementar a forma como nosso token é gerado e validado, para isso vamos instalar a seguinte biblioteca:

```
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt</artifactId>
  <version>0.9.1</version>
</dependency>
```
Adicionamos dois novos Dto's: [**LoginDto 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/dto/LoginDto.java) e [**TokenDto 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/dto/TokenDto.java)

Após isso, realizamos a implementação do serviço de token: [**TokenService 🔗**](https://github.com/cassiogamarra-ufn/spring-security/tree/main/bookstore/src/main/java/atos/academiajava/bookstore/service/token) 

## Implementando o serviço de autenticação
Após ter o token implementado, podemos implementar o serviço de autenticação: [**AuthService 🔗**](https://github.com/cassiogamarra-ufn/spring-security/tree/main/bookstore/src/main/java/atos/academiajava/bookstore/service/auth)

Com o serviço implementado, podemos adicionar o controller responsável pelo login: [**AuthController 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/controller/AuthController.java)

## Implementando o filter de autenticação
Com a implementação do serviço de autenticação, precisamos adicionar o filtro responsável por interceptar as requisições e validar nosso token JWT.
[**TokenAuthenticationFilter 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/filter/TokenAuthenticationFilter.java)

## Implementando a configuração
Por fim, após implementar os serviços de Token e Autenticação, o filtro para interceptar as requisições, precisamos realizar a configuração do Spring Security:

Precisamos implementar duas classes, a de configuração e a do usuário: 
[**UserDetailsService 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/config/security/UserDetailServiceImpl.java)
[**WebSecurityConfig 🔗**](https://github.com/cassiogamarra-ufn/spring-security/blob/main/bookstore/src/main/java/atos/academiajava/bookstore/config/security/WebSecurityConfig.java)

## Conclusão
Seguindo esses passos, teremos implementado a autenticação com SpringSecurity e a criação de JWT para utilização em nossas próximas requisições.

