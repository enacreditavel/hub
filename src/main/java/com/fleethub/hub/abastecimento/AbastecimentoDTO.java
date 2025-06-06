package com.fleethub.hub.abastecimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.veiculo.Veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbastecimentoDTO {
	private Long id;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime data;
	
	private String local;
	
	@NotNull(message = "O valor do abastecimento é obrigatório")
	private BigDecimal valor;
	
	
	@NotNull(message = "A quantidade de litros é obrigatória")
	private BigDecimal litros;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private BigDecimal kmAtualVeiculo;
	
	@NotBlank(message = "O tipo de combustível é obrigatório")
	private String tipoCombustivel;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Motorista motorista;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String motoristaNome = getMotoristaNome();
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Veiculo veiculo;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String veiculoPlaca = getVeiculoPlaca();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String veiculoModelo;
	
	private String getVeiculoPlaca() {
		return this.veiculo != null ? veiculo.getPlaca() : null;
	}
	
	private String getVeiculoModelo() {
		return this.veiculo != null ? veiculo.getModelo() : null;
	}
	
	private String getMotoristaNome() {
		return this.motorista != null ? motorista.getNome() : null;
	}

}
