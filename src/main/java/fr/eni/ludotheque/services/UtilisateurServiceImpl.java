package fr.eni.ludotheque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Utilisateur;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dal.UtilisateurRepository;
import fr.eni.ludotheque.exceptions.ClientNotFoundException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

	@Override
	public Optional<Utilisateur> findUtilisateurByEmail(String email) {
		
		return utilisateurRepository.findUtilisateurByEmail(email);
	}

    
}
