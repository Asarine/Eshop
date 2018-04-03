package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

@Local
public interface ILigneCommandeService {
	
	public List<LigneCommande> getLignesCommandeByIdCommande(Commande com);
	
	public LigneCommande addLigneCommande(LigneCommande lgComm);

}
