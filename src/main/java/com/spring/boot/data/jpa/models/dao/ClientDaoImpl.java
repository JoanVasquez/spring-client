package com.spring.boot.data.jpa.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.data.jpa.models.entity.Client;

@Repository("clientDao")
public class ClientDaoImpl implements IDao<Client> {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Client").getResultList();
	}

	@Override
	@Transactional
	public void save(Client client) {
		em.persist(client);
	}

}
