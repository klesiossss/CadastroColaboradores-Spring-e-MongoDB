package com.mobicare.colaboradores.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mobicare.colaboradores.exception.*;



import java.util.Optional;

import com.mobicare.colaboradores.repository.SetorRepository;
import com.mobicare.colaboradores.model.Colaborador;
import com.mobicare.colaboradores.model.Setor;
import lombok.AllArgsConstructor;




@Service
public class SetorService {

	
	public SetorService(SetorRepository setorRepository) {
		this.setorRepository = setorRepository;
	}
	
	
	@Autowired
	SetorRepository setorRepository;

	public Page<Setor> findAll(Pageable pageable) {
		return setorRepository.findAll(pageable);
	}
	
	public Optional<Setor> findById(String id) {
			return setorRepository.findById(id);
	}
	
	public Setor save(Setor setor){
		if(setor.getId() != null || setorRepository.findByNomeSetorIgnoreCase(setor.getNomeSetor()).isPresent()) throw new DuplicatedResourceException();
		
		setorRepository.save(setor);
		
		return setor;
	}
	
	

	public void delete(Setor setor) {
		var setorSaved = setorRepository.findById(setor.getId()).orElseThrow(() -> new ResourceNotFoundException());
		
		setorRepository.deleteById(setorSaved.getId());
	}			
}


