package com.fleethub.hub.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleethub.hub.usuario.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
	// Métodos específicos para o repositório de Administrador podem ser adicionados aqui

}
