package com.example.demo.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.api.model.AutuacaoDTO;
import com.example.demo.domain.model.Autuacao;
import com.example.demo.model.Input.AutuacaoInput;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AutuacaoAssembler {
	
	private final ModelMapper modelMapper;
	
	public Autuacao toEntity(AutuacaoInput autuacaoInput) {
		return modelMapper.map(autuacaoInput, Autuacao.class);
	}
	
	public AutuacaoDTO toDTO(Autuacao autuacao) {
		return modelMapper.map(autuacao, AutuacaoDTO.class);
	}
	
	public List<AutuacaoDTO> toCollectionModel(List<Autuacao> autuacoes){
		return autuacoes.stream()
				.map(this::toDTO)
				.toList(); 
	}
}
