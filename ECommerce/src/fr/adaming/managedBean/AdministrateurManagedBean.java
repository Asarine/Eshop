package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="adMB")
@RequestScoped
public class AdministrateurManagedBean implements Serializable {
	
	// Transformation de l'association UML en JAVA
	@EJB
	private IAdministrateurService adminService;
	
	// Déclaration des attributs envoyés à la page
	private Administrateur administrateur;
	
	private List<Produit> listeProduits;
	
	private IProduitService prService;
	
	private Produit pr;
	
	// Constructeurs
	public AdministrateurManagedBean() {
		this.administrateur=new Administrateur();
	}

	// Getters et setters
	public IAdministrateurService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdministrateurService adminService) {
		this.adminService = adminService;
	}
	
	
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
	

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	

	public IProduitService getPrService() {
		return prService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}
	

	public Produit getPr() {
		return pr;
	}

	public void setPr(Produit pr) {
		this.pr = pr;
	}

	public String seConnecter(){
		
		try{
			
			Administrateur adOut = adminService.isExist(this.administrateur);
			this.listeProduits = prService.getAllProduits(pr);
	
			
			// Ajouter l'administrateur comme attribut de la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adOut);
			
			return "accueilAdmin";
			
		}catch (Exception ex){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'identifiant ou le mot de passe est incorrecte"));
			
		}
		
		return "loginAdmin";
		
	}
	

}
