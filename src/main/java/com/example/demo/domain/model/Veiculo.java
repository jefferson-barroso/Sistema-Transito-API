package com.example.demo.domain.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.enums.StatusVeiculo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	
	@Valid
	@NotNull
	@ManyToOne
	//@JoinColumn(name="proprietario_id")
	private Proprietario proprietario; 
	
	@NotBlank
	private String marca;
	
	@NotBlank
	//Padrão de placa
	//XXX0000
	//XXX0X00
	@Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z]{2}")
	private String placa; 
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusVeiculo status; 
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataCadastro; 
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataApreensao; 
	
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
	private List<Autuacao> autoacoes = new ArrayList<>();
	
	public Autuacao adicionarAutuacao(Autuacao autuacao) {
		autuacao.setDataOcorrencia(OffsetDateTime.now());
		autuacao.setVeiculo(this);
		getAutoacoes().add(autuacao);
		return autuacao; 
	}
	
	public void apreender() {
		
		if (estaApreendido()) {
			
			throw new NegocioException("Este veiculo já está apreendido.");
		}
		
		setStatus(StatusVeiculo.APRENDIDO);
		setDataApreensao(OffsetDateTime.now());
		
	}
	
	public void removerApreensao() {
		if (naoEstaApreendido()) {
			throw new NegocioException("Veiculo não está apreendido!"); 
		}
		
		setStatus(StatusVeiculo.REGULAR);
		setDataApreensao(null);
			

		
	}
	
	public boolean estaApreendido() {
			return StatusVeiculo.APRENDIDO.equals(getStatus());
		}
	
	public boolean naoEstaApreendido() {
		return !estaApreendido();
	}
		
	
	
}