package fr.eni.ludotheque.services;



import java.util.Optional;

import fr.eni.ludotheque.bo.Utilisateur;

public interface UtilisateurService  {

	public Optional<Utilisateur> findUtilisateurByEmail(String email);
	
}
