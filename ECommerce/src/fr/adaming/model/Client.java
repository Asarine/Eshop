package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client implements Serializable {
	
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_Cl")
	private long idClient;
	private String nomClient;
	private String adresse;
	private String codePostal;
	private String ville;
	private String email;
	private String tel;
	private String mdp;
	
	//Transformation de l'association UML en Java
	@OneToMany(mappedBy="cl")
	private List<Commande> listeCommandes;
	
	//Déclaration des constructeurs
	public Client() {
		super();
	}

	public Client(String nomClient, String adresse, String codePostal, String ville, String email, String tel,
			String mdp) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.email = email;
		this.tel = tel;
		this.mdp = mdp;
	}

	public Client(long idClient, String nomClient, String adresse, String codePostal, String ville, String email,
			String tel, String mdp) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.email = email;
		this.tel = tel;
		this.mdp = mdp;
	}

	//Déclaration des accesseurs
	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	//Redéfinition de la méthode toString
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomClient=" + nomClient + ", adresse=" + adresse + ", codePostal="
				+ codePostal + ", ville=" + ville + ", email=" + email + ", tel=" + tel + ", mdp=" + mdp + "]";
	}
	

}
