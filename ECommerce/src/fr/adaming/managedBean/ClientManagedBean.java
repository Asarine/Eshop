package fr.adaming.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.service.EnvoyerMail;
import fr.adaming.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {
	
	//Attributs
	private Client client;
	private String mdp;
	HttpSession maSession;
	
	@EJB
	private IClientService clientService;

	//Constructeur vide
	public ClientManagedBean() {
		super();
		this.client = new Client();
		this.mdp=new String();
	}
	@PostConstruct
	public void inti(){
		
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	//Getter & Setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	//Méthodes métiers
	public String seConnecter(){
		try{
			this.client=clientService.isExist(client);
			//Ajouter le client à la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession", client);
			return "accueilClient";
		}catch (Exception ex){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiant ou mot de passe incorrect"));
		}
		return "seconnecterClient";
	}

	public String ajoutClient(){
		
		Client clAjout=clientService.addClient(this.client);
		if (clAjout.getIdClient()!=0){
			this.client=clAjout;
			EnvoyerMail.envoyerMessageAjout(client);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre mot de passe vous a été envoyé par mail"));
			return "seconnecterClient";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué."));
			return "ajoutClient";
		}
	}
	
	public String modifierClient(){
		HttpSession maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Client clIn=(Client) maSession.getAttribute("clientSession");
		this.client.setIdClient(clIn.getIdClient());
		int verif=clientService.modifClient(this.client);
		if (verif!=0){
			maSession.setAttribute("clientSession", this.client);
			return "accueilClient";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Les modifications n'ont pas été effectuées."));
			return "modifClient";
		}
	}

	public String suppClient(){
		HttpSession maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Client clIn=(Client) maSession.getAttribute("clientSession");
		int verif=clientService.deleteClient(clIn);
		if (verif!=0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a bien été supprimé."));
			return "accueil";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client n'a pas été supprimé."));
			return "accueilClient";
		}
	}
	
	public String modifClientavecLien(){
		HttpSession maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Client clSess=(Client) maSession.getAttribute("clientSession");
		Client clOut=clientService.getClientbyId(clSess);
		if (clOut!=null){
			this.client=clOut;
			return "modifClient";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur"));
			return "accueilClient";
		}
	}
	
	public String decoClient(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "accueil";
	}
	
public void validerMdp(FacesContext context, UIComponent composant, Object valeur) throws ValidatorException {
		
		String saisie=(String) valeur;
		
		if (saisie!=mdp) {
			throw new ValidatorException(new FacesMessage("Le mot de passe répété est différent du premier."));
		}
	}
}
