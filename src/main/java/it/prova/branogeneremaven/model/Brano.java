package it.prova.branogeneremaven.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="brano")

public class Brano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; 
	@Column(name="titolo")
	private String titolo; 
	@Column(name="autore")
	private String autore; 
	@Column(name="data_pubblicazione")
	private LocalDate dataPubblicazione; 
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name="brani_genere", joinColumns = @JoinColumn(name="idbrano", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="idgenere", referencedColumnName = "id"))
	private Set<Genere> generi = new HashSet<Genere>(); 
	
	public Brano() {
		
	}
	
	public Brano (String titolo, String autore, LocalDate dataPubblicazione) {
		this.titolo = titolo; 
		this.autore = autore; 
		this.dataPubblicazione = dataPubblicazione; 
	}
	
	public Long getId() {
		return id; 
	}
	
	public void setId(Long id) {
		this.id = id; 
	}
	
	public String getTitolo() {
		return titolo; 
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo; 
	}
	
	public String getAutore() {
		return autore; 
	}
	
	public void setAutore(String autore) {
		this.autore = autore; 
	}
	
	public LocalDate getDataPubblicazione() {
		return dataPubblicazione; 
	}
	
	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione; 
	}
	
	public Set<Genere> getGeneri() {
		return generi;
	}
	
	public void setGeneri(Set<Genere> generi) {
		this.generi = generi; 
	}

	@Override
	public String toString() {
		return "Brano [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", dataPubblicazione="
				+ dataPubblicazione + ", generi=" + generi + "]";
	}
	
}