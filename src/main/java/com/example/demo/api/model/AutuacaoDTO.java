package com.example.demo.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.example.demo.domain.model.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutuacaoDTO {

	private Veiculo veiculo; 
	
	private String descricao; 
	
	private BigDecimal valorMulta; 
	
	private OffsetDateTime dataOcorrencia;

	
}
