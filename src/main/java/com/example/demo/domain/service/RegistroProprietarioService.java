package com.example.demo.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Proprietario;
import com.example.demo.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

//caso exista uma regra de negocio futuramente, será implementada aqui

@AllArgsConstructor
@Service
public class RegistroProprietarioService {
	
	private final ProprietarioRepository proprietarioRepository;
	
	@Transactional //caso alguma operação no BD de errado, rollback
	public Proprietario salvar(Proprietario proprietario) {
		return proprietarioRepository.save(proprietario);
	}
	
	@Transactional
	public void excluir (Long proprietarioId) {
		proprietarioRepository.deleteById(proprietarioId);
	}
	

}
