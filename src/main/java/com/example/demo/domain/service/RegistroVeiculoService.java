package com.example.demo.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Proprietario;
import com.example.demo.domain.model.Veiculo;
import com.example.demo.domain.repository.ProprietarioRepository;
import com.example.demo.domain.repository.VeiculoRepository;
import com.example.demo.enums.StatusVeiculo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {
	
	private final VeiculoRepository veiculoRepository;
	
	private final RegistroProprietarioService registroProprietarioService;
	
	public Veiculo buscar(Long veiculoId) {
		return veiculoRepository.findById(veiculoId)
				.orElseThrow(()->new NegocioException("Veiculo não encontrado"));
	}
	
	@Transactional
	public Veiculo cadastrar( Veiculo novoVeiculo) {
		if(novoVeiculo.getId() != null) {
			throw new NegocioException("Veiculo a ser cadastrado nao deve possuir codigo");
		}
		
		
		boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
				.filter(veiculo -> !veiculo.equals(novoVeiculo))
				.isPresent();
		
		if(placaEmUso) {
			throw new NegocioException("Placa em Uso");
		}

		Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());
		//esses topicos serão gerados pelo proprio sistemas, nn vai ser inserido
		novoVeiculo.setProprietario(proprietario);
		novoVeiculo.setStatus(StatusVeiculo.REGULAR);
		novoVeiculo.setDataCadastro(LocalDateTime.now());
		
	
		
		return veiculoRepository.save(novoVeiculo);
	}
}
