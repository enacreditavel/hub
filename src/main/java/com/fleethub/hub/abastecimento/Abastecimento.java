package com.fleethub.hub.abastecimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.veiculo.Veiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Abastecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime data;
	
	@Column(length = 25, nullable = false)
	private String local;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	
	@Column(nullable = false)
	private BigDecimal litros;
	
	
	private BigDecimal kmAtualVeiculo;
	
	@Column(length = 25, nullable = false)	
	private String tipoCombustivel;
	
	@ManyToOne
	@JoinColumn(name = "motorista_id", nullable = false)
	private Motorista motorista;
	
	@ManyToOne
	@JoinColumn(name = "veiculo_id", nullable = false)
	private Veiculo veiculo;

}
