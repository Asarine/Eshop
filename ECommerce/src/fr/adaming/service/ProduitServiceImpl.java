package fr.adaming.service;

import javax.ejb.EJB;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;

public class ProduitServiceImpl implements IProduitService {

	@EJB
	private IProduitDao produitDao;

	@Override
	public Produit addProduit(Produit pr) {
		
		return produitDao.addProduit(pr);
	}
	
	@Override
	public Produit getProduitById(Produit pr) {
		
		return produitDao.getProduitById(pr);
	}
	
	@Override
	public Produit getProduitByMotCle(Produit pr) {
		
		return null;
	}




}