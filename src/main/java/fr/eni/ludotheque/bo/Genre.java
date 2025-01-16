package fr.eni.ludotheque.bo;

public class Genre {
	private Integer noGenre;
	private String libelle;
	
	public Genre() {
		
	}
	
	public Genre(Integer noGenre, String libelle) {
		super();
		this.noGenre = noGenre;
		this.libelle = libelle;
	}

	public Integer getNoGenre() {
		return noGenre;
	}

	public void setNoGenre(Integer noGenre) {
		this.noGenre = noGenre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Genre [noGenre=" + noGenre + ", libelle=" + libelle + "]";
	}
}
