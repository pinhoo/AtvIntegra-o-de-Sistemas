package br.com.felipe.pos.usuario.model.service;

import java.util.List;

import br.com.felipe.pos.usuario.model.Usuario;

public interface UsuarioService {

	Usuario salvar(Usuario usuario);
	List<Usuario> listarTodos();
	Usuario listaById(int id);
}
