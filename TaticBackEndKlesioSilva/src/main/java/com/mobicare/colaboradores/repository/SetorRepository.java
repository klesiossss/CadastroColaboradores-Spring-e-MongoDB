package com.mobicare.colaboradores.repository;

import java.io.Serial;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mobicare.colaboradores.model.Colaborador;
import com.mobicare.colaboradores.model.Setor;

@Qualifier("setor")
@Repository
public interface SetorRepository extends MongoRepository<Setor, String> {

		
		 Optional<Setor> findById(String id);	
		 Optional<Setor>findByNomeSetorIgnoreCase(String nome);
		 		
				
}
