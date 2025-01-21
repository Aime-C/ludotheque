package fr.eni.ludotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
	
	private ClientController clientcontroller;
	
	public BaseController(ClientController clientcontroller) {
		this.clientcontroller = clientcontroller;
	}

    @GetMapping("/")
    public String basePageClient(Model model) {
        return clientcontroller.clients(model);
    }
	
}
