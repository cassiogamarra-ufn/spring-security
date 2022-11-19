![header](https://user-images.githubusercontent.com/116769226/202857262-256420a0-1ec0-4fa7-8962-f1ffac12cc3b.png)
## Spring Security

## O que é o Spring Security?

Primeiramente o Spring Security é um framework do projeto Spring que possui um sistema de autenticação e autorização de alto nível e altamente customizável para aplicações Java.
A framework inclusive é a solução oficial para implementação de recursos de segurança em aplicações Spring Boot, porém ele também pode ser utilizado em conjunto com outras frameworks.

## Funcionalidades do Spring Security

Ainda que o foco do Spring Security seja o sistema de autenticação e autorização, ele possui outras funcionalidades que aumentam a segurança de nossas aplicações Java, aqui vai uma lista de algumas de suas principais funcionalidades:

- Sistema de autenticação
- Sistema de autorização
- Proteção contra ataques como session fixation, clickjacking e cross site request forgery
- Integração com a Servlet API
- Integração opcional com o Spring Web MVC
- Fácil instalação através de um starter Spring Boot

## Autenticação
Basicamente podemos dizer que a autenticação é o **login** na nossa aplicação. Trata-se da etapa de verificação se um determinado usuário possui credenciais (geralmente, combinação de login e senha) válidas para acessar a nossa aplicação.
Especificamente o sistema de autenticação do Spring Security pode ser configurado para que utilize diferentes estratégias de autenticação, pois o mesmo trabalha com o conceito de **providers** de autenticação.

Os **providers** de autenticação são as estruturas responsáveis por efetivar as informações sobre os usuários que acessam a aplicação. Dessa maneira, você pode ter uma série de **providers** diferentes para utilizar nas aplicações.

## Autorização
Já a autorização é um processo que acontece depois da autenticação. É o momento onde a aplicação verifica se o usuário atualmente autenticado tem permissão de acesso a um determinado recurso.

O sistema de autorização do Spring Security também é bastante flexível, pois nos permite definir com facilidade quais são os possíveis tipos de usuários da nossa aplicação, como o sistema relaciona cada usuário com o seu determinado tipo e quais rotas de nossa aplicação cada tipo de usuário terá acesso.