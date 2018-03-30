package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {
	
	//Attributs
	private Client client;
	private List<Client> listeCl;
	
	@EJB
	private IClientService clientService;

	//Constructeur vide
	public ClientManagedBean() {
		super();
		this.client = new Client();
	}

	//Getter & Setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public List<Client> getListeCl() {
		return listeCl;
	}

	public void setListeCl(List<Client> listeCl) {
		this.listeCl = listeCl;
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
			return seConnecter();
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué."));
			return "ajoutClient";
		}
	}

}
