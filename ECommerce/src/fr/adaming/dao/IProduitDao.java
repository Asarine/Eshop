package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public List<Produit> getAllProduits(Produit pr);
	
	public Produit addProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);
	
	public List<Produit> GetProduitByCat(Categorie cat);
	
	public int deleteProduit (Produit pr);
	
	public int updateProduit (Produit pr);
	
	public Produit getProduitByMotCle(Produit pr);

}
