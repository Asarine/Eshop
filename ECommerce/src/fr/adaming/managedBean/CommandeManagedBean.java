package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@ManagedBean(name="@cmdMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {
	
	// Transformation de l'association UML en java
	@EJB
	private ICommandeService commandeService;
	
	// Déclaration des attributs envoyés à la page
	private Commande commande;
	private Client client;
	private List<Commande> listeCommande;
	private boolean indice;
	private HttpSession maSession;
	
	
	// Constructeurs
	
	public CommandeManagedBean() {
		this.commande=new Commande();
		this.indice=false;
		this.client=new Client();
	}


	public ICommandeService getCommandeService() {
		return commandeService;
	}


	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public List<Commande> getListeCommande() {
		return listeCommande;
	}


	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}


	public boolean isIndice() {
		return indice;
	}


	public void setIndice(boolean indice) {
		this.indice = indice;
	}


	public HttpSession getMaSession() {
		return maSession;
	}


	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}
	
	// Méthodes métiers
	
	public String creerCommande(){
		
		Commande cmdAjout = commandeService.addCommande(commande);
		
		if (cmdAjout.getIdCommande() != null){
			
			List<Commande> liste = commandeService.getAllCommandesCl(this.commande);
			
			maSession.setAttribute("commandesListe", liste);
			
			return "accueilClient";
		}else{
			
			return "ajoutCmd";
			
		}
		
	}

}
