package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientService {

	public Client isExist(Client cl);
}
