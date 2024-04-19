package it.prova.branogeneremaven.service;

import java.time.LocalDate;
import java.util.List;

import it.prova.branogeneremaven.dao.GenereDAO;
import it.prova.branogeneremaven.model.Genere;

public class GenereServiceImpl implements GenereService {

	@Override
	public void setGenereDAO(GenereDAO genereDAOInstance) {
		
	}

	@Override
	public List<Genere> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Genere genereInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Genere genereInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Genere cercaPerDescrizione(String descrizione) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genere> braniPubblicatiTra(LocalDate dataInizio, LocalDate dataFine) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
