package it.be.gestionecatalogo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false)
	private String titolo;
	@Column(nullable = false)
	private Integer annoPubblicazione;
	@Column(nullable = false)
	private double prezzo;
	
	@ManyToMany
	@JoinTable(name = "libri_autore",
	joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	private Set<Autore> autori = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "libri_categorie",
	joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private List<Categoria> categorie = new ArrayList<>();
	
	public void deleteAllFromSet(Autore a) {
        if(this.autori.contains(a)) {
            this.autori.remove(a);
        }
    }
	
	public void deleteAllCategorieFromSet(Categoria c) {
        if(this.categorie.contains(c)) {
            this.categorie.remove(c);
        }
    }

}
