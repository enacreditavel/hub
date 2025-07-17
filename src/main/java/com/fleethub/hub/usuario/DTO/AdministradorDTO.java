package com.fleethub.hub.usuario.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdministradorDTO extends UsuarioDTO {
	private String tipo = "ADMINISTRADOR";
	private String cargo;

}
