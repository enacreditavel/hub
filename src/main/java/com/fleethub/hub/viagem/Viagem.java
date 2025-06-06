package com.fleethub.hub.viagem;

import java.time.LocalDateTime;
import java.util.List;

import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.usuario.entity.Passageiro;
import com.fleethub.hub.veiculo.Veiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataInicio;
    
    private LocalDateTime dataFim;

    private String origem;
    
    private String destino;

    private Integer kmInicial;
    
    private Integer kmFinal;

    @Enumerated(EnumType.STRING)
    private StatusViagem status;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
   
    @ManyToMany
    @JoinTable(
        name = "viagem_passageiros",
        joinColumns = @JoinColumn(name = "viagem_id"),
        inverseJoinColumns = @JoinColumn(name = "passageiro_id")
    
    )
    private List<Passageiro> passageiros;
}