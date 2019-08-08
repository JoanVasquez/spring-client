package com.spring.boot.data.jpa.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DaoImpl<T> implements IDao<T> {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		return em.createQuery("from Client").getResultList();
	}

	@Transactional
	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Transactional
	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public T findById(String className, int id) throws ClassNotFoundException {
		Class c = Class.forName("com.spring.boot.data.jpa.models.entity" + className);
		return (T) em.find(c, id);
	}

	@Transactional
	@Override
	public void delete(String className, int id) throws ClassNotFoundException {
		em.remove(findById(className, id));
	}

}
