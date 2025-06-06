package com.fleethub.hub.usuario.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class Passageiro extends Usuario {
	
	@Override
	public String getRole() {
	    return "ROLE_PASSAGEIRO";
	}
	
	private String contato;
	
}
