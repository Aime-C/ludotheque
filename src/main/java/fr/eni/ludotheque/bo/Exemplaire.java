package fr.eni.ludotheque.bo;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.UniqueElements;

public class Exemplaire {

	private Integer no_exemplaire_jeu;
	
	private String codebarre;
	
	private Boolean louable;
	
	public Exemplaire() {
		
	}

	public Exemplaire(Integer no_exemplaire_jeu, String codebarre, Boolean louable) {
		super();
		this.no_exemplaire_jeu = no_exemplaire_jeu;
		this.codebarre = codebarre;
		this.louable = louable;
	}

	public Integer getNo_exemplaire_jeu() {
		return no_exemplaire_jeu;
	}

	public void setNo_exemplaire_jeu(Integer no_exemplaire_jeu) {
		this.no_exemplaire_jeu = no_exemplaire_jeu;
	}

	public String getCodebarre() {
		return codebarre;
	}

	public void setCodebarre(String codebarre) {
		this.codebarre = codebarre;
	}

	public Boolean getLouable() {
		return louable;
	}

	public void setLouable(Boolean louable) {
		this.louable = louable;
	}
}
