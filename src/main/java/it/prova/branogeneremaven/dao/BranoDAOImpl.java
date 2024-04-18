package main.java.it.prova.branogeneremaven.dao;

import java.util.List;

import it.prova.menupizzeria.model.Ingrediente;
import jakarta.persistence.EntityManager;
import main.java.it.prova.branogeneremaven.model.Brano;
import main.java.it.prova.branogeneremaven.model.Genere;

public class BranoDAOImpl implements BranoDAO{

	private EntityManager entityManager;

	@Override
	public void insert(Brano branoInstance) throws Exception {
		if (branoInstance == null){
			throw new Exception("Errore nell'inserimento di un nuovo brano.");
		}
		entityManager.persist(branoInstance);
	}

	@Override
	public List<Brano> getAll() throws Exception {
		return entityManager.createQuery("SELECT b FROM Brano b",Brano.class).getResultList();
	}

	@Override
	public Brano get(Long id) throws Exception {
		return entityManager.find(Ingrediente.class, id);
	}

	@Override
	public Brano update(Brano branoInstance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Brano branoInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Brano> caricaBraniPerGenere(Genere genereInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genere> estraiListaDescrizioneGeneriAssociateAdUnBrano(Brano branoInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brano> listaBraniConGeneriConPiuDiDieciCaratteri(Genere genereInstance) {
		// TODO Auto-generated method stub
		return null;
	}

}
