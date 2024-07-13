package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Proprietario;
import com.example.demo.domain.repository.ProprietarioRepository;
import com.example.demo.domain.service.RegistroProprietarioService;

import lombok.AllArgsConstructor;


@AllArgsConstructor //notação lombok p/ construtora no repositoru
@RestController
@RequestMapping("/proprietarios")
public class PropritarioController {
	

	private final RegistroProprietarioService registroProprietarioService;
	
	
	private final ProprietarioRepository proprietarioRepository;
	



	@GetMapping
	public List<Proprietario> listar(){
		return proprietarioRepository.findAll();
	}
	
	@GetMapping("/{proprietarioId}")
	public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){
		
		return proprietarioRepository.findById(proprietarioId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
								//Request serializa e desserializa o q for inserido em Json
								//coloca como está no corpo da requisição
	public Proprietario adicionar(@RequestBody Proprietario proprietario) {
		return registroProprietarioService.salvar(proprietario);
		//return proprietarioRepository.save(proprietario);
		
	}
	
	@PutMapping("/{proprietarioId}")
	public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId, @RequestBody Proprietario proprietario){
		
		if(!proprietarioRepository.existsById(proprietarioId)) {
			return ResponseEntity.notFound().build();
		}
		proprietario.setId(proprietarioId);
		Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);
		
		return ResponseEntity.ok(proprietarioAtualizado);
	}
	
	@DeleteMapping("/{proprietarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
		if(!proprietarioRepository.existsById(proprietarioId)) {
			return ResponseEntity.notFound().build(); 
			
		}
		
		//proprietarioRepository.deleteById(proprietarioId);
		 registroProprietarioService.excluir(proprietarioId);
		 return ResponseEntity.noContent().build();
	}
	
}
