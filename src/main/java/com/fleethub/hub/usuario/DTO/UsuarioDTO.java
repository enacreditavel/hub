package com.fleethub.hub.usuario.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fleethub.hub.usuario.util.Endereco;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(	
		use = JsonTypeInfo.Id.NAME, 		// Usa o nome da subclasse como identificador
		include = JsonTypeInfo.As.PROPERTY, // Inclui como propriedade no JSON
		property = "tipo"					// Nome da propriedade que indica o tipo
)
@JsonSubTypes({ 
		@JsonSubTypes.Type(value = AdministradorDTO.class, name = "ADMINISTRADOR"),
		@JsonSubTypes.Type(value = MotoristaDTO.class, name = "MOTORISTA"),
		@JsonSubTypes.Type(value = PassageiroDTO.class, name = "PASSAGEIRO") })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O CPF é obrigatório")
	private String cpf;

	@NotBlank(message = "O email é obrigatório")
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "A senha é obrigatória")
	private String senha;

	private Endereco endereco;

}
