package fr.eni.ludotheque.controllers;



import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.dal.GenreRepository;

@Component
public class StringToGenreConverter implements Converter< String, Genre>{

	private GenreRepository genreRepository;
	
	public StringToGenreConverter(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public Genre convert(String strGenreId) {
		int genreId ;
		try {
			genreId = Integer.parseInt(strGenreId);
		}catch(NumberFormatException exc ) {
			throw exc;
		}
		Optional<Genre> optGenre = genreRepository.getById(genreId);
		if(optGenre.isPresent()) {
			return optGenre.get();
		}
		
		throw new RuntimeException("Genre " + strGenreId + " non trouvé");
	}
	
	

}
