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
import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.AutoriService;
import it.be.gestionecatalogo.service.LibroService;


@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class AutoreController {
	@Autowired
	private AutoriService autoreservice;
	@Autowired
	private LibroService libroservice;
	
	@GetMapping(path = "/findall")
	@Operation(summary="cerca tutti gli autori")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')") // Se un SOLO. ruolo : @PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Autore>> findAll() {
		List<Autore> findAll = autoreservice.findALL();

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping(path = "/autoreId/{id}")
	@Operation(summary="cerca tutti gli autori per id")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Autore> findById(@PathVariable(required = true) Long id) {
		Optional<Autore> find = autoreservice.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping(path = "/autoresave")
	@Operation(summary=" SALVA")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Autore> save(@RequestBody Autore autore) {
		Autore save = autoreservice.save(autore);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PutMapping(path = "/autoreaggiorna/{id}")
	@Operation(summary="Aggiorna")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Autore> update(@PathVariable Long id, @RequestBody Autore autore) {
		Autore ceck = autoreservice.update(id, autore);
		return new ResponseEntity<>(ceck, HttpStatus.OK);

	}
	@DeleteMapping(path = "/autoreelimina/{id}")
	@Operation(summary=" metodo per eliminare")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		Optional<Autore> find = autoreservice.findById(id);
        if(!find.isEmpty()) {
            Autore delete = find.get();
            List<Libro> allLibri = libroservice.findAll();
            for(Libro libro : allLibri) {
                libro.deleteAllFromSet(delete);
            }
            libroservice.deleteLibriNoAutori();
            autoreservice.delete(id);
            return new ResponseEntity<>("Autore e libri corrispondenti cancellati", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Autore non trovato", HttpStatus.BAD_REQUEST);
        }


	}
	

}
