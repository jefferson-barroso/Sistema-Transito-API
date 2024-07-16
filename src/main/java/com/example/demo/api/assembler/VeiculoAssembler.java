package com.example.demo.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.api.model.VeiculoDTO;
import com.example.demo.domain.model.Veiculo;
import com.example.demo.model.Input.VeiculoInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class VeiculoAssembler {
	private final ModelMapper modelMapper;
	
	public Veiculo toEntity(VeiculoInput veiculoInput) {
		return modelMapper.map(veiculoInput, Veiculo.class);
	}
	
	public VeiculoDTO toDTO(Veiculo veiculo) {
		return modelMapper.map(veiculo, VeiculoDTO.class); 
		
	}
	
	public List<VeiculoDTO> toColletionModel(List<Veiculo> veiculos){
		return veiculos.stream()
				.map(this::toDTO)
				.toList();
		
	}

}
