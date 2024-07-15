package com.example.demo.domain.model;

import java.time.LocalDateTime;

import com.example.demo.domain.validation.ValidationGroups;
import com.example.demo.enums.StatusVeiculo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
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
	@ConvertGroup(from = Default.class, to = ValidationGroups.proprietarioId.class)
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
}
