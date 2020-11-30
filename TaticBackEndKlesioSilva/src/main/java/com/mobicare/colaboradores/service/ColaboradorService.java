package com.mobicare.colaboradores.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mobicare.colaboradores.exception.*;



import java.util.Optional;

import com.mobicare.colaboradores.repository.ColaboradorRepository;
import com.mobicare.colaboradores.repository.SetorRepository;
import com.mobicare.colaboradores.model.Colaborador;
import com.mobicare.colaboradores.model.Setor;
import lombok.AllArgsConstructor;



@Service
public class ColaboradorService {

	public ColaboradorService(@Qualifier("colaborador")ColaboradorRepository colaboradorRepository) {
		this.colaboradorRepository = colaboradorRepository;
	}
	
	@Autowired
	ColaboradorRepository colaboradorRepository;
	
	@Autowired
	SetorRepository setorRepository;

	public Optional<Colaborador> findById(String id) {
			return colaboradorRepository.findById(id);
	}
	
	public Optional<Colaborador> findByCpf( String cpf) {
			return colaboradorRepository.findByCpf(cpf);
	}
	
	public Page<Colaborador>findAll(Pageable pageable){		
			return colaboradorRepository.findAll(pageable);
	}
	
	
public Colaborador save(Colaborador colaborador) {
		
		if(colaborador.getId() != null || setorRepository.findByNomeSetorIgnoreCase(colaborador.getNome()).isPresent()) throw new DuplicatedResourceException();
		 var setorAux = setorRepository.findByNomeSetorIgnoreCase(colaborador.getSetor().getNomeSetor()).orElseThrow(() -> new ResourceNotFoundException()); 
		 	
		long numTotalColaboradores = setorAux.getColaboradores().stream().count();
		long numMenor18Anos = setorAux.getColaboradores().stream().filter(c-> c.getIdade() < 18).count();
		long numMaior65Anos = setorAux.getColaboradores().stream().filter(c->c.getIdade()>65).count();
		
		long percentualMenor18Anos = (numMenor18Anos/numTotalColaboradores)*100;
		long percentualMaior65Anos = (numMaior65Anos/numTotalColaboradores)*100;
		
		
		if(colaborador.getIdade()>18 && colaborador.getIdade() < 65) 
			    colaboradorRepository.save(colaborador);
		
		else if (colaborador.getIdade() <18 && percentualMenor18Anos < 20)
			    colaboradorRepository.save(colaborador);
		else if(colaborador.getIdade() > 65 && percentualMaior65Anos < 20 )
				colaboradorRepository.save(colaborador);
		else
			throw new BusinessException("There is no more place, the percentage allowed for you got exceded");	
						
		return colaborador;
}
	
	
	 
public void delete(Colaborador colaborador) {
	var colaboradorSaved = colaboradorRepository.findById(colaborador.getId()).orElseThrow(() -> new ResourceNotFoundException());
	
	colaboradorRepository.deleteById(colaboradorSaved.getId());
}
	
	
	
	
	
	
	
	
	
	
}
