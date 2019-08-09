package com.spring.boot.data.jpa.models.service;

import java.util.List;

public interface IService<Entity> {
	public List<Entity> findAll(String entity);

	public void save(Entity entity);

	public void update(Entity entity);

	public Entity findById(String className, int id) throws ClassNotFoundException;

	public void delete(String className, int id) throws ClassNotFoundException;
}
