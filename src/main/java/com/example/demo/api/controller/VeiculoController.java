package com.example.demo.api.controller;

import java.util.List;

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

import com.example.demo.api.assembler.VeiculoAssembler;
import com.example.demo.api.model.VeiculoDTO;
import com.example.demo.domain.model.Veiculo;
import com.example.demo.domain.repository.VeiculoRepository;
import com.example.demo.domain.service.ApreensaoVeiculoService;
import com.example.demo.domain.service.RegistroVeiculoService;
import com.example.demo.model.Input.VeiculoInput;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	private VeiculoRepository veiculoRepository;
	
	private RegistroVeiculoService registroVeiculoService;
	
	private VeiculoAssembler veiculoAssembler;
	
	private  ApreensaoVeiculoService apreensaoVeiculoService;
	
	@GetMapping
	public List<VeiculoDTO> listar(){
		return veiculoAssembler.toColletionModel(veiculoRepository.findAll());
	}
	
	@GetMapping("/{veiculoId}")
	public ResponseEntity<VeiculoDTO> buscar(@PathVariable Long veiculoId) {
		return veiculoRepository.findById(veiculoId)
				.map(veiculo -> veiculoAssembler.toDTO(veiculo))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VeiculoDTO cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) {
		
		Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
		Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);
		
		return veiculoAssembler.toDTO(veiculoCadastrado);
		
	}
	
    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.apreender(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreensao(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.removerApreensao(veiculoId);
    }

}
