package com.fleethub.hub.usuario.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fleethub.hub.abastecimento.Abastecimento;
import com.fleethub.hub.usuario.util.CategoriaCNH;
import com.fleethub.hub.veiculo.Veiculo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_motorista_cnh", columnNames = "cnh")})
public final class Motorista extends Usuario {

    private Long cnh;
    
    @Enumerated(EnumType.STRING)
    private CategoriaCNH categoriaCNH;
    
    @JsonBackReference
    @OneToOne(mappedBy = "motorista", optional = true)
    private Veiculo veiculo;
    
    @Override
    public String getRole() {
    	return "ROLE_MOTORISTA";
    }
    
    @OneToMany(mappedBy = "motorista", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Abastecimento> abastecimentosRealizados;

}
