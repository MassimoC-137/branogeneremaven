package main.java.it.prova.branogeneremaven.dao;

import java.util.List;

import jakarta.persistence.EntityManager;

public interface IBaseDAO<T> {

	public void insert(T input) throws Exception;
	
    public List<T> getAll() throws Exception;

    public   T   get(Long id) throws Exception;

    public T update(T input) throws Exception;

    public void delete(T input) throws Exception;

 	public void setEntityManager(EntityManager entityManager);
 	
}