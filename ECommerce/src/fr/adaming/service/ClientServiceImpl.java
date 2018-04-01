package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService {

	//Transformation de l'association UML en Java
	@EJB
	private IClientDao clientDao;

	@Override
	public Client isExist(Client cl) {
		return clientDao.isExist(cl);
	}

	@Override
	public Client addClient(Client cl) {
		return clientDao.addClient(cl);
	}

	@Override
	public int modifClient(Client cl) {
		return clientDao.modifClient(cl);
	}

	@Override
	public int deleteClient(Client cl) {
		return clientDao.deleteClient(cl);
	}

	@Override
	public Client getClientbyId(Client cl) {
		return clientDao.getClientbyId(cl);
	}
	
	


}
