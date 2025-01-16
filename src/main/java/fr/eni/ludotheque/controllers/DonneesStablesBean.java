package fr.eni.ludotheque.controllers;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.services.GenreService;

/*
 * Classe utilitaire utilisée depuis les templates Thymeleaf
 */
@Component("ludobean")
public class DonneesStablesBean {

	private List<Genre> genres;
	
	private GenreService genreService;
	
	public DonneesStablesBean(GenreService genreService) {
		this.genreService = genreService;
		initGenres();
	}
	
	public void initGenres() {
		this.genres = genreService.getAll();
	}
	
	public List<Genre> getGenres(){
		return this.genres;
	}
	
	public boolean isGenreSelected(Jeu jeu, int noGenre) {
		boolean res = jeu.getGenres().stream().filter(genre->genre.getNoGenre()==noGenre).findFirst().isPresent();
		return res;
	}
	
}
