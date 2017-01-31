package br.com.felipe.pos.usuario.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipe.pos.usuario.model.Agenda;

public interface AgendaRepository extends MongoRepository<Agenda, Integer> {

}
