package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {
	
	public List<Commande> getAllCommandesCl(Commande cmd);
	
	public List<Commande> getAllCommandesAdm(Commande cmd);
	
	public Commande addCommande(Commande cmd);
	
	public Commande getCommandeById(Commande cmd);
	
	public List<Commande> getCommandeByIdCl(Commande cmd, Client cl);
	
	public int deleteCommande(Commande cmd);

}
