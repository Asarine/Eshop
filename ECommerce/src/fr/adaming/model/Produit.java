package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "produits")
public class Produit implements Serializable{

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pr")
	private Long idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	@Column(columnDefinition="TINYINT(1)")
	private boolean selectionne;
	@Lob
	private byte[] photo;
	
	@Transient
	private int quantSouhait;
	
	@Transient
	private String image;

	

	// Transformation de l'association UML en JAVA
	@ManyToOne
	@JoinColumn(name = "cat_id", referencedColumnName = "id_cat")
	private Categorie cat;

	@OneToMany(mappedBy = "prod")
	private List<LigneCommande> listeLCommande;

	// Constructeurs
	public Produit() {
		super();
	}

	public Produit(String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit(Long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, byte[] photo) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}
	
	

	// Getters et setters
	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public List<LigneCommande> getListeLCommande() {
		return listeLCommande;
	}

	public void setListeLCommande(List<LigneCommande> listeLCommande) {
		this.listeLCommande = listeLCommande;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantSouhait() {
		return quantSouhait;
	}

	public void setQuantSouhait(int quantSouhait) {
		this.quantSouhait = quantSouhait;
	}

	// Rédéfinition toString
	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo=" + photo
				+ "]";
	}

}
