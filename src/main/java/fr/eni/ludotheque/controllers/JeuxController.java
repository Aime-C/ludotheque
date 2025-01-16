package fr.eni.ludotheque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.services.JeuService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/jeux")
public class JeuxController {

    private JeuService jeuService;

    public JeuxController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    /*
     * Afficher la liste des jeux 
     */
    @GetMapping
    public String afficherJeux(Model model) {
        List<Jeu> jeux = jeuService.getAll();
        model.addAttribute("jeux", jeux);
        model.addAttribute("body", "pages/jeux/jeux");
        return "index";
    }

    @GetMapping("/{noJeu}/afficher")
    public String pageAjouterJeu(@PathVariable("noJeu") Integer noJeu, Model model) {
    	
    	Optional<Jeu> optJeu = jeuService.getById(noJeu);
    	
    	if(optJeu.isEmpty()) {
    		//TODO : Erreur
    		throw new RuntimeException("Le jeu " + noJeu +" n'a pas été trouvé. ");
    	}
        model.addAttribute("jeu", optJeu.get());
        model.addAttribute("body", "pages/jeux/jeu");
        return "index";
    }

    @GetMapping("/ajouter")
    public String pageAjouterJeu(Model model) {
        model.addAttribute("jeu", new Jeu());
        model.addAttribute("body", "pages/jeux/formulaire-jeu");
        return "index";
    }

    @PostMapping("/enregistrer")
    public String ajouterJeu(Model model, @Valid Jeu jeu,  BindingResult bindingResult ) {
    	model.addAttribute("body", "pages/jeux/formulaire-jeu");
    	if(bindingResult.hasErrors()) {
    		return "index";
    	}
        jeuService.save(jeu);
        return "redirect:/jeux";
    }

    @GetMapping("/modifier")
    public String getModifierJeu(Model model, @RequestParam("noJeu") int noJeu) {
        Optional<Jeu> jeuOpt = jeuService.getById(noJeu);
        if (jeuOpt.isPresent()) {
            model.addAttribute("jeu", jeuOpt.get());
            model.addAttribute("body", "pages/jeux/formulaire-jeu");

        } else {
        	//TODO : gestion erreur
            model.addAttribute("body", "pages/jeux/jeux");
        }
        return "index";
    }

    @GetMapping("/supprimer/{noJeu}")
    public String supprimerJeu(Model model, @PathVariable("noJeu") int noJeu) {
        jeuService.delete(noJeu);
        model.addAttribute("body", "pages/jeux/formulaire-jeu");
        return "redirect:/jeux";
    }
}
