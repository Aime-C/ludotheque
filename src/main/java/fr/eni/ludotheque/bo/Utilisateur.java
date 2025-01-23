package fr.eni.ludotheque.bo;

import java.util.Objects;

public class Utilisateur {
	private int noUtilisateur;
	private String email;
	private String motDePasse;
	private String role;
	
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}
	
	public Utilisateur(int noUtilisateur, String email, String motDePasse, String role) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
	}

	public Utilisateur(String email, String motDePasse, String role) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(email, other.email);
	}

	
	
	
}
