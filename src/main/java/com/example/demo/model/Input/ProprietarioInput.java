package com.example.demo.model.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioInput {
	@NotNull
	private Long id; 
}
