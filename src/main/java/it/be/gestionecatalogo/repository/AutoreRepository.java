package it.be.gestionecatalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.gestionecatalogo.model.Autore;



public interface AutoreRepository extends JpaRepository<Autore, Long> {
	
	List<Autore> findByNome(String nome);

}
