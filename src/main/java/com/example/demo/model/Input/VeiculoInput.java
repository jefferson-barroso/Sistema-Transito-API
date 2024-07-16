package com.example.demo.model.Input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoInput {
	
	@NotBlank
	private String marca; 

	
	@NotBlank
	@Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z]{2}")
	private String placa;
	
	 //private Long proprietarioId;
	
	private ProprietarioInput proprietario;
	
}
 