package fr.adaming.managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {
	
	//Attributs
	private Client client;
	
	@EJB
	private IClientService clientService;

	//Constructeur vide
	public ClientManagedBean() {
		super();
	}

	//Getter & Setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	//Méthodes métiers
	

}
