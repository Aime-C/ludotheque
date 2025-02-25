package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;

public interface JeuRepository extends ICrudRepository<Jeu>{
	List<Genre> getGenresByNoJeu(Integer noJeu);

	List<Exemplaire> getAllExemplaires(int noJeu);

	void deleteExemplaire(int noExemplaire);

	void updateExemplaire(Exemplaire exemplaire);

	Optional<Exemplaire> getExemplaireById(int id);

	void addExemplaire(Exemplaire exemplaire);
}
