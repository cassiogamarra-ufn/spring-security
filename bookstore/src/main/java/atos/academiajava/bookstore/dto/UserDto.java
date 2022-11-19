package atos.academiajava.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor //Classe responsável pela criação do usuário
public class UserDto {

    @NotBlank
    @Size(max = 255)
    private String username;

    @NotBlank
    @Size(max = 255)
    private String password;

    @NotBlank
    @Size(max = 255)
    private String retypePassword;
}
