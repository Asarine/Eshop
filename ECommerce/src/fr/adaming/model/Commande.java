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
	public Commande(Date date) {
		super();
		this.date = date;
	}
	public Commande(Long idCommande, Date date) {
		super();
		this.idCommande = idCommande;
		this.date = date;
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
	
	// Génération toString
	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", date=" + date + "]";
	}
	
	

}
