package com.example.demo.api.model;

import java.time.OffsetDateTime;

import com.example.demo.enums.StatusVeiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO {

		private Long id;
		private String nomeProprietario; 
		private String marca;
		private String placa; 
		private StatusVeiculo status; 
		private OffsetDateTime dataCadastro; 
		private OffsetDateTime dataApreensao; 
	}

	
	

