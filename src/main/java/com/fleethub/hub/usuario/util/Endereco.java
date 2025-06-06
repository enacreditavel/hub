package com.fleethub.hub.usuario.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
	@Column(length = 50)
	private String logradouro;
	
	@Column(length = 6)
	private Integer numero;
	
	@Column(length = 50)
	private String bairro;
	
	@Column(length = 50)
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Column(length = 10)
	private String CEP;
	
	public String formatarEnderecoCompleto() {
	    return String.format("%s, %d - %s, %s - %s, CEP: %d",
	            logradouro,
	            numero,
	            bairro,
	            cidade,
	            estado,
	            CEP);
	}

}
