package br.com.leonardo.carrera.desafio_tecnico.model;

import jakarta.persistence.*;


@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    @Column(length = 50)
    public String name;
    @Column(length = 100)
    public String email;
    String password;

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

