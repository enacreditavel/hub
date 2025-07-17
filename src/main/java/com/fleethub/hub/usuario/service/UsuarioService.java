package com.fleethub.hub.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fleethub.hub.configuration.Mapping;
import com.fleethub.hub.usuario.DTO.UsuarioDTO;
import com.fleethub.hub.usuario.entity.Usuario;
import com.fleethub.hub.usuario.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {

	@Autowired
	private final UsuarioRepository usuarioRepository;
	@Autowired
	private final Mapping mapping;

	public UsuarioDTO save(UsuarioDTO usuarioDTO) {
		Usuario usuario;
		try {
			usuario = usuarioRepository.save(mapping.mapUsuarioToEntity(usuarioDTO));
			
		} catch (DataIntegrityViolationException e) {
		    throw new DataIntegrityViolationException(e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar o usuário." + e.getMessage());
		}
		
		usuarioDTO = mapping.mapUsuarioToDTO(usuario);
		
		return usuarioDTO;
	}
	
	public List<UsuarioDTO> findAllUsuario() {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		if (usuarios.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuario encontrado.");
		}
		
		List<UsuarioDTO> usuariosDTO = mapping.mapListUsuarioDTO(usuarios);
		
		return usuariosDTO;
	}
	
	public void deleteById(Long id) {
		
	
		if (!usuarioRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id);
		}
		
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível deletar o usuário, pois ele está vinculado a outros registros.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar o usuário: " + e.getMessage());
		}
	}
	

	
	
	

	
}