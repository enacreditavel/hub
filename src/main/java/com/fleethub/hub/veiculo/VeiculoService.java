package com.fleethub.hub.veiculo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fleethub.hub.abastecimento.AbastecimentoDTO;
import com.fleethub.hub.abastecimento.AbastecimentoService;
import com.fleethub.hub.configuration.Mapping;
import com.fleethub.hub.exceptions.ResourceNotFoundException;
import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.usuario.service.MotoristaService;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private MotoristaService motoristaService;
	@Autowired
	private AbastecimentoService abastecimentoService;
	@Autowired
	private Mapping mapping;
	

	public List<VeiculoDTO> findAll() {
		// Busca todos os veículos do repositório
		List<Veiculo> veiculos = veiculoRepository.findAll();
		if (veiculos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum veículo encontrado.");
		}
		// Mapeia a lista de veículos para DTOs
		List<VeiculoDTO> veiculosDTO = mapping.mapListVeiculoDTO(veiculos);
		return veiculosDTO;
	}
	
	// Encontrar veículo por ID
	public VeiculoDTO encontrarVeiculoPorID(Long veiculoID) {
		// Busca o veículo pelo ID
		Veiculo veiculo = veiculoRepository.findById(veiculoID)
				.orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com ID: " + veiculoID));
		// Mapeia o veículo encontrado para DTO
		VeiculoDTO veiculoDTO = mapping.mapVeiculoToDTO(veiculo);
		return veiculoDTO;
		}
		

	public VeiculoDTO novoVeiculo(VeiculoDTO veiculoDTO) {
		Veiculo veiculo;
		try {
			veiculo = veiculoRepository.save(mapping.mapVeiculoToEntity(veiculoDTO));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar o veículo: " + e.getMessage());
		}
		// Mapeia o veículo salvo para DTO
		veiculoDTO = mapping.mapVeiculoToDTO(veiculo);
		return veiculoDTO;
		
	}
	
	public VeiculoDTO associarMotoristaAoVeiculo(Long veiculoID, Long motoristaID) {
        // Verifica se o veículo existe
		Veiculo veiculo = veiculoRepository.findById(veiculoID)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));    
		// Verifica se o veículo possui motorista associado
        if (veiculo.getMotorista() != null) {
			throw new ResourceNotFoundException("Veículo já possui motorista associado.");
		}
        // Tenta encontrar o motorista pelo ID e associá-lo ao veículo
        Motorista motorista = mapping.mapMotoristaToEntity(motoristaService.encontrarMotoristaPorID(motoristaID));
        
        veiculo.setMotorista(motorista);
        // Salva as alterações no veículo
        try {
			veiculo = veiculoRepository.save(veiculo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Erro ao vincular motorista do veículo: " + e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao remover motorista do veículo: " + e.getMessage());
		}
        // Mapeia o veículo atualizado para DTO
        VeiculoDTO veiculoDTO = mapping.mapVeiculoToDTO(veiculo);
        return veiculoDTO;
    }
	
	public VeiculoDTO removerMotoristaDoVeiculo(Long veiculoId) {
		// Verifica se o veículo existe
        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));
        // Verifica se o veículo não possui motorista associado
        if (veiculo.getMotorista() == null) {
			throw new ResourceNotFoundException("Veículo não possui motorista associado.");
		}
        // Remove o motorista do veículo
        veiculo.setMotorista(null);
        // Salva as alterações no veículo
        try {
			veiculo = veiculoRepository.save(veiculo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Erro ao remover motorista do veículo: " + e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao remover motorista do veículo: " + e.getMessage());
		}
        // Mapeia o veículo atualizado para DTO
        VeiculoDTO veiculoDTO = mapping.mapVeiculoToDTO(veiculo);
        return veiculoDTO;
    }

	
	
	
	
	
	public AbastecimentoDTO abastecerVeiculo(Long veiculoId, AbastecimentoDTO abastecimentoDTO) {
		// Verifica se o veículo existe
		Veiculo veiculo = veiculoRepository.findById(veiculoId)
				.orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));
		
		// Verifica se o veículo possui motorista associado
		if (veiculo.getMotorista() == null) {
			throw new ResourceNotFoundException("Veículo não possui motorista associado.");
		}
		// Aciona o serviço de abastecimento para criar um novo abastecimento
		AbastecimentoDTO novoAbastecimento = abastecimentoService.novoAbastecimento(veiculo, abastecimentoDTO);
		return novoAbastecimento;
	}
}
