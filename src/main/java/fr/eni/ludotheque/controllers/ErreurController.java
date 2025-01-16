package fr.eni.ludotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErreurController {

	@GetMapping("/erreur")
	public String pageErreur() {
		return "erreur";
	}
	
}
