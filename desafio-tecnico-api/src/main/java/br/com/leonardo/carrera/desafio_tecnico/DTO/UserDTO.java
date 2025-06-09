package br.com.leonardo.carrera.desafio_tecnico.DTO;

public class UserDTO {
    public String name;
    public String email;
    public String password;

    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
