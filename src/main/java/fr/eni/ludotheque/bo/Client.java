package fr.eni.ludotheque.bo;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class Client {
	private Integer noClient;
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	
	@NotBlank
	private String email;
	private String noTelephone;
	@NotBlank
	private String rue;
	@NotBlank
	private String codePostal;
	@NotBlank
	private String ville;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client( String nom, String prenom, String email, String rue,
			String codePostal, String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;		
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	
	public Client(Integer noClient, String nom, String prenom, String email, String noTelephone, String rue,
			String codePostal, String ville) {
		super();
		this.noClient = noClient;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.noTelephone = noTelephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Integer getNoClient() {
		return noClient;
	}

	public void setNoClient(Integer noClient) {
		this.noClient = noClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNoTelephone() {
		return noTelephone;
	}

	public void setNoTelephone(String noTelephone) {
		this.noTelephone = noTelephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Client [noClient=" + noClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", noTelephone=" + noTelephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(noClient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(noClient, other.noClient);
	}
	
	
}
