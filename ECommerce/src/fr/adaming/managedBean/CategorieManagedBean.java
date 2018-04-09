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
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{
	
	//Attributs
	private Categorie cat;
	private UploadedFile uf;
	private List<Categorie> listecat;
	private HttpSession maSession;
	private boolean indice=false;
	
	@EJB
	private ICategorieService categorieService;

	//Constructeur vide
	public CategorieManagedBean() {
		
		this.cat= new Categorie();
		this.uf=new UploadedFileWrapper();
	}
	
	@PostConstruct
	public void inti(){
		
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listecat=categorieService.getAllCategorie();
		
	}

	//Getter et setter
	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}	
	
	public List<Categorie> getListecat() {
		return listecat;
	}

	public void setListecat(List<Categorie> listecat) {
		this.listecat = listecat;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}
	

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	//Méthode métier
	public void ajoutCategorie(){
		this.cat.setPhoto(this.uf.getContents());
		Categorie catAjout=categorieService.addCategorie(cat);
		if (catAjout.getIdCategorie()!=0){
			List<Categorie> listeCategorie=categorieService.getAllCategorie();
			maSession.setAttribute("categorieList", listeCategorie);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a été effectué."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué."));
		}
	}
	
	public void suppCategorie(){
		System.out.println(this.cat.getIdCategorie());
		int verif=categorieService.deleteCategorie(this.cat);
		if (verif!=0){
			List<Categorie> listeCategories=categorieService.getAllCategorie();
			maSession.setAttribute("categorieList", listeCategories);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectuée"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression n'a pas été effectuée."));
		}
	}

	public void modifCategorie(){
		this.cat.setPhoto(this.uf.getContents());
		int verif=categorieService.updateCategorie(cat);
		if (verif!=0){
			List<Categorie> listeCategorie=categorieService.getAllCategorie();
			maSession.setAttribute("categorieList", listeCategorie);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a été effectuée."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification n'a pas été effectuée."));
		}
	}
	
	public void chercherCategorie(){
		Categorie catOut=categorieService.getCategorieById(this.cat);
		if (catOut!=null){
			this.cat=catOut;
			this.indice=true;
		}else{
			this.indice=false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucune catégorie trouvée"));
		}
	}
	
	public String modifierCatAvecLien(){
		Categorie catOut=categorieService.getCategorieById(this.cat);
		if (catOut!=null){
			this.cat=catOut;
			return "modifCatAdmin";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur"));
			return "AfficherCategoriesAdmin";
		}
	}
		
	
}
