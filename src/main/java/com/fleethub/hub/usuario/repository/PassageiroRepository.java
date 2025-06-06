package com.fleethub.hub.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleethub.hub.usuario.entity.Passageiro;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

}
