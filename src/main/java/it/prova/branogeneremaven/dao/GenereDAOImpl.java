package it.prova.branogeneremaven.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.branogeneremaven.model.Genere;

public class GenereDAOImpl implements GenereDAO {
	
	private EntityManager entityManager; 

	@Override
	public List<Genere> list() throws Exception {
		return entityManager.createQuery("SELECT DISTINCT g FROM Genere g LEFT JOIN FETCH g.brani", Genere.class).getResultList();
	}

	@Override
	public Genere get(Long id) throws Exception {
		return entityManager.find(Genere.class, id);
	}

	@Override
	public void update(Genere o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. "); 
		}
		o = entityManager.merge(o); 
	}

	@Override
	public void insert(Genere o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. "); 
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Genere o) throws Exception {
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
	public Genere cercaPerDescrizione(String descrizione) throws Exception {
		TypedQuery<Genere> query = entityManager.createQuery("SELECT g FROM Genere g WHERE g.descrizione = ?1", Genere.class).setParameter(1, descrizione);
		return query.getResultStream().findFirst().orElse(null); 
	}

	@Override
	public List<Genere> braniPubblicatiTra(LocalDate dataInizio, LocalDate dataFine) throws Exception {
		if ((dataInizio == null) || (dataFine == null)) {
			throw new Exception("Errore nei valori in input. "); 
		}
		TypedQuery<Genere> query = entityManager.createQuery("SELECT g FROM Genere g JOIN g.brani WHERE data_pubblicazione BETWEEN ?1 and ?2", Genere.class); 
		query.setParameter(1, dataInizio); 
		query.setParameter(2, dataFine);
		return query.getResultList();
	}
	
	@Override
	public void rimuoviGenereSeNonCollegato(Long idGenereInput) throws Exception {
	    Query queryCheck = entityManager.createNativeQuery("SELECT COUNT(*) FROM brani_genere WHERE idgenere = ?1");
	    queryCheck.setParameter(1, idGenereInput);
	    BigInteger countResult = (BigInteger) queryCheck.getSingleResult();
	    long count = countResult.longValue();

	    if (count > 0) {
	        System.out.println("Impossibile eliminare il genere perché ha dei brani collegati.");
	    } else {
	        Query queryDelete = entityManager.createNativeQuery("DELETE FROM genere WHERE id = ?1");
	        queryDelete.setParameter(1, idGenereInput);
	        queryDelete.executeUpdate();
	        System.out.println("Genere eliminato con successo.");
	    }
	}



}
