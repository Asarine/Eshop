package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {

	@EJB
	private IProduitDao produitDao;
	
	@Override
	public List<Produit> getAllProduits(Produit pr) {
		
		return null;
	}

	@Override
	public Produit addProduit(Produit pr) {
		
		return produitDao.addProduit(pr);
	}
	
	@Override
	public Produit getProduitById(Produit pr) {
		
		return produitDao.getProduitById(pr);
	}
	
	@Override
	public List<Produit> GetProduitByCat(Categorie cat) {
		
		return produitDao.GetProduitByCat(cat);
	}
	
	@Override
	public Produit getProduitByMotCle(Produit pr) {
		
		return null;
	}



}
