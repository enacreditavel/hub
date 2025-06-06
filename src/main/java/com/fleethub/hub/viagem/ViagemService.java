package com.fleethub.hub.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleethub.hub.configuration.Mapping;
import com.fleethub.hub.veiculo.Veiculo;
import com.fleethub.hub.veiculo.VeiculoService;

@Service
public class ViagemService {

	@Autowired
	private ViagemRepository viagemRepository;
	@Autowired
	private VeiculoService veiculoService;
	@Autowired
	private Mapping mapping;

	public ViagemDTO novaViagem(Long veiculoId, ViagemDTO viagemDTO) {
		Veiculo veiculo = mapping.mapVeiculoToEntity(veiculoService.encontrarVeiculoPorID(veiculoId));
		
		Viagem viagem = mapping.mapViagemToEntity(viagemDTO);
		
		viagem.setVeiculo(veiculo);
		viagem.setMotorista(veiculo.getMotorista());
		viagem.setStatus(StatusViagem.NAO_INICIADA);
		
		try {
			viagem = viagemRepository.save(viagem);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar a viagem: " + e.getMessage());
		}
		
		// Mapeia a entidade Viagem para DTO e retorna
		viagemDTO= mapping.mapViagemToDTO(viagem);
		
		return viagemDTO;
		

			
	}

}
