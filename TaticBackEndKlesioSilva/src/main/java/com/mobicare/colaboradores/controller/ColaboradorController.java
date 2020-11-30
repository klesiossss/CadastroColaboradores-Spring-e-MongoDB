package com.mobicare.colaboradores.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mobicare.colaboradores.service.ColaboradorService;

import com.mobicare.colaboradores.model.*;
import com.mobicare.colaboradores.repository.*;  
import com.mobicare.colaboradores.service.*;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/colaborador")
public class ColaboradorController {


	@Autowired
	private ColaboradorService colaboradorService;
	

	@GetMapping()
	public ResponseEntity<Page<Colaborador>> findAll(@PageableDefault(sort ="nome", size = 20) Pageable pageable) {
		var colaboradores = colaboradorService.findAll(pageable);
		return ResponseEntity.ok(colaboradores);
	}
	
	
	
	@GetMapping("/cpf/{cpf}")
	@ApiOperation("Find the worker by CPF")
	public ResponseEntity<Optional<Colaborador>> findByCpf(@PathVariable String cpf) {
		var colaborador = colaboradorService.findByCpf(cpf);
		return ResponseEntity.ok(colaborador);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Find a user by id")
	public ResponseEntity<Optional<Colaborador>> findById(@PathVariable String id) {
		var user = colaboradorService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	
	@PostMapping
	@ApiOperation("Save a worker")
	public ResponseEntity<Colaborador> save(@RequestBody @Valid Colaborador colaborador) {
		var colaboradorSaved = colaboradorService.save(colaborador);
		var location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(colaboradorSaved.getId()).toUri();
		return ResponseEntity.created(location).body(colaboradorSaved);
	}
	
	
	@DeleteMapping
	@ApiOperation("Delete a worker")
	public ResponseEntity<Colaborador> delete(@RequestBody @Valid Colaborador colaborador) {
		colaboradorService.delete(colaborador);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
}
