package fr.eni.ludotheque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;

@Service
public class JeuServiceImpl implements JeuService{

	private JeuRepository jeuRepo;
	private GenreRepository genreRepo;
	
	public JeuServiceImpl(JeuRepository jeuRepo, GenreRepository genreRepo) {
		this.jeuRepo = jeuRepo;
		this.genreRepo = genreRepo;
	}
	
	@Override
	public void add(Jeu entity) {
		
		jeuRepo.add(entity);
	}

	@Override
	public List<Jeu> getAll() {
		
		return jeuRepo.getAll();
	}

	@Override
	public Optional<Jeu> getById(int id) {
		Optional<Jeu> optJeu = jeuRepo.getById(id);
		
		return optJeu;
	}

	@Override
	public void update(Jeu entity) {
		jeuRepo.update(entity);
		
	}

	@Override
	public void save(Jeu jeu) {
		if(jeu.getNoJeu()!=null) {
			jeuRepo.update(jeu);
		}else {
			jeuRepo.add(jeu);
		}
		
	}

	@Override
	public void delete(int id) {
		jeuRepo.delete(id);
		
	}
	
	@Override
	public List<Exemplaire> getAllExemplaires(int id) {
		return jeuRepo.getAllExemplaires(id);
		
	}
	
	@Override
	public void deleteExemplaire(int noExemplaire) {
		jeuRepo.deleteExemplaire(noExemplaire);
	}
	
	@Override
	public void saveExemplaire(Exemplaire exemplaire) {
		if(exemplaire.getNo_exemplaire_jeu()!=null) {
			jeuRepo.updateExemplaire(exemplaire);
		}else {
			jeuRepo.addExemplaire(exemplaire);
		}
		
	}
	
	@Override
	public void addExemplaire(Exemplaire exemplaire) {
		jeuRepo.addExemplaire(exemplaire);
	}
	
	@Override
	public void updateExemplaire(Exemplaire exemplaire) {
		jeuRepo.updateExemplaire(exemplaire);
	}
	
	@Override
	public Optional<Exemplaire> getExemplaireById(int noExemplaire) {
		Optional<Exemplaire> optExemplaire = jeuRepo.getExemplaireById(noExemplaire);
	
		return optExemplaire;
	}

}
