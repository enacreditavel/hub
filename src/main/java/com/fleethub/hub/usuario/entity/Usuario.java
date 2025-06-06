package com.fleethub.hub.usuario.entity;

import com.fleethub.hub.usuario.util.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario")
@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_usuario_cpf", columnNames = "cpf"),
		@UniqueConstraint(name = "uk_usuario_email", columnNames = "email") })
public abstract class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 15, nullable = false)
	private String cpf;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Embedded
	private Endereco endereco;

	public abstract String getRole();

}
