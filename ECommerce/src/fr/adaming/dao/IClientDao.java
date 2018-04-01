package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
	public Client isExist(Client cl);
	
	public Client addClient(Client cl);
	
	public int modifClient(Client cl);
	
	public int deleteClient(Client cl);
	
	public Client getClientbyId(Client cl);
	

}
