package atos.academiajava.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity //Define que é uma entidade
@Table(name = "tb_users") //Define qual é a tabela que estamos mapeando
@Data //Anotações do Lombok para criação de Getters, Setters e Construtores
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails, Serializable { //Implementa a interface UserDetails, responsável por recuperar dados referente ao usuário.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) //Carrega os dados com mais frequencia, pois precismos das roles do usuário
    @JoinTable(name = "tb_users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleModel> roleModels;

    /*Implementação do UserDetails*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleModels;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
