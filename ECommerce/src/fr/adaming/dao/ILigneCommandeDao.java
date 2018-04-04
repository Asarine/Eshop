package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeDao {
	
	public List<LigneCommande> getLignesCommandeByIdCommande(Commande com);
	
	public LigneCommande addLigneCommande(LigneCommande lgComm);
	
	public int updateLigneCommande(LigneCommande lgComm);
	

}
