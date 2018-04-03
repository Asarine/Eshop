package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	private Panier panier;
	private Produit produit;
	private LigneCommande ligne;
	HttpSession maSession;

	@EJB
	private ILigneCommandeService ligneService;
	@EJB
	private IProduitService produitService;

	public LigneCommandeManagedBean() {
		super();
		this.produit = new Produit();
		this.ligne=new LigneCommande();
		this.panier=new Panier();
	}
	
	@PostConstruct
	public void inti(){
		
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	// Méthodes métier
	public void ajouterLigneCommande() {
		boolean verif=true;
		this.panier=(Panier) maSession.getAttribute("monPanier");
		if (panier==null){
			panier=new Panier();
			panier.setListeLignes(new ArrayList<>());
			this.ligne.setQuantite(this.produit.getQuantite());
			this.ligne.setPrix(this.produit.getPrix() * this.ligne.getQuantite());
			this.ligne.setProd(this.produit);
			LigneCommande ligneOut = ligneService.addLigneCommande(ligne);
			panier.getListeLignes().add(ligneOut);
			maSession.setAttribute("monPanier", panier);
			
		}else{
			for (LigneCommande lg:panier.getListeLignes()){
				if (lg.getProd().getIdProduit()==this.produit.getIdProduit()){
					verif=false;
				}
			}
			if (verif){
				this.ligne.setQuantite(this.produit.getQuantite());
				this.ligne.setPrix(this.produit.getPrix() * this.ligne.getQuantite());
				this.ligne.setProd(this.produit);
				LigneCommande ligneOut = ligneService.addLigneCommande(ligne);
				panier.getListeLignes().add(ligneOut);
				maSession.setAttribute("monPanier", panier);
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit déjà ajouté"));
			}
		}
	}
	public void supprimerPanier(){
		maSession.setAttribute("monPanier", null);
	}
	
	public String ajouterPanier(){
		
		System.out.println("llllllllllll-----------------------------------");
		Produit prodOut = produitService.getProduitById(produit);
		this.produit=prodOut;
		return "ajoutPanier";
	}
}
