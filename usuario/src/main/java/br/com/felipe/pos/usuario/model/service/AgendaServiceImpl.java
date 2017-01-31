package br.com.felipe.pos.usuario.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.pos.usuario.model.Agenda;
import br.com.felipe.pos.usuario.repository.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService {
	
	@Autowired
	private AgendaRepository repository;

	public Agenda salvarReserva(Agenda agenda){
		List<Agenda> listaseq = repository.findAll();
		Agenda agendaseq = listaseq.get(listaseq.size() -1);
		int id = agendaseq.getId() +1;
		agenda.setId(id);
		return repository.save(agenda);
	}
	public List<Agenda> listarReservas(){
		return repository.findAll();
	}
}
