package com.spring.boot.data.jpa.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.boot.data.jpa.models.entity.Client;
import com.spring.boot.data.jpa.models.service.IService;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IService<Client> iService;

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("title", "List of Clients");
		Client client = new Client();
		model.addAttribute("client", client);

		return "index";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") int id, Model model) {
		Client client = null;
		if (id > 0) {
			try {
				client = iService.findById("Client", id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			return "redirect:/";
		}
		model.addAttribute("client", client);
		return "index";
	}

	@PostMapping("/save")
	public String save(@Valid Client client, BindingResult result, SessionStatus status) {
		if (result.hasErrors())
			return "index";
		if(client.getId() > 0) iService.update(client);
		else iService.save(client);
		status.setComplete();
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String remove(@PathVariable("id") int id) {
		if(id > 0)
			try {
				iService.delete("Client", id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return "redirect:/";
	}

	@ModelAttribute("clients")
	public List<Client> getClients() {
		return iService.findAll();
	}

}
