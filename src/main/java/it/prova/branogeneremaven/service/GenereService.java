package it.prova.branogeneremaven.service;

import java.time.LocalDate;
import java.util.List;

import it.prova.branogeneremaven.dao.GenereDAO;
import it.prova.branogeneremaven.model.Genere;

public interface GenereService {

	public void setGenereDAO(GenereDAO genereDAO);
	

	public List<Genere> list() throws Exception;

	public Genere get(Long id) throws Exception;

	public void update(Genere genereInstance) throws Exception;

	public void insert(Genere genereInstance) throws Exception;

	public void delete (Long id) throws Exception;
	

	public Genere cercaPerDescrizione(String descrizione) throws Exception;

	public List<Genere> braniPubblicatiTra(LocalDate dataInizio, LocalDate dataFine) throws Exception;
}
