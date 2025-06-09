package br.com.leonardo.carrera.desafio_tecnico.repository;

import br.com.leonardo.carrera.desafio_tecnico.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
