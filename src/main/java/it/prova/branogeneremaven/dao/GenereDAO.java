package it.prova.branogeneremaven.dao;

import java.time.LocalDate;
import java.util.List;

import it.prova.branogeneremaven.model.Genere;

public interface GenereDAO extends IBaseDAO<Genere>{

	public Genere cercaPerDescrizione(String descrizione)throws Exception; 
	
	public List<Genere> braniPubblicatiTra(LocalDate dataInizio, LocalDate dataFine) throws Exception;

	void rimuoviGenereSeNonCollegato(Long idGenereInput) throws Exception; 
}
