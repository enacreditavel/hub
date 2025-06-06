package com.fleethub.hub.abastecimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abastecimento")
public class AbastecimentoController {
	
	@Autowired
	private AbastecimentoService abastecimentoService;
	
	@GetMapping
	public List<AbastecimentoDTO> findAll() {
		return abastecimentoService.findAll();
	}
	
	@GetMapping("/{abastecimentoId}")
	public AbastecimentoDTO findById(@PathVariable Long abastecimentoId) {
		
		return abastecimentoService.findById(abastecimentoId);
	}

	

	// Métodos para manipulação de abastecimentos podem ser adicionados aqui
	// Exemplo: @PostMapping, @GetMapping, etc.

}
