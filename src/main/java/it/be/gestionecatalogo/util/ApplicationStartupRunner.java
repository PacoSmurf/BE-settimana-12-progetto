package it.be.gestionecatalogo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.repository.AutoreRepository;
import it.be.gestionecatalogo.repository.CategoriaRepository;
import it.be.gestionecatalogo.repository.LibroRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private AutoreRepository autorerepository;
	@Autowired
	private CategoriaRepository categoriarepository;
	@Autowired
	private LibroRepository librorepository;

	@Override
	public void run(String... args) throws Exception {
		
		Autore autore = new Autore();
		autore.setNome("giovanni");
		autore.setCognome("eossi");
		autorerepository.save(autore);
		
		
		Categoria categoria = new Categoria();
	    categoria.setNome("horror");
	    categoriarepository.save(categoria);
		
	
	    Libro libro = new Libro();
	    libro.setTitolo("pippo");
	    libro.setAnnoPubblicazione(2000);
	    libro.setPrezzo(14.59);
	    libro.getAutori().add(autore);
	    libro.getCategorie().add(categoria);
	    librorepository.save(libro);
	    
	    
	    Libro libro2 = new Libro();
	    libro2.setTitolo("cucu");
	    libro2.setAnnoPubblicazione(2004);
	    libro2.setPrezzo(14.59);
	    libro2.getAutori().add(autore);
	    libro2.getCategorie().add(categoria);
	    librorepository.save(libro2);
	    
	    Autore autore2 = new Autore();
		autore2.setNome("mimmo");
		autore2.setCognome("titti");
		autorerepository.save(autore2);
		
		Categoria categoria2 = new Categoria();
	    categoria2.setNome("fantascienza");
	    categoriarepository.save(categoria2);
	    
	    Libro libro3 = new Libro();
	    libro3.setTitolo("paco");
	    libro3.setAnnoPubblicazione(2006);
	    libro3.setPrezzo(13.50);
	    libro3.getAutori().add(autore);
	    libro3.getCategorie().add(categoria);
	    librorepository.save(libro3);
		
	    
		
		
		
		
		

	}

}
