package com.fleethub.hub.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fleethub.hub.configuration.Mapping;
import com.fleethub.hub.exceptions.ResourceNotFoundException;
import com.fleethub.hub.usuario.DTO.MotoristaDTO;
import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.usuario.repository.MotoristaRepository;

@Service
public class MotoristaService {
	
	@Autowired
	private MotoristaRepository motoristaRepository;
	@Autowired
	private Mapping mapping;
	
	public MotoristaDTO encontrarMotoristaPorID(Long motoristaID) {
		
		Motorista motorista = motoristaRepository.findById(motoristaID)
				.orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado."));
		
		MotoristaDTO motoristaDTO = mapping.mapMotoristaToDTO(motorista);
		return motoristaDTO;

	}
	
	//Listar todos motoristas e retornar DTOs
	public List<MotoristaDTO> findAllMotorista() {
		
		List<Motorista> motoristas = motoristaRepository.findAll();
		
		if (motoristas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum motorista encontrado.");
		}
		
		List<MotoristaDTO> motoristasDTO = mapping.mapListMotoristaDTO(motoristas);
		
		return motoristasDTO;
	}
	
	//Encontrar motorista por ID e retornar DTO
	public MotoristaDTO findById(Long id) {
		Motorista motorista = motoristaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Motorista não encontrado com ID: " + id));
		MotoristaDTO motoristaDTO = mapping.mapMotoristaToDTO(motorista);
		return motoristaDTO;
	}
	
	//Deletar motorista por ID
	public void deleteMotorista(Long id) {
		
		if (!motoristaRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado com ID: " + id);
		}
		
		motoristaRepository.deleteById(id);
	}

}
