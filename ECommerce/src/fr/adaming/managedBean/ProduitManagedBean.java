package fr.adaming.managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {
	
	// Transformation de l'association UML en java
	@EJB
	private IProduitService produitService;
	
	// Déclaration des attributs envoyés à la page
	private Produit produit;
	
	private boolean indice;

	// Constructeurs
	public ProduitManagedBean() {
		this.produit=new Produit();
		this.indice=false;
	}

	// Getters et setters
	public IProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	
	// Methodes métiers
	
	public String rechercherProduit(){
		
		try{
			
		this.produit=produitService.getProduitById(produit);
		this.indice=true;
		
		}catch (Exception ex){
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit recherché n'existe pas"));
			this.indice=false;
			
		}
		return "rechpr";
	}
	
	public String ajouterProduit(){
		
		Produit prAjout = produitService.addProduit(produit);
		if (prAjout.getIdProduit() != null){
			
		this.produit=prAjout;
		
		return "accueilAdmin";
			
		}else{
			
			return "ajoutpr";
			
		}
		
	}

}
