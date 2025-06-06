package com.fleethub.hub.abastecimento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long>{
	
	List<Abastecimento> findByVeiculoId(Long veiculoId);

	List<Abastecimento> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);

}
