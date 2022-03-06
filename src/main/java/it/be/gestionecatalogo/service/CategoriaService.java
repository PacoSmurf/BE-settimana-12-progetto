package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exception.LibroException;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.repository.CategoriaRepository;
@Service
public class CategoriaService  {
	@Autowired
	CategoriaRepository categoriarepository;


	public Optional<Categoria> findById(Long id) {
		return categoriarepository.findById(id);
	}

	public List<Categoria> findAll() {
		return categoriarepository.findAll();
	}

	public Categoria save(Categoria categoria) {
		return categoriarepository.save(categoria);
	}

	public Categoria update(Long id, Categoria categoria) {
		Optional<Categoria> categoriaResult = categoriarepository.findById(id);

		if (categoriaResult.isPresent()) {
			Categoria categoriaUpdate = categoriaResult.get();
			categoriaUpdate.setNome(categoria.getNome());
			categoriarepository.save(categoriaUpdate);
			return categoriaUpdate;
		} else {
			throw new LibroException("Categoria non aggiornata");
		}

	}
	public void delete(Long id) {
		categoriarepository.deleteById(id);
	}

}



