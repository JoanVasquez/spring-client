package com.spring.boot.data.jpa.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.boot.data.jpa.models.entity.Client;

//import java.util.List;

public interface IDao extends PagingAndSortingRepository<Client, Long>{
/*
	public List<Entity> findAll(String entity);

	public void save(Entity entity);
	
	public void update(Entity entity);

	public Entity findById(String className, int id) throws ClassNotFoundException;
	
	public void delete(String className, int id) throws ClassNotFoundException;*/

}
