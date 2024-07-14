package com.example.demo.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Proprietario;
import com.example.demo.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

//caso exista uma regra de negocio futuramente, será implementada aqui

@AllArgsConstructor
@Service
public class RegistroProprietarioService {
	
	private final ProprietarioRepository proprietarioRepository;
	
	public Proprietario buscar(Long proprietarioId) {
		//resolvendo questão do null no retorno da requisição nos nomes de um proprietario; 
		return  proprietarioRepository.findById(proprietarioId)
				.orElseThrow(() -> new NegocioException("Proprietário não encontrado!"));
	}
	
	@Transactional //caso alguma operação no BD de errado, rollback
	public Proprietario salvar(Proprietario proprietario) {
		boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail()).
			filter(p->!p.equals(proprietario)).isPresent();
		
		if (emailEmUso) {
			throw new NegocioException("Ja existe cadastro pra esse email!");
		}
		
		return proprietarioRepository.save(proprietario);
	}
	
	@Transactional
	public void excluir (Long proprietarioId) {
		proprietarioRepository.deleteById(proprietarioId);
	}
	

}
