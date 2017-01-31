package br.com.felipe.pos.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.pos.usuario.model.Usuario;
import br.com.felipe.pos.usuario.model.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		try {
			service.salvar(usuario);
			return new ResponseEntity<>(usuario,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping("/lista")
	public List<Usuario> listarTodos(){
		List<Usuario> listaUsuarios = service.listarTodos();
		return listaUsuarios;
	}

}
