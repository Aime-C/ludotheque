package fr.eni.ludotheque.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class Jeu {
	private Integer noJeu;
	@NotBlank
	private String titre;
	@NotBlank
	private String reference;
	private String description;

	@Min(0)
	private Float tarifJournee;
	@Min(0)
	private int ageMin;
	@Min(0)
	private int duree; //en minutes
	//@NotEmpty => si au moins un genre obligatoire
	private List<Genre> genres;
	
	public Jeu() {
		genres = new ArrayList<>();
	}
	
	public Jeu(Integer noJeu) {
		this();
		this.noJeu = noJeu;
	}
	public Jeu(Integer noJeu, String titre, String reference, String description, float tarifJournee, int ageMin,
			int duree, Genre... genres ) {
		this(noJeu);
		this.titre = titre;
		this.reference = reference;
		this.description = description;
		this.tarifJournee = tarifJournee;
		this.ageMin = ageMin;
		this.duree = duree;
		for(Genre genre :genres) {
			this.genres.add(genre);
		}
	}

	public Integer getNoJeu() {
		return noJeu;
	}

	public void setNoJeu(Integer noJeu) {
		this.noJeu = noJeu;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getTarifJournee() {
		return tarifJournee;
	}

	public void setTarifJournee(Float tarifJournee) {
		this.tarifJournee = tarifJournee;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public List<Genre> getGenres() {
		return genres;
	}
	
	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Jeu [noJeu=" + noJeu + ", titre=" + titre + ", reference=" + reference + ", description=" + description
				+ ", tarifJournee=" + tarifJournee + ", ageMin=" + ageMin + ", duree=" + duree + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(noJeu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jeu other = (Jeu) obj;
		return Objects.equals(noJeu, other.noJeu);
	}

	
}
