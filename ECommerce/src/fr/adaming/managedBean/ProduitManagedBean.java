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

import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {
	
	// Transformation de l'association UML en java
	@EJB
	private IProduitService produitService;
	
	// Déclaration des attributs envoyés à la page
	private Produit produit;
	private Categorie categorie;
	private List<Produit> listeProduit;
	private boolean indice;
	private boolean indiceCat;
	private HttpSession maSession;
	
	private UploadedFile uf;

	// Constructeurs
	public ProduitManagedBean() {
		this.produit=new Produit();
		this.indice=false;
		this.categorie=new Categorie();
		this.indiceCat=false;
		this.uf=new UploadedFileWrapper();
	}

	@PostConstruct
	public void inti(){
		
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeProduit=produitService.getAllProduits();
		
	}
	// Getters et setters
	public IProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	
		public boolean isIndiceCat() {
		return indiceCat;
	}

	public void setIndiceCat(boolean indiceCat) {
		this.indiceCat = indiceCat;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}
	
	
	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	// Methodes métiers
	public String ajouterProduit(){
		this.produit.setPhoto(this.uf.getContents());
		Produit prAjout = produitService.addProduit(produit);
		if (prAjout.getIdProduit() != null){
			
		// Récupérer la nouvelle liste
		List<Produit> liste = produitService.getAllProduits();
			
		maSession.setAttribute("produitsListe", liste);
		
		return "accueilAdmin";
			
		}else{
			
			return "ajoutpr";
			
		}
		
	}
	
	public String rechercherProduit(){
		
		try{
			
		this.produit=produitService.getProduitById(produit);
		this.indice=true;
		
		}catch (Exception ex){
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit recherché n'existe pas"));
			this.indice=false;
			
		}
		return "rechpr";
	}
	
	public String rechercherProduitCat(){
		
		try{
		
		listeProduit=produitService.GetProduitByCat(categorie);
		this.indiceCat=true;
		Client cl=(Client) maSession.getAttribute("clientSession");
		if (cl!=null){
		return "accueilClient";
		}else{
			return "accueil";
		}
		
		}catch (Exception ex){
		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie recherchée n'existe pas"));
			this.indiceCat=false;
			return "accueilClient";
			
		}
	}
	
	public String modifierProduit(){
		
		this.produit.setPhoto(this.uf.getContents());
		int prModif = produitService.updateProduit(this.produit);
		
		if (prModif != 0){
			
			List<Produit> liste = produitService.getAllProduits();
			
			maSession.setAttribute("produitsListe", liste);
			
			return "accueilAdmin";
			
		}else{
			
			return "modifPr";
			
		}
		
	}
	
	public String supprimerProduit(){
		
		int prSupp = produitService.deleteProduit(this.produit);
		
		if (prSupp != 0){
			
			List<Produit> liste = produitService.getAllProduits();
			
			maSession.setAttribute("produitsListe", liste);
			
			return "accueilAdmin";
			
		}else{
			
			return "suppPr";
			
		}
		
	}

}
