package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@ManagedBean(name="cmdMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {
	
	// Transformation de l'association UML en java
	@EJB
	private ICommandeService commandeService;
	
	// D�claration des attributs envoy�s � la page
	private Commande commande;
	private Client client;
	private List<Commande> listeCommande;
	private boolean indice;
	private HttpSession maSession;
	
	@PostConstruct
	public void inti(){
		
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeCommande=commandeService.getAllCommandesCl(commande, client);
		
	}
	
	
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
	
	// M�thodes m�tiers
		
	public String creerCommande(){
		
		Commande cmdAjout = commandeService.addCommande(commande);
		
		if (cmdAjout.getIdCommande() != 0){
			
			List<Commande> liste = commandeService.getAllCommandesCl(this.commande,this.client);
			
			maSession.setAttribute("commandesListe", liste);
			
			return "accueilClient";
		}else{
			
			return "ajoutCmd";
			
		}
		
	}
	
	public String rechercherCommande(){
		
		try{
			
			this.commande=commandeService.getCommandeById(commande);
			this.indice=true;
			
			}catch (Exception ex){
			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande recherch�e n'existe pas"));
				this.indice=false;
				
			}
			return "rechCmd";
		
	}
	
	public String rechercherCommandeIdCl(){
		
try{
			
	listeCommande=commandeService.getCommandeByIdCl(commande, client);
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("commandesListe", listeCommande);
	this.indice=true;
	Administrateur adm=(Administrateur) maSession.getAttribute("adminSession");
	
	if (adm != null){
		
		return "rechCmdCl";
		
	}else{
		
		return "accueilAdmin";
		
	}
			
	}catch (Exception ex){
			
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande recherch�e n'existe pas"));
		this.indice=false;
				
	}
			return "accueilAdmin";
		
	}

}
