package com.fleethub.hub.veiculo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleethub.hub.abastecimento.AbastecimentoDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping()
	public ResponseEntity<List<VeiculoDTO>> listarVeiculos() {
		List<VeiculoDTO> veiculos = veiculoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(veiculos);
	}

	@PostMapping()
	public ResponseEntity<VeiculoDTO> criarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO) {
		VeiculoDTO novoVeiculo = veiculoService.novoVeiculo(veiculoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
	}

	@PutMapping("/set-motorista/{veiculoId}/{motoristaId}")
	public ResponseEntity<String> associarMotoristaAoVeiculo(@PathVariable Long veiculoID, @PathVariable Long motoristaID) {
		VeiculoDTO updatedVeiculo = veiculoService.associarMotoristaAoVeiculo(veiculoID, motoristaID);
		String message = "Motorista: %s vinculado ao veículo %s de placa %s".formatted(
				updatedVeiculo.getMotorista().getNome(), updatedVeiculo.getModelo(), updatedVeiculo.getPlaca());
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping("/set-motorista/{veiculoId}")
	public ResponseEntity<String> removerMotoristaDoVeiculo(@PathVariable Long veiculoID) {
		VeiculoDTO updatedVeiculo = veiculoService.removerMotoristaDoVeiculo(veiculoID);
		return ResponseEntity.status(HttpStatus.OK).body("Motorista desvinculado ao veículo %s de placa %s".formatted(
				 updatedVeiculo.getModelo(), updatedVeiculo.getPlaca()));
	}

	@PostMapping("/{veiculoId}/abastecer")
	public ResponseEntity<String> abastecerVeiculo(@PathVariable Long veiculoID, @RequestBody @Valid AbastecimentoDTO abastecimentoDTO) {
		AbastecimentoDTO novoAbastecimento = veiculoService.abastecerVeiculo(veiculoID, abastecimentoDTO);
		String message ="Abastecimento realizado com sucesso: \nVeículo: %s \nPlaca: %s \nValor: %s \nLitros: %s".formatted(
				novoAbastecimento.getVeiculo().getModelo(), novoAbastecimento.getVeiculo().getPlaca(), 
				novoAbastecimento.getValor(), novoAbastecimento.getLitros());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(message);
	}

}
