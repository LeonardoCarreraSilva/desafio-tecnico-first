package br.com.leonardo.carrera.desafio_tecnico.controller;

import br.com.leonardo.carrera.desafio_tecnico.DTO.UserDTO;
import br.com.leonardo.carrera.desafio_tecnico.model.User;
import br.com.leonardo.carrera.desafio_tecnico.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserDTO user) {
        String result = service.save(user);
        if( result.equals("Usario salvo com sucesso."))
            return ResponseEntity.ok(service.save(user));
        else
            return ResponseEntity.badRequest().body(result);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody User user){
        return ResponseEntity.ok(service.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

}
