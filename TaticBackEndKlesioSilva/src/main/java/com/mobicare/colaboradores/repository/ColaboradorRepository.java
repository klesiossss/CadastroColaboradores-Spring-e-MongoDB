package com.mobicare.colaboradores.repository;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mobicare.colaboradores.model.Colaborador;

@Qualifier("colaborador")
@Repository
public interface ColaboradorRepository extends MongoRepository<Colaborador, String> {
				
		
				Optional<Colaborador>findById(String id);
				Optional<Colaborador>findByCpf(String cpf);			
				
				@Query("{idade : {$lt : ?0}}")
				Page<Colaborador>FindAllMenorDeDezoito(int idade, Pageable pageable);
				
				@Query("{idade : {$gt : ?1}}")
				Page<Colaborador>FindAllMaiorDeSessentaECinco(int idade, Pageable pageable);
				
}
