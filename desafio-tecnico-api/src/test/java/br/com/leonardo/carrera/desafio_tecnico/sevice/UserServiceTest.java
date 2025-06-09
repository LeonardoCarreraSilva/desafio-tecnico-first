package br.com.leonardo.carrera.desafio_tecnico.sevice;

import br.com.leonardo.carrera.desafio_tecnico.DTO.UserDTO;
import br.com.leonardo.carrera.desafio_tecnico.model.User;
import br.com.leonardo.carrera.desafio_tecnico.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    public UserService service;
    @Mock
    public UserRepository repository;

    @BeforeEach()
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar lista de usuarios cadastrados")
    public void DeveListarUsuarios() {
        when(repository.findAll()).thenReturn(List.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        List<User> result = service.findAll();
        Assertions.assertEquals(1, result.size());
    }


    @Test
    @DisplayName("Deve retornar usuario cadastrado")
    public void DeveRetornarUsuario() {
        when(repository.findById(any())).thenReturn(Optional.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        User result = service.findById(1L);
        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("Deve salvar usuario")
    public void DeveSalvarUsuario() {
        when(repository.findById(any())).thenReturn(Optional.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        String result = service.save(new UserDTO("Leonardo", "Leonardosilva.ads@hotmail.com", "1234567"));
        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("Deve validar nome do usuario")
    public void DeveValidarNomeUsuario() {
        when(repository.findById(any())).thenReturn(Optional.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        String result = service.save(new UserDTO("Le", "Leonardosilva.ads@hotmail.com", "1234567"));
        String result2 = service.save(new UserDTO("aajsdf;alsdkfj;adslfkja;sdfkja;sdlfkjasdlkfjsdkjfsldkfjsdlkfjsldfjfldskfjsdlkfjsdlkfjsdlkfjsdlkfj", "Leonardosilva.ads@hotmail.com", "1234567"));
        Assertions.assertEquals("O nome deve ter no mínimo de 3 caracteres e no máximo de 50 caracteres",result);
        Assertions.assertEquals("O nome deve ter no mínimo de 3 caracteres e no máximo de 50 caracteres",result2);
    }

    @Test
    @DisplayName("Deve validar senha do usuario")
    public void DeveSenhaNomeUsuario() {
        when(repository.findById(any())).thenReturn(Optional.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        String result = service.save(new UserDTO("Leonardo", "Leonardosilva.ads@hotmail.com", "123"));
        String result2 = service.save(new UserDTO("Leonardo", "Leonardosilva.ads@hotmail.com", "jdsljf;asldkj;alskdfjlksdjalskdjalsdkjasljdlaksjdlaskjdlsakdajsdlk"));
        Assertions.assertEquals("A senha deve ter no mínimo de 6 caracteres e no máximo de 20 caracteres",result);
        Assertions.assertEquals("A senha deve ter no mínimo de 6 caracteres e no máximo de 20 caracteres",result2);
    }


    @Test
    @DisplayName("Deve Atualizar usuario")
    public void DeveAtualizarUsuario() {
        when(repository.findById(any())).thenReturn(Optional.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        String result = service.update(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123654789"));
        Assertions.assertEquals("Usuario atualizado com sucesso!",result);
    }

    @Test
    @DisplayName("Deve deletar usuario")
    public void DeveDeletarUsuario() {
        when(repository.findById(any())).thenReturn(Optional.of(new User(1L, "Leonardo", "Leonardosilva.ads@hotmail.com", "123456")));
        doNothing().when(repository).delete(any());
        String result = service.delete(1L);
        Assertions.assertEquals("Usuario excluido com sucesso.",result);
    }

    @Test
    @DisplayName("Deve deletar com erro usuario")
    public void DeveApresentarEroUsuario() {
        when(repository.findById(any())).thenThrow(NoSuchElementException.class);
        String result = service.delete(1L);
        Assertions.assertEquals("Erro ao excluir usuario.",result);
    }







}