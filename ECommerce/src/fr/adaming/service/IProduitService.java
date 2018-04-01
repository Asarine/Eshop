package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	public List<Produit> getAllProduits(Produit pr);
	
	public Produit addProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);
	
	public Produit GetProduitByCat(Produit pr);
	
	public Produit getProduitByMotCle(Produit pr);

}
