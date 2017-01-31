package br.com.felipe.pos.usuario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Agenda {

	@Id
	private int id;
	@DBRef
	private Usuario usuario;
	public Agenda(){
		
	}
	public Agenda(int id, Usuario usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
