package it.prova.branogeneremaven.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="genere")

public class Genere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; 
	@Column(name="descrizione")
	private String descrizione; 
	
	public Genere() {
		
	}
	
	public Genere(Long id, String descrizione) {
		this.setId(id); 
		this.setDescrizione(descrizione); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Genere [id=" + id + ", descrizione=" + descrizione + "]";
	}
	
	
	
}
