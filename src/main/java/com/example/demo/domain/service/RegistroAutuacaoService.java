package com.example.demo.domain.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Autuacao;
import com.example.demo.domain.model.Veiculo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {
	
	private RegistroVeiculoService registroVeiculoService; 
	
	@Transactional
	public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
		return veiculo.adicionarAutuacao(novaAutuacao);
	}

}
