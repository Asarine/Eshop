package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	public List<Produit> getAllProduits();
	
	public Produit addProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);
	
	public List<Produit> GetProduitByCat(Categorie cat);
	
	public int deleteProduit (Produit pr);
	
	public int updateProduit (Produit pr);
	
	public Produit getProduitByMotCle(Produit pr);

}
