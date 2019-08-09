package com.spring.boot.data.jpa.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.data.jpa.models.dao.IDao;
import com.spring.boot.data.jpa.models.entity.Client;

@Service
public class IServiceImpl implements IService<Client> {

	@Autowired
	private IDao iDao;

	@Transactional(readOnly = true)
	@Override
	public List<Client> findAll() {
		return (List<Client>) iDao.findAll();
	}

	@Transactional
	@Override
	public void save(Client entity) {
		iDao.save(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public Client findById(Long id) {
		return iDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		iDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Client> findAll(Pageable pageable) {
		return iDao.findAll(pageable);
	}

}
