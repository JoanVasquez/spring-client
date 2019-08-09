package com.spring.boot.data.jpa.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<Entity> {
	
	public List<Entity> findAll();
	
	public Page<Entity> findAll(Pageable pageable);

	public void save(Entity entity);

	public Entity findById(Long id);

	public void delete(Long id);
	
}
