package br.com.felipe.pos.usuario.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.pos.usuario.model.Usuario;
import br.com.felipe.pos.usuario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario salvar(Usuario usuario) {
		List<Usuario> listaseq = repository.findAll();
		Usuario userseq = listaseq.get(listaseq.size() -1);
		int id = userseq.getId() + 1;
		usuario.setId(id);
		repository.save(usuario);

		return null;
	}

	public List<Usuario> listarTodos() {
		return repository.findAll();
	}

	public Usuario listaById(int id) {

		return repository.findOne(id);

	}
}
