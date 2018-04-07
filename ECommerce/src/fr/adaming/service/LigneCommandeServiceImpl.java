package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{
	
	@EJB
	private ILigneCommandeDao ligneDao;

	@Override
	public List<LigneCommande> getLignesCommandeByIdCommande(Commande com) {
		return ligneDao.getLignesCommandeByIdCommande(com);
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lgComm) {
		return ligneDao.addLigneCommande(lgComm);
	}

	@Override
	public int updateLigneCommande(LigneCommande lgComm) {
		return ligneDao.updateLigneCommande(lgComm);
	}

	@Override
	public void deleteLigneCommande(LigneCommande lgComm) {
		ligneDao.deleteLigneCommande(lgComm);
		
	}

	@Override
	public LigneCommande getLigneById(long id) {
		return ligneDao.getLigneById(id);
	}

	@Override
	public int updateLigneCommande2(LigneCommande lgComm) {
		return ligneDao.updateLigneCommande2(lgComm);
	}
	
	
		
	

}
