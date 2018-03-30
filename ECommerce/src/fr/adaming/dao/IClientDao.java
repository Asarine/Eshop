package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
	public Client isExist(Client cl);
	public Client addClient(Client cl);

}
