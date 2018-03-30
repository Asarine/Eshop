package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{
	
	//Attributs
	private Categorie cat;
	private UploadedFile uf;
	
	@EJB
	private ICategorieService categorieService;

	//Constructeur vide
	public CategorieManagedBean() {
		super();
		this.cat= new Categorie();
		this.uf=new UploadedFileWrapper();
	}
	
	

	//Getter et setter
	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}


	
	
	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	//Méthode métier
	public String ajoutCategorie(){
		this.cat.setNomCategorie("Suricate");
		this.cat.setDescription("Un petit animal tout mignon mignon");
		this.cat.setPhoto(this.uf.getContents());
		Categorie catAjout=categorieService.addCategorie(cat);
		if (catAjout.getIdCategorie()!=0){
			List<Categorie> listecat=categorieService.getAllCategorie();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieList", listecat);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a été effectué."));
			return "accueilClient";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué."));
			return "accueilClient";
		}
	}
	

}
