package fr.eni.ludotheque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.services.ClientService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
     * Afficher la liste des clients 
     */
    @GetMapping
    public String clients(Model model) {
        List<Client> clients = clientService.getAll();
        model.addAttribute("clients", clients);
        model.addAttribute("body", "pages/clients/clients");
        return "index";
    }

    @GetMapping("/ajouter")
    public String pageAjouterClient(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("body", "pages/clients/formulaire-client");
        return "index";
    }

    @PostMapping("/enregistrer")
    public String ajouterClient(Model model, @Valid Client client, BindingResult bindingResult) {
    	model.addAttribute("body", "pages/clients/formulaire-client");
    	if(bindingResult.hasErrors()) {
    		return "index";
    	}
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/modifier")
    public String getModifierClient(Model model, @RequestParam("noClient") int noClient) {
        Optional<Client> clientOpt = clientService.getById(noClient);
        if (clientOpt.isPresent()) {
            model.addAttribute("client", clientOpt.get());
            model.addAttribute("body", "pages/clients/formulaire-client");

        } else {
        	//TODO : gestion erreur
            return "redirect:/erreur";
        }
        return "index";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerClient(Model model, @PathVariable("id") int id) {
        clientService.delete(id);
        model.addAttribute("body", "pages/clients/clients");
        return "redirect:/clients";
    }
}
