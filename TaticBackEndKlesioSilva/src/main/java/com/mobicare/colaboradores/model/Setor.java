package com.mobicare.colaboradores.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Setor {
	
	@Id
	private String id;
	
	@NotBlank(message = "Este campo é obrigatório")
	private String nomeSetor;
	
	@NotBlank(message = "Fill in the description field")
	@Size(min = 50, message = "description should have at least 50 characters")
	private String descricao;
	
	
	private List<Colaborador> colaboradores;
		
}
