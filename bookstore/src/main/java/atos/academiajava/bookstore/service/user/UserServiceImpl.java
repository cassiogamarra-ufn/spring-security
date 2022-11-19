package atos.academiajava.bookstore.service.user;

import atos.academiajava.bookstore.dto.MessageResponseDto;
import atos.academiajava.bookstore.dto.UserDto;
import atos.academiajava.bookstore.entity.RoleModel;
import atos.academiajava.bookstore.enums.RoleName;
import atos.academiajava.bookstore.mapper.UserMapper;
import atos.academiajava.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder; //Importação do password encoder

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MessageResponseDto create(UserDto dto) { //Método responsável pela criação do nosso usuário no banco
        var isSamePassword = dto.getPassword().equals(dto.getRetypePassword());

        if (!isSamePassword) {
            return MessageResponseDto.builder()
                    .message("Senhas não são iguais.")
                    .build();
        }

        var exists = userRepository.findByUsername(dto.getUsername()).isPresent();
        if (exists) {
            return MessageResponseDto.builder()
                    .message("Usuário já cadastrado.")
                    .build();
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword())); //Faz a criptografia da senha

        var userToSave = userMapper.toModel(dto);
        var roles= setUserRoles(2, RoleName.ROLE_USER);
        userToSave.setRoleModels(roles);

        userRepository.save(userToSave);

        return MessageResponseDto.builder().message("Usuário cadastrado com sucesso.").build();
    }

    private List<RoleModel> setUserRoles(long id, RoleName roleName) { //Método para adicionar a role em nosso usuário
        var role = new RoleModel();
        role.setRoleId(id);
        role.setRoleName(roleName);
        var roles = new ArrayList<RoleModel>();
        roles.add(role);

        return roles;
    }
}
