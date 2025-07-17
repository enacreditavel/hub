package com.fleethub.hub.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleethub.hub.usuario.DTO.MotoristaDTO;
import com.fleethub.hub.usuario.service.MotoristaService;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

	@Autowired
	private MotoristaService motoristaService;
	
	// Endpoint para listar todos os motoristas
	@GetMapping
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	public ResponseEntity<List<MotoristaDTO>> listarTodosMotoristas() {
		List<MotoristaDTO> motoristas = motoristaService.findAllMotorista();
		return ResponseEntity.ok(motoristas);
	}
	
	// Endpoint para encontrar motorista por ID
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMINISTRADOR') or #id == authentication.principal.id")
	public ResponseEntity<MotoristaDTO> encontrarMotoristaPorId(@PathVariable Long id) {
		MotoristaDTO motorista = motoristaService.findById(id);
		return ResponseEntity.ok(motorista);
	}
}
