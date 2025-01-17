package fr.eni.ludotheque.services;

import java.util.List;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Jeu;


public interface JeuService extends ICrudService <Jeu>{

	List<Exemplaire> getAllExemplaires(int id);

}
