package com.fleethub.hub.viagem;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.usuario.entity.Passageiro;
import com.fleethub.hub.veiculo.Veiculo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViagemDTO {

	private Long id;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	@NotBlank(message = "Informe o local de partida")
	private String origem;

	@NotBlank(message = "Informe o local de destino")
	private String destino;

	private Integer kmInicial;

	private Integer kmFinal;

	private StatusViagem status;
	
	// Veículo, Motorista e Passageiros são campos que não devem ser serializados na resposta, apenas na requisição

	
	// Veículo
	
	// Este campo é usado apenas para receber o veículo na requisição
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Veiculo veiculo;
	
	// Campos de leitura para o veículo

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String veiculoPlaca = getVeiculoPlaca();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String veiculoModelo = getVeiculoModelo();

	// Métodos auxiliares para obter os dados do veículo para leitura
	private String getVeiculoPlaca() {
		return this.veiculo != null ? veiculo.getPlaca() : null;
	}

	private String getVeiculoModelo() {
		return this.veiculo != null ? veiculo.getModelo() : null;
	}

	// Motorista
	
	// Este campo é usado apenas para receber o motorista na requisição
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Motorista motorista;

	// Campos de leitura para o motorista
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String motoristaNome = getMotoristaNome();
	
	
	private String getMotoristaNome() {
		return this.motorista != null ? motorista.getNome() : null;
	}
	
	// Passageiros
	
	// Este campo é usado apenas para receber os passageiros na requisição

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Passageiro> passageiros;
	
	// Campos de leitura para os passageiros
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<String> passageirosNomes = getPassageirosNomes();
	
	// Método auxiliar para obter os nomes dos passageiros para leitura

	private List<String> getPassageirosNomes() {
		return this.passageiros != null ? passageiros.stream().map(Passageiro::getNome).toList() : null;
	}

}
