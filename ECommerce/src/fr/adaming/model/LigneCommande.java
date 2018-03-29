package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lignesCommande")
public class LigneCommande {
	
	//D�claration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lcomm")
	private long idLComm;
	private int quantite;
	private double prix;
	
	//D�claration des constructeurs
	public LigneCommande() {
		super();
	}

	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(long idLComm, int quantite, double prix) {
		super();
		this.idLComm = idLComm;
		this.quantite = quantite;
		this.prix = prix;
	}

	//D�claration des accesseurs
	public long getIdLComm() {
		return idLComm;
	}

	public void setIdLComm(long idLComm) {
		this.idLComm = idLComm;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	//Red�fintion de la m�thode toString
	@Override
	public String toString() {
		return "LigneCommande [idLComm=" + idLComm + ", quantite=" + quantite + ", prix=" + prix + "]";
	}
	
	

}
