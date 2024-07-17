package com.example.demo.model.Input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutuacaoInput {
	
	@NotBlank
	private String descricao; 
	
	@NotNull
	@Positive
	private BigDecimal valorMulta; 
}
