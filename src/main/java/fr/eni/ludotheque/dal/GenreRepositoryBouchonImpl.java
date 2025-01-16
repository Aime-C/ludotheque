package fr.eni.ludotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Genre;

@Repository
public class GenreRepositoryBouchonImpl implements GenreRepository{


    private List<Genre> genres;
    
    public GenreRepositoryBouchonImpl() {
		genres = new ArrayList<Genre>();
		genres.add(new Genre(1, "Collaboratif"));
		genres.add(new Genre(2, "Cartes"));
		genres.add(new Genre(3, "Plateau"));
		genres.add(new Genre(4, "Stratégie"));
		genres.add(new Genre(5, "Jeu de rôle"));
		genres.add(new Genre(6, "Educatif"));
	}
    
	@Override
	public void add(Genre entity) {
		genres.add(entity);
		
	}

	@Override
	public List<Genre> getAll() {
		return genres.stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Genre> getById(int id) {
		// TODO Auto-generated method stub
		return genres.stream().filter(genre->genre.getNoGenre()==id).findFirst();
	}

	@Override
	public void update(Genre entity) {
		throw new RuntimeException("update non supporté pour les Genres");
		
	}

	@Override
	public void delete(int id) {
		throw new RuntimeException("delete non supporté pour les Genres");
		
	}

}
