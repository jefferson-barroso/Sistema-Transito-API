package com.example.demo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.assembler.AutuacaoAssembler;
import com.example.demo.api.model.AutuacaoDTO;
import com.example.demo.domain.model.Autuacao;
import com.example.demo.domain.model.Veiculo;
import com.example.demo.domain.service.RegistroAutuacaoService;
import com.example.demo.domain.service.RegistroVeiculoService;
import com.example.demo.model.Input.AutuacaoInput;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {
	
	private final AutuacaoAssembler autuacaoAssembler;
	
	private final RegistroAutuacaoService registroAutuacaoService;
	
	private final RegistroVeiculoService registroVeiculoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AutuacaoDTO registrar(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInput autuacaoInput) {
		
		Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput); 
		Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, novaAutuacao);
		return autuacaoAssembler.toDTO(autuacaoRegistrada);
	}
	
	@GetMapping
	public List<AutuacaoDTO> listar(@PathVariable Long veiculoId ){
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
		return autuacaoAssembler.toCollectionModel(veiculo.getAutoacoes());
	}
}
