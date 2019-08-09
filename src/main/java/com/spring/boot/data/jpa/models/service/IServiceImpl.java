package com.spring.boot.data.jpa.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.data.jpa.models.dao.IDao;


@Service
public class IServiceImpl<T> implements IService<T>{
	
	@Autowired
	private IDao<T> iDao;

	@Override
	public List<T> findAll(String entity) {
		return iDao.findAll(entity);
	}

	@Override
	public void save(T entity) {
		iDao.save(entity);
	}

	@Override
	public void update(T entity) {
		iDao.update(entity);
	}

	@Override
	public T findById(String className, int id) throws ClassNotFoundException {
		return iDao.findById(className, id);
	}

	@Override
	public void delete(String className, int id) throws ClassNotFoundException {
		iDao.delete(className, id);
	}


}
