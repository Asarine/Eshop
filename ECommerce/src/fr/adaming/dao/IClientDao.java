package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
	public Client isExist(Client cl);

}
