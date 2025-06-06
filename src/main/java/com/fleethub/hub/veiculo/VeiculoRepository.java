package com.fleethub.hub.veiculo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	Optional<Veiculo> findByPlaca(String placa);

}
