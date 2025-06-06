package com.fleethub.hub.abastecimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fleethub.hub.configuration.Mapping;
import com.fleethub.hub.veiculo.Veiculo;

@Service
public class AbastecimentoService {

	@Autowired
	private AbastecimentoRepository abastecimentoRepository;
	@Autowired
	private Mapping mapping;

	public AbastecimentoDTO novoAbastecimento(Veiculo veiculo, AbastecimentoDTO abastecimentoDTO) {
		Abastecimento abastecimento = mapping.mapAbastecimentoToEntity(abastecimentoDTO);
		
		abastecimento.setData(java.time.LocalDateTime.now());
		abastecimento.setVeiculo(veiculo);
		abastecimento.setMotorista(veiculo.getMotorista());
		abastecimento.setKmAtualVeiculo(veiculo.getKmAtual());
		
		try {
			abastecimento = abastecimentoRepository.save(abastecimento);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"(AbastecimentoService error: Erro ao abastecer veículo: " + e.getMessage());
		}
		
		abastecimentoDTO = mapping.mapAbastecimentoToDTO(abastecimento);

		return abastecimentoDTO;

	}

	public List<AbastecimentoDTO> findAll() {
		List<Abastecimento> abastecimentos = abastecimentoRepository.findAll();
		if (abastecimentos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum abastecimento encontrado.");
		}
		List<AbastecimentoDTO> abastecimentosDTO = mapping.mapListAbastecimentoDTO(abastecimentos);
		return abastecimentosDTO;
	}

	public AbastecimentoDTO findById(Long abastecimentoId) {
		Abastecimento abastecimento = abastecimentoRepository.findById(abastecimentoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Abastecimento não encontrado com ID: " + abastecimentoId));
		AbastecimentoDTO abastecimentoDTO = mapping.mapAbastecimentoToDTO(abastecimento);
		return abastecimentoDTO;
	}

}
