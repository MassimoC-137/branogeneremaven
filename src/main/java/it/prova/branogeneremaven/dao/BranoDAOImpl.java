package it.prova.branogeneremaven.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;

public class BranoDAOImpl implements BranoDAO {
	
	private EntityManager entityManager; 

	@Override
	public List<Brano> list() throws Exception {
		return entityManager.createQuery("SELECT DISTINCT b FROM Brano b JOIN FETCH b.generi", Brano.class).getResultList();
		}

	@Override
	public Brano get(Long id) throws Exception {
	    if (id == null) {
	        throw new IllegalArgumentException("L'id non può essere nullo");
	    }

	    TypedQuery<Brano> query = entityManager.createQuery("SELECT b FROM Brano b JOIN FETCH b.generi WHERE b.id = :id", Brano.class);
	    query.setParameter("id", id);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    } catch (Exception e) {
	        throw new Exception("Errore nell'id brano: " + id, e);
	    }
	}

	@Override
	public void update(Brano o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. "); 
		}
		o = entityManager.merge(o); 
		entityManager.flush();
	}

	@Override
	public void insert(Brano o) throws Exception {
		if (o == null) {
			throw new Exception("Errore nei valori in input. "); 
		}
		Set<Genere> managedGeneri = new HashSet<>();
	    for (Genere genere : o.getGeneri()) {
	        if (entityManager.contains(genere)) {
	            managedGeneri.add(genere);
	        } else {
	            managedGeneri.add(entityManager.merge(genere));
	        }
	    }
	    
	    o.setGeneri(managedGeneri);
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
	public Brano caricaBranoPerGenere(Long idBranoInput) throws Exception {
		TypedQuery<Brano> query = entityManager.createQuery("SELECT b FROM Brano b LEFT JOIN FETCH b.generi g WHERE b.id = :idbrano", Brano.class); 
		query.setParameter("idbrano", idBranoInput); 
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	@Override
	public void rimuoviBranoMaPrimaScollega(Long idBranoInput) throws Exception {
	    Query queryRelazione = entityManager.createNativeQuery("DELETE FROM brani_genere WHERE idbrano = ?1");
	    queryRelazione.setParameter(1, idBranoInput);
	    queryRelazione.executeUpdate();

	    Query queryBrano = entityManager.createNativeQuery("DELETE FROM brano WHERE id = ?1");
	    queryBrano.setParameter(1, idBranoInput);
	    queryBrano.executeUpdate();
	}


	@Override
	public List<Genere> descrizioneGeneriAssociatiBrano(Long idBrano) throws Exception {
	    TypedQuery<Genere> query = entityManager.createQuery("SELECT g FROM Genere g JOIN FETCH g.brani b WHERE b.id = :idbrano", Genere.class);
	    query.setParameter("idbrano", idBrano);

	    return query.getResultList();
	}


	@Override
	public List<Brano> conDescrizioneDaPiuDieci() throws Exception {
		return entityManager.createQuery("SELECT DISTINCT b FROM Brano b JOIN FETCH b.generi g WHERE LENGTH (g.descrizione) > 10", Brano.class).getResultList();
	}

}
