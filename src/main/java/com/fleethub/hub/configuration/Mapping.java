package com.fleethub.hub.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleethub.hub.abastecimento.Abastecimento;
import com.fleethub.hub.abastecimento.AbastecimentoDTO;
import com.fleethub.hub.usuario.DTO.AdministradorDTO;
import com.fleethub.hub.usuario.DTO.MotoristaDTO;
import com.fleethub.hub.usuario.DTO.PassageiroDTO;
import com.fleethub.hub.usuario.DTO.UsuarioDTO;
import com.fleethub.hub.usuario.entity.Administrador;
import com.fleethub.hub.usuario.entity.Motorista;
import com.fleethub.hub.usuario.entity.Passageiro;
import com.fleethub.hub.usuario.entity.Usuario;
import com.fleethub.hub.veiculo.Veiculo;
import com.fleethub.hub.veiculo.VeiculoDTO;
import com.fleethub.hub.viagem.Viagem;
import com.fleethub.hub.viagem.ViagemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class Mapping {
	
	@Autowired
	private final ModelMapper modelMapper;
	
	/* 
	 * 
	 * 
	 * MAPEAMENTOS PARA CLASSE VIAGEM
	 *  
	 * 
	 * 
	 */
	
	// Viagem
	
	 public ViagemDTO mapViagemToDTO(Viagem viagem) {
		 return modelMapper.map(viagem, ViagemDTO.class);
		 	 }
	 
	 public Viagem mapViagemToEntity(ViagemDTO viagemDTO) {
		 return modelMapper.map(viagemDTO, Viagem.class);
	 }
	 
	 public List<ViagemDTO> mapListViagemDTO(List<Viagem> viagens) {
		 return viagens.stream().map(viagem -> mapViagemToDTO(viagem)).toList();
	 }
	
	/* 
	 * 
	 * 
	 * MAPEAMENTOS PARA CLASSE VEÍCULO E ABASTECIMENTO
	 *  
	 * 
	 * 
	 */
	
	//Veículo
	
	public VeiculoDTO mapVeiculoToDTO(Veiculo veiculo) {
		return modelMapper.map(veiculo, VeiculoDTO.class);
	}
	
	public Veiculo mapVeiculoToEntity(VeiculoDTO veiculoDTO) {
		return modelMapper.map(veiculoDTO, Veiculo.class);
	}
	
	public List<VeiculoDTO> mapListVeiculoDTO(List<Veiculo> veiculos) {
		return veiculos.stream().map(veiculo -> mapVeiculoToDTO(veiculo)).toList();
	}
	
	//Abastecimento
	
	public AbastecimentoDTO mapAbastecimentoToDTO(Abastecimento abastecimento) {
		return modelMapper.map(abastecimento, AbastecimentoDTO.class);
	}
	
	public Abastecimento mapAbastecimentoToEntity(AbastecimentoDTO abastecimentoDTO) {
		return modelMapper.map(abastecimentoDTO, Abastecimento.class);
	}
	
	public List<AbastecimentoDTO> mapListAbastecimentoDTO(List<Abastecimento> abastecimentos) {
		return abastecimentos.stream().map(abastecimento -> mapAbastecimentoToDTO(abastecimento)).toList();
	}
	
	
	
	/* 
	 * 
	 * 
	 * 
	 * MAPEAMENTOS PARA CLASSE USUÁRIOS E SUAS SUBCLASSES
	 * 
	 * 
	 * 
	 */
	
	//Motorista
	
	public MotoristaDTO mapMotoristaToDTO(Motorista motorista) {
		return modelMapper.map(motorista, MotoristaDTO.class);
	}
	
	public Motorista mapMotoristaToEntity(MotoristaDTO motoristaDTO) {
		return modelMapper.map(motoristaDTO, Motorista.class);
	}
	
	public List<MotoristaDTO> mapListMotoristaDTO(List<Motorista> motoristas) {
		return motoristas.stream().map(motorista -> mapMotoristaToDTO(motorista)).toList();
	}
	
	//Passageiro
	
	public PassageiroDTO mapPassageiroToDTO(Passageiro passageiro) {
		return modelMapper.map(passageiro, PassageiroDTO.class);
	}
	
	public Passageiro mapPassageiroToEntity(PassageiroDTO passageiroDTO) {
		return modelMapper.map(passageiroDTO, Passageiro.class);
	}
	
	public List<PassageiroDTO> mapListPassageiroDTO(List<Passageiro> passageiros) {
		return passageiros.stream().map(passageiro -> mapPassageiroToDTO(passageiro)).toList();
	}
	
	// Administrador
	
	public AdministradorDTO mapAdministradorToDTO(Administrador administrador) {
		return modelMapper.map(administrador, AdministradorDTO.class);
	}
	
	public Administrador mapAdministradorToEntity(AdministradorDTO administradorDTO) {
		return modelMapper.map(administradorDTO, Administrador.class);
	}
	
	public List<AdministradorDTO> mapListAdministradorDTO(List<Administrador> administradores) {
		return administradores.stream().map(administrador -> mapAdministradorToDTO(administrador)).toList();
	}
	
	
	// MÉTODOS DE MAPEAMENTO PARA DTOs

	// Esse método mapeia um usuário para um DTO específico baseado em sua classe
	public UsuarioDTO mapUsuarioToDTO(Usuario usuario) {
		return modelMapper.map(usuario, usuarioDtoMappingClass().getOrDefault(usuario.getClass(), UsuarioDTO.class));
	}

	// Esse método especifica qual classe de DTO deve ser usada para cada tipo de
	// usuário
	public Map<Class<?>, Class<? extends UsuarioDTO>> usuarioDtoMappingClass() {
		Map<Class<?>, Class<? extends UsuarioDTO>> dtoMapping = new HashMap<>();
		dtoMapping.put(Administrador.class, AdministradorDTO.class);
		dtoMapping.put(Motorista.class, MotoristaDTO.class);
		dtoMapping.put(Passageiro.class, PassageiroDTO.class);
		return dtoMapping;
	}

	// MÉTODOS DE MAPEAMENTO PARA ENTITYs

	// Esse método mapeia um usuário para uma Entity específica baseado em sua
	// classe
	public Usuario mapUsuarioToEntity(UsuarioDTO usuarioDTO) {
		return modelMapper.map(usuarioDTO,
				usuarioEntityMappingClass().getOrDefault(usuarioDTO.getClass(), Usuario.class));
	}

	// Esse método especifica qual classe de Entity deve ser usada para cada tipo de
	// usuário
	public Map<Class<?>, Class<? extends Usuario>> usuarioEntityMappingClass() {
		Map<Class<?>, Class<? extends Usuario>> entityMapping = new HashMap<>();
		entityMapping.put(AdministradorDTO.class, Administrador.class);
		entityMapping.put(MotoristaDTO.class, Motorista.class);
		entityMapping.put(PassageiroDTO.class, Passageiro.class);
		return entityMapping;
	}

}
