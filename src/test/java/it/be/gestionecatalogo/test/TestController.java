package it.be.gestionecatalogo.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import it.be.gestionecatalogo.controller.LibroController;

@SpringBootTest

@AutoConfigureMockMvc
public class TestController {
	@Autowired
	LibroController librocontroller;
	

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void listaCategorieWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/categorie")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void listaLibriWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/findall")).andExpect(status().isUnauthorized());
	}
}
	


