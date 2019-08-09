package com.spring.boot.data.jpa.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.data.jpa.models.entity.Client;
import com.spring.boot.data.jpa.models.service.IService;
import com.spring.boot.data.jpa.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IService<Client> iService;

	@GetMapping("/")
	public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		model.addAttribute("title", "List of Clients");
		
		Pageable pageable = PageRequest.of(page, 5);
		Page<Client> clients = iService.findAll(pageable);
		PageRender<Client> pageRender = new PageRender<>("/", clients);
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		
		Client client = new Client();
		model.addAttribute("client", client);

		return "index";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
		Client client = null;
		if (id > 0) {
			client = iService.findById(id);
			if(client == null) {
				flash.addFlashAttribute("error", "Client not found");
				return "redirect:/";
			}
		} else {
			flash.addFlashAttribute("error", "Id cannot be 0!");
			return "redirect:/";
		}
		model.addAttribute("client", client);
		return "index";
	}

	@PostMapping("/save")
	public String save(@Valid Client client, BindingResult result, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) return "index";
		iService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", "Client Created");
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			iService.delete(id);
			flash.addFlashAttribute("success", "Client Deleted");
		}
		return "redirect:/";
	}

	/*@ModelAttribute("clients")
	public Page<Client> getClients(@RequestParam(name = "page", defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Client> clients = iService.findAll(pageable);
		PageRender<Client> pageRender = new PageRender<>("/", clients);
		return pageReq;
	}*/

}
