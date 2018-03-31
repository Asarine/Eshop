package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {

	public Categorie addCategorie(Categorie cat);
	
	public List<Categorie> getAllCategorie();
	
	public int deleteCategorie(Categorie cat);
}
