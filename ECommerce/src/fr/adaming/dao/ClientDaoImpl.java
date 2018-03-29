package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao{

	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	@Override
	public Client isExist(Client cl) {
		String req="SELECT cl FROM Client AS cl WHERE cl.email=:pEmail AND cl.mdp=:pMdp";
		Query query =em.createQuery(req);
		query.setParameter("pEmail", cl.getEmail());
		query.setParameter("pMdp", cl.getMdp());
		return (Client) query.getSingleResult();
	}
	
	

}
