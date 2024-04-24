package it.prova.branogeneremaven.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import it.prova.branogeneremaven.dao.EntityManagerUtil;
import it.prova.branogeneremaven.dao.GenereDAO;
import it.prova.branogeneremaven.model.Genere;

public class GenereServiceImpl implements GenereService {

	private GenereDAO genereDAO; 
	
	@Override
	public void setGenereDAO(GenereDAO genereDAO) {
		this.genereDAO = genereDAO; 
	}

	@Override
	public List<Genere> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try { 
			genereDAO.setEntityManager(entityManager);
			return genereDAO.list(); 
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Genere get(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			genereDAO.setEntityManager(entityManager);
			return genereDAO.get(id); 
		} catch (Exception e){
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void update(Genere genereInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			entityManager.getTransaction().begin();
			genereDAO.setEntityManager(entityManager);
			genereDAO.update(genereInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void insert(Genere genereInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			entityManager.getTransaction().begin();
			genereDAO.setEntityManager(entityManager);
			genereDAO.insert(genereInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void delete(Long id) throws Exception {
	    EntityManager entityManager = EntityManagerUtil.getEntityManager();
	    try {
	        entityManager.getTransaction().begin();
	        genereDAO.setEntityManager(entityManager);
	        genereDAO.rimuoviGenereSeNonCollegato(id);
	        entityManager.getTransaction().commit();
	    } catch (Exception e) {
	        entityManager.getTransaction().rollback();
	        e.printStackTrace();
	        throw e; 
	    } finally {
	        EntityManagerUtil.closeEntityManager(entityManager);
	    }
	}


	@Override
	public Genere cercaPerDescrizione(String descrizione) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			genereDAO.setEntityManager(entityManager);
			return genereDAO.cercaPerDescrizione(descrizione); 
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Genere> braniPubblicatiTra(LocalDate dataInizio, LocalDate dataFine) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			genereDAO.setEntityManager(entityManager);
			return genereDAO.braniPubblicatiTra(dataInizio, dataFine); 
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
}
