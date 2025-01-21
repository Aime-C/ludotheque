package fr.eni.ludotheque.bo;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;

public class Exemplaire {

	private Integer no_exemplaire_jeu;
	
	private Integer no_jeu;

	@NotBlank
	private String codebarre;
	
	private Boolean louable;
	
	public Exemplaire() {
		
	}

	public Exemplaire(Integer no_exemplaire_jeu, Integer no_jeu,String codebarre, Boolean louable) {
		super();
		this.no_exemplaire_jeu = no_exemplaire_jeu;
		this.no_jeu = no_jeu;
		this.codebarre = codebarre;
		this.louable = louable;
	}

	public Integer getNo_exemplaire_jeu() {
		return no_exemplaire_jeu;
	}

	public void setNo_exemplaire_jeu(Integer no_exemplaire_jeu) {
		this.no_exemplaire_jeu = no_exemplaire_jeu;
	}

	public Integer getNo_jeu() {
		return no_jeu;
	}

	public void setNo_jeu(Integer no_jeu) {
		this.no_jeu = no_jeu;
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
