package com.spring.boot.data.jpa.models.dao;

import java.util.List;

public interface IDao<Entity> {

	public List<Entity> findAll();

}
