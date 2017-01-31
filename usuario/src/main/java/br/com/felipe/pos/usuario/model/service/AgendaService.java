package br.com.felipe.pos.usuario.model.service;

import java.util.List;

import br.com.felipe.pos.usuario.model.Agenda;

public interface AgendaService {

	Agenda salvarReserva(Agenda agenda);
	List<Agenda> listarReservas();
}
