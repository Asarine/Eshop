package fr.adaming.managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Administrateur;
import fr.adaming.service.IAdministrateurService;

@ManagedBean(name="adMB")
@RequestScoped
public class AdministrateurManagedBean implements Serializable {
	
	// Transformation de l'association UML en JAVA
	@EJB
	private IAdministrateurService adminService;
	
	private Administrateur administrateur;
	
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

	public String seConnecter(){
		
		try{
			
			Administrateur adOut = adminService.isExist(this.administrateur);
			
			// Ajouter l'administrateur comme attribut de la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adOut);
			
			return "accueilAdmin";
			
		}catch (Exception ex){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'identifiant ou le mot de passe est incorrecte"));
			
		}
		
		return "loginAdmin";
		
	}
	

}
