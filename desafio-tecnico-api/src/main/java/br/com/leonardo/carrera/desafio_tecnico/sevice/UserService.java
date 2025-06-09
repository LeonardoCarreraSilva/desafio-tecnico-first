package br.com.leonardo.carrera.desafio_tecnico.sevice;

import br.com.leonardo.carrera.desafio_tecnico.DTO.UserDTO;
import br.com.leonardo.carrera.desafio_tecnico.model.User;
import br.com.leonardo.carrera.desafio_tecnico.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public String save(UserDTO dto) {
        var errorMessage = "";
        if (dto.name.length() < 3 || dto.name.length() > 50) {
            errorMessage = "O nome deve ter no mínimo de 3 caracteres e no máximo de 50 caracteres";
        }
        if (dto.password.length() < 6 || dto.password.length() > 20) {
            errorMessage = "A senha deve ter no mínimo de 6 caracteres e no máximo de 20 caracteres";
        }

        if (errorMessage.isEmpty()) {
            repository.saveAndFlush(new User(null, dto.name, dto.email, new BCryptPasswordEncoder().encode(dto.password)));
            return "Usario salvo com sucesso.";
        } else {
            return errorMessage;
        }
    }

    public String update(User user) {
        Optional<User> byId = repository.findById(user.id);

        if(byId.isPresent()){
            byId.get().setEmail(user.getEmail());
            byId.get().setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            byId.get().setName(user.getName());
            repository.save(byId.get());
            return "Usuario atualizado com sucesso!";
        }else{
            return "Usuario não encontrado.";
        }
    }

    public String delete(Long id) {
        try {
            repository.delete(repository.findById(id).get());
            return "Usuario excluido com sucesso.";
        } catch (Exception e) {
            return "Erro ao excluir usuario.";
        }
    }
}
