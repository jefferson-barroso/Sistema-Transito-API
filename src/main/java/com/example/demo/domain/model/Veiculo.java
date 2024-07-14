package com.example.demo.domain.model;

import java.time.LocalDateTime;

import com.example.demo.enums.StatusVeiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	//@JoinColumn(name="proprietario_id")
	private Proprietario proprietario; 
	
	private String marca;
	
	private String placa; 
	
	@Enumerated(EnumType.STRING)
	private StatusVeiculo status; 
	
	private LocalDateTime dataCadastro; 
	
	private LocalDateTime dataApreensao; 
}
