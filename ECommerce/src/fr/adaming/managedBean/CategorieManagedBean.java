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

import org.primefaces.event.RowEditEvent;
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
	
	@EJB
	private ICategorieService categorieService;

	//Constructeur vide
	public CategorieManagedBean() {
		super();
		this.cat= new Categorie();
		this.uf=new UploadedFileWrapper();
	}
	
	@PostConstruct
	public void inti(){
		this.listecat=categorieService.getAllCategorie();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieList", listecat);
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
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

	//Méthode métier
	public void ajoutCategorie(){
		this.cat.setNomCategorie("Suricate");
		this.cat.setDescription("Un petit animal tout mignon mignon");
		this.cat.setPhoto(this.uf.getContents());
		Categorie catAjout=categorieService.addCategorie(cat);
		if (catAjout.getIdCategorie()!=0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a été effectué."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué."));
		}
	}
	
	public void suppCategorie(){
		int verif=categorieService.deleteCategorie(this.cat);
		if (verif!=0){
			List<Categorie> listeCategories=categorieService.getAllCategorie();
			maSession.setAttribute("categorieList", listeCategories);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectuée"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression n'a pas été effectuée."));
		}
	}

}
