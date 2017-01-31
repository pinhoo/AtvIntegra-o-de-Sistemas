package br.com.felipe.pos.usuario.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipe.pos.usuario.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {

}
