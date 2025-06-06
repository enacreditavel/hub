package com.fleethub.hub.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleethub.hub.usuario.entity.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

	Optional<Motorista> findByCnh(Long cnh);
	
}
