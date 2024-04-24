package it.prova.branogeneremaven.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import it.prova.branogeneremaven.dao.BranoDAO;
import it.prova.branogeneremaven.dao.EntityManagerUtil;
import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;

public class BranoServiceImpl implements BranoService {

	private BranoDAO branoDAO; 
	
	@Override
	public void setBranoDAO(BranoDAO branoDAO) {
		this.branoDAO = branoDAO; 
	}
	
	@Override
	public List<Brano> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			branoDAO.setEntityManager(entityManager);
			return branoDAO.list();
		}catch (Exception e) {
			e.printStackTrace();
			throw e; 
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Brano get(Long idBranoInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			branoDAO.setEntityManager(entityManager);
			return branoDAO.get(idBranoInput);
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void update(Brano branoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin(); 
			branoDAO.setEntityManager(entityManager);
			branoDAO.update(branoInstance);
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
	public void insert(Brano branoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			entityManager.getTransaction().begin(); 
			branoDAO.setEntityManager(entityManager);
			branoDAO.insert(branoInstance);
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
	public void delete(Long idBranoInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			entityManager.getTransaction().begin(); 
			branoDAO.setEntityManager(entityManager);
			System.out.println("prima della delete");
			branoDAO.delete(branoDAO.get(idBranoInput));
			System.out.println("dopo la delete ");
			entityManager.getTransaction().commit();
		} catch (NoResultException e) {
			System.out.println("Non esiste alcun brano con id: " + idBranoInput);
			entityManager.getTransaction().rollback();
			throw e; 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Brano caricaBranoPerGenere(Long idBranoInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			branoDAO.setEntityManager(entityManager);
			return branoDAO.caricaBranoPerGenere(idBranoInput);
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuoviBranoMaPrimaScollega(Long idBranoInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			branoDAO.setEntityManager(entityManager);
			branoDAO.rimuoviBranoMaPrimaScollega(idBranoInput);
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
	public List<Genere> descrizioneGeneriAssociatiBrano(Long idBranoInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			branoDAO.setEntityManager(entityManager);
			return branoDAO.descrizioneGeneriAssociatiBrano(idBranoInput); 
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Brano> conDescrizioneDaPiuDieci() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		try {
			branoDAO.setEntityManager(entityManager);
			return branoDAO.conDescrizioneDaPiuDieci();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
