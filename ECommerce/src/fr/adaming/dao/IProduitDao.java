package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public Produit addProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);
	
	public Produit getProduitByMotCle(Produit pr);

}
