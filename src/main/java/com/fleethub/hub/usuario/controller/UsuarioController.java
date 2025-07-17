package com.fleethub.hub.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleethub.hub.usuario.DTO.UsuarioDTO;
import com.fleethub.hub.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping()
	public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDTO));
	}
	
	// Endpoint para listar todos os usuários
	@GetMapping()
	public ResponseEntity<?> listarTodosUsuarios() {
		return ResponseEntity.ok(usuarioService.findAllUsuario());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
