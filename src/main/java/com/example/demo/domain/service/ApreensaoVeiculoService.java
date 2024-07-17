package com.example.demo.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Veiculo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {
	
	private final RegistroVeiculoService registroVeiculoService; 
	
	@Transactional
	public void apreender(Long VeiculoId) {
		Veiculo veiculo = registroVeiculoService.buscar(VeiculoId);
		veiculo.apreender();
	}
	
	@Transactional
	public void removerApreensao(Long veiculoId) {
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
		veiculo.removerApreensao(); 
	}
}
