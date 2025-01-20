package fr.eni.ludotheque.services;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Jeu;


public interface JeuService extends ICrudService <Jeu>{

	List<Exemplaire> getAllExemplaires(int id);

	void deleteExemplaire(int noExemplaire);

	void updateExemplaire(int noExemplaire);

	Optional<Exemplaire> getExemplaireById(int noExemplaire);

}
