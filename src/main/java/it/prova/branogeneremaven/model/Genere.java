package it.prova.branogeneremaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="genere")
public class Genere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; 
	@Column(name="descrizione")
	private String descrizione; 
	
	@ManyToMany(mappedBy = "generi", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Brano> brani = new HashSet<Brano>(); 
	
	public Genere() {
		
	}
	
	public Genere(String descrizione, Set<Brano> brani) {
		this.descrizione = descrizione; 
		this.brani = brani; 
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
	
	public Set<Brano> getBrani() {
		return brani; 
	}
	
	public void setBrani(Set<Brano> brani) {
		this.brani = brani; 
	}

	@Override
	public String toString() {
		return "Genere [id=" + id + ", descrizione=" + descrizione + "]";
	}
	
}