package com.fleethub.hub.veiculo;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fleethub.hub.abastecimento.Abastecimento;
import com.fleethub.hub.usuario.entity.Motorista;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = { 
		@UniqueConstraint(name = "uk_veiculo_placa", columnNames = "placa"),
		@UniqueConstraint(name = "uk_veiculo_chassi", columnNames = "chassi"),
		@UniqueConstraint(name = "uk_veiculo_motorista_id", columnNames = "motorista_id"),
		@UniqueConstraint(name = "uk_veiculo_renavam", columnNames = "renavam") 
		})
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String placa;
	
	@Column(length = 25, nullable = false)
	private String modelo;
	
	@Column(length = 25, nullable = false)
	private String marca;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal kmAtual;
	
	@Column(nullable = false)
	private Integer capacidade;
	
	@Column(length = 25)
	private String cor;
	
	@Column(length = 25)
	private String anoFabricacao;
	
	@Column(length = 25)
	private String anoModelo;
	
	@Column(length = 25)
	private String chassi;
	
	@Column(length = 25)
	private String renavam;
	
	@Column(length = 25)
	private String tipoCombustivel;
	
	@Column(length = 25)
	private String categoriaVeiculo;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id", referencedColumnName = "id")
	@JsonManagedReference
    private Motorista motorista;
	
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)	
	private List<Abastecimento> abastecimentos;

}
