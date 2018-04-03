package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="lignesCommande")
public class LigneCommande {
	
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lcomm")
	private long idLComm;
	private int quantite;
	private double prix;
	
	//Transformation de l'association UML en Java
	
	@ManyToOne
	@JoinColumn(name="com_id",referencedColumnName="id_com")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="pr_id", referencedColumnName="id_pr")
	private Produit prod;

	@Transient
	private Panier panier;
	
	//Déclaration des constructeurs
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

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	//Déclaration des accesseurs
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

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	//Redéfintion de la méthode toString
	@Override
	public String toString() {
		return "LigneCommande [idLComm=" + idLComm + ", quantite=" + quantite + ", prix=" + prix + "]";
	}
	
	

}
