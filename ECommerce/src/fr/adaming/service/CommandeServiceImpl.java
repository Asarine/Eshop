package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService {
	
	@EJB
	private ICommandeDao commandeDao;

	@Override
	public List<Commande> getAllCommandesCl(Commande cmd,Client cl) {
		
		return null;
	}

	@Override
	public List<Commande> getAllCommandesAdm(Commande cmd) {
		
		return null;
	}

	@Override
	public Commande addCommande(Commande cmd) {
		
		return commandeDao.addCommande(cmd);
	}

	@Override
	public Commande getCommandeById(Commande cmd) {
		
		return commandeDao.getCommandeById(cmd);
	}
	
	@Override
	public List<Commande> getCommandeByIdCl(Commande cmd, Client cl) {
		
		return commandeDao.getCommandeByIdCl(cmd, cl);
	}

	@Override
	public int deleteCommande(Commande cmd) {
		
		return commandeDao.deleteCommande(cmd);
	}



}
