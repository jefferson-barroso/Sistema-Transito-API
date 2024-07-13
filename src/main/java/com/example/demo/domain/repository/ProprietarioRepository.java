package com.example.demo.domain.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Proprietario;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>{
	// prefixo: find , demilitador:By , expressao: Nome (varia), se tiver partes -> + Containing
	List<Proprietario> findByNome(String nome); 
	Optional<Proprietario> findByEmail(String email);

}
