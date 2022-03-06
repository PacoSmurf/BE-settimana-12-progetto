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
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.LibroService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class LibroController {

	@Autowired
	private LibroService libroservice;

	@Operation(summary = "cerca tutti i libri")
	@GetMapping("/findall/")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findAll() {
		List<Libro> findAll = libroservice.findAll();

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = " cerca i libri per id")
	@GetMapping("/getbyid/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Libro> getById(@PathVariable(required = true) Long id) {
		Optional<Libro> find = libroservice.findByLibroId(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = "salva un libro nel db")
	@PostMapping(path = "/librosave")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> save(@RequestBody Libro libro) {
		Libro save = libroservice.save(libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@Operation(summary = "aggiorna un libro")
	@PutMapping(path = "/libroaggiornato/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro) {
		Libro save = libroservice.updateLibro(libro, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@Operation(summary = "cancella un libro")
	@DeleteMapping(path = "/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		libroservice.delete(id);
		return new ResponseEntity<>("Libro deleted", HttpStatus.OK);

	}

}
