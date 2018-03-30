package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	public Produit addProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);
	
	public Produit getProduitByMotCle(Produit pr);

}
