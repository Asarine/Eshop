package fr.adaming.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commandes")
public class Commande {
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_com")
	private Long idCommande;
	
	

	@Temporal(TemporalType.DATE)
	private Date date;
	private double prix;
	
	//Transformation des associations UML en Java
	@ManyToOne
	@JoinColumn(name="cl_id",referencedColumnName="id_cl")
	private Client cl;
	
	@OneToMany(mappedBy="commande")
	private List<LigneCommande> listeLCommande;
	// Constructeur
	public Commande() {
		super();
	}
	
	public Commande(Date date, double prix) {
		super();
		this.date = date;
		this.prix = prix;
	}

	public Commande(Long idCommande, Date date, double prix) {
		super();
		this.idCommande = idCommande;
		this.date = date;
		this.prix = prix;
	}

	// Getters et setters
	public Long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Client getCl() {
		return cl;
	}
	public void setCl(Client cl) {
		this.cl = cl;
	}
	public List<LigneCommande> getListeLCommande() {
		return listeLCommande;
	}
	public void setListeLCommande(List<LigneCommande> listeLCommande) {
		this.listeLCommande = listeLCommande;
	}
	
	
	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	// Génération toString
	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", date=" + date + "]";
	}
	
	

}
