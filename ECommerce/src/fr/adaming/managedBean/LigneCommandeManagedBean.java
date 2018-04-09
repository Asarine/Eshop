package fr.adaming.managedBean;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import com.itextpdf.text.DocumentException;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.EnvoyerMail;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;
import fr.adaming.service.PdfService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	private Panier panier;
	private Produit produit;
	private LigneCommande ligne;
	private double totalPanier = 0;
	HttpSession maSession;

	@EJB
	private ILigneCommandeService ligneService;
	@EJB
	private IProduitService produitService;
	@EJB
	private ICommandeService commandeService;
	
	
	public LigneCommandeManagedBean() {
		super();
		this.produit = new Produit();
		this.ligne = new LigneCommande();
		this.panier = new Panier();
	}

	@PostConstruct
	public void init() {

		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

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
		boolean verif = true;
		Produit pOut = produitService.getProduitById(this.produit);
		pOut.setQuantSouhait(produit.getQuantSouhait());
		this.ligne.setProd(pOut);
		this.panier = (Panier) maSession.getAttribute("monPanier");
		if (panier == null) {
			panier = new Panier();
			panier.setListeLignes(new ArrayList<>());
			this.ligne.setQuantite(this.ligne.getProd().getQuantSouhait());
			this.ligne.setPrix(this.ligne.getProd().getPrix() * this.ligne.getQuantite());
			LigneCommande ligneOut = ligneService.addLigneCommande(ligne);
			panier.getListeLignes().add(ligneOut);
			totalPanier = ligneOut.getPrix();
			maSession.setAttribute("monPanier", panier);
			maSession.setAttribute("total", totalPanier);
		} else {
			for (LigneCommande lg : panier.getListeLignes()) {
				if (lg.getProd().getIdProduit() == this.produit.getIdProduit()) {
					verif = false;
				}
			}
			if (verif) {
				this.ligne.setQuantite(this.ligne.getProd().getQuantSouhait());
				this.ligne.setPrix(this.ligne.getProd().getPrix() * this.ligne.getQuantite());
				LigneCommande ligneOut = ligneService.addLigneCommande(ligne);
				panier.getListeLignes().add(ligneOut);
				totalPanier = (double) maSession.getAttribute("total") + ligneOut.getPrix();
				maSession.setAttribute("total", totalPanier);
				maSession.setAttribute("monPanier", panier);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit déjà ajouté"));
			}
		}
		Client clientOut = (Client) maSession.getAttribute("clientSession");
		if (clientOut != null) {
			return "ajoutPanierClient";
		} else {
			return "ajoutPanier";
		}
	}

	public String supprimerPanier() {

		Client clientOut = (Client) maSession.getAttribute("clientSession");
		if (clientOut == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez vous connecter"));
			return "seconnecterClient";
		} else {
			Calendar cd = Calendar.getInstance();
			Date datecrea = cd.getTime();
			double totalCommande = (double) maSession.getAttribute("total");
			Commande cmdNew = new Commande(datecrea, totalCommande);
			Panier panOut = (Panier) maSession.getAttribute("monPanier");
			cmdNew.setListeLCommande(panOut.getListeLignes());
			cmdNew.setCl(clientOut);
			Commande cOut = commandeService.addCommande(cmdNew);
			for (LigneCommande lComm : panOut.getListeLignes()){
				Produit p=lComm.getProd();
				Produit pRech=produitService.getProduitById(p);
				pRech.setQuantite(pRech.getQuantite()-lComm.getQuantite());
				int verifModif=produitService.updateProduit(pRech);
				if (verifModif!=0){
					lComm.setCommande(cOut);
					int verifModifL=ligneService.updateLigneCommande(lComm);
				}
			}
			if (cOut != null) {
				try {
					PdfService.Facture(cOut);
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				EnvoyerMail.envoyerMessageFacture(clientOut.getEmail());
				maSession.setAttribute("total", 0);
				maSession.setAttribute("monPanier", null);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
			}
			return "commandes";
		}
	}
	
	public void supprimerLComm(){
		this.panier = (Panier) maSession.getAttribute("monPanier");
		for (LigneCommande lg : panier.getListeLignes()) {
			if (lg.getIdLComm() == this.ligne.getIdLComm()) {
				this.totalPanier=(double) maSession.getAttribute("total")-lg.getPrix();
				panier.getListeLignes().remove(lg);
				ligneService.deleteLigneCommande(lg);
				maSession.setAttribute("total", totalPanier);
				maSession.setAttribute("monPanier", panier);
			}
		
			}
		
	}
	
	public void onRowEdit(RowEditEvent event) {
		this.panier = (Panier) maSession.getAttribute("monPanier");
		this.totalPanier=(double) maSession.getAttribute("total");
		LigneCommande ligneModif=(LigneCommande) event.getObject();
		for (LigneCommande lg : panier.getListeLignes()) {
			if (lg.getIdLComm() == ligneModif.getIdLComm()) {
				this.totalPanier=(double) maSession.getAttribute("total")-ligneModif.getPrix();
				ligneModif.setPrix(ligneModif.getQuantite()*ligneModif.getProd().getPrix());
				this.totalPanier=totalPanier+ligneModif.getPrix();
				int ind=panier.getListeLignes().lastIndexOf(lg);
				panier.getListeLignes().set(ind,ligneModif);
				int verif=ligneService.updateLigneCommande2(ligneModif);
		        FacesMessage msg = new FacesMessage("Commande modifiée");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        maSession.setAttribute("total", totalPanier);
				maSession.setAttribute("monPanier", panier);
			}
		
			}
		

}
     
}
