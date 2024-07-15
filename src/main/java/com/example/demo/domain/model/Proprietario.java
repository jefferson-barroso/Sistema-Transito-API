package com.example.demo.domain.model;

import com.example.demo.domain.validation.ValidationGroups;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity //mapeando como uma tabela

// caso mudança de nome -> @Table(name = 'tabela proprietario')
public class Proprietario {
	
	@NotNull(groups = ValidationGroups.proprietarioId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private String nome;
	
	private String email;
	
	@Column(name="telefone")
	private String numero; 
	
}
