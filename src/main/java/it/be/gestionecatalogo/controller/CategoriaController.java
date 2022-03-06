package it.be.gestionecatalogo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.CategoriaService;
import it.be.gestionecatalogo.service.LibroService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaservice;
	@Autowired
	LibroService libroservice;

	@GetMapping(path = "/categorie")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "cerca tutte le categorie")
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> findAll = categoriaservice.findAll();

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = "cerca tutte le categorie per id")
	@GetMapping(path = "/categoria/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Categoria> findById(@PathVariable(required = true) Long id) {
		Optional<Categoria> find = categoriaservice.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = "save della categoria")
	@PostMapping(path = "/categoriasalva")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria save = categoriaservice.save(categoria);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@Operation(summary = "aggiornamento della categoria")
	@PutMapping(path = "/categoriaaggiorna/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
		Categoria save = categoriaservice.update(id, categoria);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@Operation(summary = "cancella categoria")
	@DeleteMapping(path = "/categoriacancella/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		Optional<Categoria> find = categoriaservice.findById(id);
        if(!find.isEmpty()) {
            Categoria delete = find.get();
            List<Libro> allLibri = libroservice.findAll();
            for(Libro libro : allLibri) {
                libro.deleteAllCategorieFromSet(delete);
            }
            
            categoriaservice.delete(id);
            return new ResponseEntity<>("categoria cancellati", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("categoria non trovata", HttpStatus.BAD_REQUEST);
        }


	}

}
