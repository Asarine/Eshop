package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	private double totalPanier=0;
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
	
	

	public LigneCommande getLigne() {
		return ligne;
	}

	public void setLigne(LigneCommande ligne) {
		this.ligne = ligne;
	}

	public double getTotalPanier() {
		return totalPanier;
	}

	public void setTotalPanier(double totalPanier) {
		this.totalPanier = totalPanier;
	}

	// Méthodes métier
	public String ajouterLigneCommande() {
		boolean verif=true;
		Produit pOut=produitService.getProduitById(this.produit);
		pOut.setQuantSouhait(produit.getQuantSouhait());
		this.ligne.setProd(pOut);
		this.panier=(Panier) maSession.getAttribute("monPanier");
		if (panier==null){
			panier=new Panier();
			panier.setListeLignes(new ArrayList<>());
			this.ligne.setQuantite(this.ligne.getProd().getQuantSouhait());
			this.ligne.setPrix(this.ligne.getProd().getPrix()* this.ligne.getQuantite());
			LigneCommande ligneOut = ligneService.addLigneCommande(ligne);
			panier.getListeLignes().add(ligneOut);
			totalPanier=ligneOut.getPrix();
			maSession.setAttribute("monPanier", panier);
			maSession.setAttribute("total", totalPanier);
		}else{
			for (LigneCommande lg:panier.getListeLignes()){
				if (lg.getProd().getIdProduit()==this.produit.getIdProduit()){
					verif=false;
				}
			}
			if (verif){
				this.ligne.setQuantite(this.ligne.getProd().getQuantSouhait());
				this.ligne.setPrix(this.produit.getPrix() * this.ligne.getQuantite());
				this.ligne.setProd(this.produit);
				LigneCommande ligneOut = ligneService.addLigneCommande(ligne);
				panier.getListeLignes().add(ligneOut);
				totalPanier=(double) maSession.getAttribute("total")+ligneOut.getPrix();
				maSession.setAttribute("total", totalPanier);
				maSession.setAttribute("monPanier", panier);
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit déjà ajouté"));
			}
		}
		
		return "ajoutPanier";
	}

	public String supprimerPanier(){
		
		Calendar cd=Calendar.getInstance();
		Date datecrea=cd.getTime();
		maSession.setAttribute("monPanier", null);
		return "choup";
	}
	
}
