package com.spring.boot.data.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.boot.data.jpa.models.dao.IDao;
import com.spring.boot.data.jpa.models.entity.Client;

@Controller
public class ClientController {

	@Autowired
	@Qualifier("clientDao")
	private IDao<Client> iDao;
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("title", "List of Clients");
		model.addAttribute("clients", iDao.findAll());
		return "index";
	}
}
