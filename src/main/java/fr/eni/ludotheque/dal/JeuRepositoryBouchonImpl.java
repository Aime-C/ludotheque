package fr.eni.ludotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;

@Repository
public class JeuRepositoryBouchonImpl implements JeuRepository{
	private static int idxJeu = 1;
    private List<Jeu> jeux;

    
    private GenreRepository genreRepo;
    
    public JeuRepositoryBouchonImpl(GenreRepository genreRepo) {
    	this.genreRepo = genreRepo;
    	
		jeux = new ArrayList<Jeu>();
			
		jeux.add(new Jeu(1, "Monopoly", "MON001", "Jeu de société classique d'achat et de vente de propriétés", 5.99f, 8, 120, genreRepo.getById(3).get()));
        jeux.add(new Jeu(2, "Catan", "CAT001", "Jeu de stratégie et de développement de colonies", 6.99f, 10, 90, genreRepo.getById(3).get(), genreRepo.getById(4).get()));
        jeux.add(new Jeu(3, "Uno", "UNO001", "Jeu de cartes familial", 3.99f, 7, 30, genreRepo.getById(2).get()));
        jeux.add(new Jeu(4, "Risk", "RIS001", "Jeu de stratégie mondiale", 7.99f, 10, 180, genreRepo.getById(3).get()));
        jeux.add(new Jeu(5, "Cluedo", "CLU001", "Jeu d'enquête et de déduction", 5.99f, 8, 60, genreRepo.getById(3).get()));
        jeux.add(new Jeu(6, "Skyjo", "SKY001", "Jeu de cartes stratégique avec des chiffres de -2 à 12", 4.99f, 8, 30, genreRepo.getById(2).get()));
        jeux.add(new Jeu(7, "Welcome", "WEL001", "Jeu de création de ville avec remplissage de tableau", 5.99f, 10, 25, genreRepo.getById(3).get()));
	}
    
	@Override
	public void add(Jeu entity) {
		jeux.add(entity);		
	}

	@Override
	public List getAll() {
		return jeux.stream().toList();
	}

	@Override
	public Optional<Jeu> getById(int noJeu) {
		
		return jeux.stream().filter(jeu->jeu.getNoJeu()==noJeu).findFirst();
	}

	@Override
	public void update(Jeu entity) {
		Jeu jeu = getById(entity.getNoJeu()).orElseThrow();
		BeanUtils.copyProperties(entity, jeu); 
	}

	@Override
	public void delete(int id) {
		jeux.remove(new Jeu(id));		
	}

	@Override
	public List<Genre> getGenresByNoJeu(Integer noJeu) {
		// TODO Auto-generated method stub
		return null;
	}

}
