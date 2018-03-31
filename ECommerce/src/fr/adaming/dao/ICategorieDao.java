package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieDao {
	
	public Categorie addCategorie(Categorie cat);
	
	public List<Categorie> getAllCategorie();
	
	public int deleteCategorie(Categorie cat);

}
