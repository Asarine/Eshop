package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrateurs")
public class Administrateur {
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ad")
	private int id;
	private String mail;
	private String mdp;
	
	// Constructeurs
	public Administrateur() {
		super();
	}
	public Administrateur(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}
	public Administrateur(int id, String mail, String mdp) {
		super();
		this.id = id;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	// Getters et setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	// Redéfinition toString
	@Override
	public String toString() {
		return "Administrateur [id=" + id + ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	

}
