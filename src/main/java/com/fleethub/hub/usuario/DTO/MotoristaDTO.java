package com.fleethub.hub.usuario.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fleethub.hub.usuario.util.CategoriaCNH;
import com.fleethub.hub.veiculo.Veiculo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MotoristaDTO extends UsuarioDTO {
	
	@NotNull(message = "A CNH é obrigatória")
	private Long cnh;

	private CategoriaCNH categoriaCNH;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Veiculo veiculo;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String veiculoPlaca;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String veiculoModelo;
	
	private String getVeiculoPlaca() {
		return this.veiculo != null ? veiculo.getPlaca() : null;
	}
	
	private String getVeiculoModelo() {
		return this.veiculo != null ? veiculo.getModelo() : null;
	}

}
