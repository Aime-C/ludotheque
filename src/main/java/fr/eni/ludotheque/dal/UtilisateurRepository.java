package fr.eni.ludotheque.dal;



import java.util.Optional;

import fr.eni.ludotheque.bo.Utilisateur;

public interface UtilisateurRepository  {

	public Optional<Utilisateur> findUtilisateurByEmail(String email);
	
}
