package com.mobicare.colaboradores.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Colaborador {
	
	@Id
	private String id;
	
	@NotBlank(message = "esse campo é mandatório ser preenchido")
	private String nome;
	
	private int idade;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDate dataNascimento;
	
	@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@Valid
	@NotBlank(message = "Esse campo é mandatório ser preenchido")
	private String email;
	
	@Valid
	private Setor setor;

}
