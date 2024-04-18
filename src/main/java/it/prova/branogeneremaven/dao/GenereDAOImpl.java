package main.java.it.prova.branogeneremaven.dao;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import main.java.it.prova.branogeneremaven.model.Brano;
import main.java.it.prova.branogeneremaven.model.Genere;

public class GenereDAOImpl implements GenereDAO{

	private EntityManager entityManager;

	@Override
	public void insert(Genere genereInstance) throws Exception {
		if (genereInstance == null){
			throw new Exception("Errore nell'inserimento di un nuovo genere. ");
		}
		entityManager.persist(genereInstance);
	}

	@Override
	public List<Genere> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere update(Genere genereInstance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Genere genereInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Genere> cercaPerDescrizione(String descrizione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brano> braniPubblicatiTra(Date dataInizio, Date dataFine) {
		// TODO Auto-generated method stub
		return null;
	}

}
