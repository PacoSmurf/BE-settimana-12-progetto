package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.be.gestionecatalogo.exception.LibroException;
import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.repository.AutoreRepository;

@Service
public class AutoriService {
	@Autowired
	AutoreRepository autorepository;

	public Optional<Autore> findById(Long id) {
		return autorepository.findById(id);

	}

	public List<Autore> findALL() {
		return autorepository.findAll();
	}

	public Autore save(Autore autore) {
		return autorepository.save(autore);
	}

	public List<Autore> findByNome(String nome) {
		return autorepository.findByNome(nome);
	}

	public Autore update(Long id, Autore autore) {
		Optional<Autore> check = autorepository.findById(id);
		if (check.isPresent()) {
			Autore autoreUpdate = check.get();
			autoreUpdate.setNome(autore.getNome());
			autoreUpdate.setCognome(autore.getCognome());
			autorepository.save(autoreUpdate);
			return autoreUpdate;

		}else {
			throw new LibroException("Autore non aggiornato");
		}

	}
	public void delete(Long id) {
		autorepository.deleteById(id);
	}


}
