package it.prova.branogeneremaven.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	private Date dataPubblicazione; 
	
	public Brano() {
		
	}
	
	public Brano(Long id, String titolo, String autore, Date dataPubblicazione) {
		this.setId(id); 
		this.setTitolo(titolo); 
		this.setAutore(autore); 
		this.setDataPubblicazione(dataPubblicazione); 
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

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	@Override
	public String toString() {
		return "Brano [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", dataPubblicazione="
				+ dataPubblicazione + "]";
	}
	
	
	
}
