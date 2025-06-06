package com.fleethub.hub.veiculo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fleethub.hub.usuario.entity.Motorista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {
	
	private Long id;
	
	@NotBlank(message = "A placa do veículo é obrigatória")
	private String placa;
	
	@NotNull(message = "Informe a quilometragem atual do veículo")
	private BigDecimal kmAtual;
	
	@NotBlank(message = "O modelo é obrigatório")
	private String modelo;
	
	@NotBlank(message = "A marca é obrigatório")
	private String marca;
	
	private String cor;
	
	private String anoFabricacao;
	
	private String anoModelo;
	
	@NotBlank(message = "A numeração do chassi é obrigatória")
	private String chassi;
	
	@NotBlank(message = "O renavam é obrigatório")
	private String renavam;
	
	private String tipoCombustivel;
	
	private String categoriaVeiculo;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Motorista motorista;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String motoristaNome;
	
	private String getMotoristaNome() {
		return this.motorista != null ? motorista.getNome() : null;
	}

}

