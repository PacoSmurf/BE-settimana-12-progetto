package it.be.gestionecatalogo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.be.gestionecatalogo.model.Libro;

public interface LibroRepository  extends JpaRepository<Libro, Long>{
	
	@Query("SELECT l from Libro l, Autore a WHERE a.id =?2")
	Page<Libro> findByIdAutori(Long id,Pageable pageable);
	


}
