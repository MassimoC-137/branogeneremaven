package it.prova.branogeneremaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;

public class BranoDAOImpl implements BranoDAO {
	
	private EntityManager entityManager; 

	@Override
	public List<Brano> list() throws Exception {
		return entityManager.createQuery("FROM Brano", Brano.class).getResultList();
	}

	@Override
	public Brano get(Long id) throws Exception {
		return entityManager.find(Brano.class, id);
	}

	@Override
	public void update(Brano o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. "); 
		}
		o = entityManager.merge(o); 
	}

	@Override
	public void insert(Brano o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. "); 
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Brano o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. ");
		}
		entityManager.remove(o);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager; 
	}

	@Override
	public List<Brano> caricaBraniPerGenere(Long idBranoInput) throws Exception {
		TypedQuery<Brano> query = entityManager.createQuery("SELECT b FROM Brano b LEFT JOIN FETCH b.generi g WHERE b.id = :idbrano", Brano.class); 
		query.setParameter("idbrano", idBranoInput); 
		return query.getResultList();
	}

	@Override
	public void rimuoviBranoMaPrimaScollega(Long idBranoInput) throws Exception {
		entityManager.createNativeQuery("DELETE FROM brani_genere m WHERE m.idbrano = ?1").setParameter(1, idBranoInput); 
		entityManager.createNativeQuery("DELETE FROM brano b WHERE b.id = ?1").executeUpdate(); 
	}

	@Override
	public List<Genere> descrizioneGeneriAssociatiBrano(Long idBranoInput) throws Exception {
		TypedQuery<Genere> query = entityManager.createQuery("SELECT g FROM genere g LEFT JOIN FETCH g.brani b WHERE b.id = :idbrano", Genere.class); 
		query.setParameter("idbrano", idBranoInput); 
		return query.getResultList();
	}

	@Override
	public List<Brano> conDescrizioneDaPiuDieci() throws Exception {
		return entityManager.createQuery("SELECT DISTINCT b FROM Brano b JOIN b.generi g WHERE LENGTH (g.descrizione) > 10", Brano.class).getResultList();
	}

}