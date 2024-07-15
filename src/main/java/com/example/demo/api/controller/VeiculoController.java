package com.example.demo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Veiculo;
import com.example.demo.domain.repository.VeiculoRepository;
import com.example.demo.domain.service.RegistroVeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	private VeiculoRepository veiculoRepository;
	
	private RegistroVeiculoService registroVeiculoService;
	
	@GetMapping
	public List<Veiculo> listar(){
		return veiculoRepository.findAll();
	}
	
	@GetMapping("/{veiculoId}")
	public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId) {
		return veiculoRepository.findById(veiculoId).
				map(ResponseEntity::ok).
				orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
		return registroVeiculoService.cadastrar(veiculo);
		
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<String> capturar(NegocioException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
