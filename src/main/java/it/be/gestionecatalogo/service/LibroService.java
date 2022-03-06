package it.be.gestionecatalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exception.LibroException;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.repository.LibroRepository;

@Service
public class LibroService {
	@Autowired
	LibroRepository librorepository;

	public Optional<Libro> findById(Long id) {
		return librorepository.findById(id);
	}

	public List<Libro> findAll() {
		return librorepository.findAll();
	}

	public Optional<Libro> findByLibroId(Long id) {
		return librorepository.findById(id);
	}

	public Libro saveLibro(Libro libro) {
		return librorepository.save(libro);
	}

	public Libro updateLibro(Libro libro, Long id) {
		Optional<Libro> trovato = librorepository.findById(id);
		if (trovato.isPresent()) {
			Libro update = trovato.get();
			update.setTitolo(libro.getTitolo());
			update.setPrezzo(libro.getPrezzo());
			update.setAnnoPubblicazione(libro.getAnnoPubblicazione());
			update.setAutori(libro.getAutori());
			update.setCategorie(libro.getCategorie());
			librorepository.save(update);
			return update;

		} else {
			throw new LibroException("ERRORE");
		}

	}

	public void delete(Long id) {
		librorepository.deleteById(id);

	}

	public Libro save(Libro libro) {
		return librorepository.save(libro);
	}
	public void deleteLibriNoAutori() {
        List<Libro> all = librorepository.findAll();
        List<Libro> libriNoAutori = new ArrayList<>();
        for(Libro libro : all) {
            if(libro.getAutori().isEmpty()) {
                libriNoAutori.add(libro);
            }
        }
        librorepository.deleteAll(libriNoAutori);

    }

}
