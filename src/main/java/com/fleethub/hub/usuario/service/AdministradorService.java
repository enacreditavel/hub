package com.fleethub.hub.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fleethub.hub.configuration.Mapping;
import com.fleethub.hub.usuario.DTO.AdministradorDTO;
import com.fleethub.hub.usuario.entity.Administrador;
import com.fleethub.hub.usuario.repository.AdministradorRepository;

@Service
public class AdministradorService {
	@Autowired
	private AdministradorRepository administradorRepository;
	@Autowired
	private Mapping mapping;
	
	
	public List<AdministradorDTO> findAll() {
		List<Administrador> administradores = administradorRepository.findAll();
		if (administradores.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum administrador encontrado.");
		}
		
		List<AdministradorDTO> administradorDTO = mapping.mapListAdministradorDTO(administradores);
		
		return administradorDTO;
	}
	
}
