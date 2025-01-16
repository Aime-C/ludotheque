package fr.eni.ludotheque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.dal.GenreRepository;

@Service("genreService")
public class GenreServiceImpl implements GenreService{

	private GenreRepository genreRepo;
	
	public GenreServiceImpl( GenreRepository genreRepo) {
		this.genreRepo = genreRepo;
	}
	
	@Override
	public void add(Genre entity) {
		genreRepo.add(entity);
		
	}

	@Override
	public List<Genre> getAll() {
		return genreRepo.getAll();
	}
	

	@Override
	public Optional<Genre> getById(int id) {
		return genreRepo.getById(id);
	}

	@Override
	public void update(Genre entity) {
		genreRepo.update(entity);
		
	}

	@Override
	public void save(Genre entity) {
		//TODO : A faire...
		throw new RuntimeException("save non supporté pour les Genres");
		
	}

	@Override
	public void delete(int id) {
		//TODO : A faire...
		throw new RuntimeException("delete non supporté pour les Genres");
		
	}

}
