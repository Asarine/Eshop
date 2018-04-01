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

	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	@Override
	public int modifClient(Client cl) {
		String req1="UPDATE Client cl SET cl.nomClient=:pNom, cl.adresse=:pAdresse, cl.codePostal=:pCodepost, cl.ville=:pVille, cl.email=:pMail, cl.tel=:pTel, cl.mdp=:pMdp WHERE cl.idClient=:pId";
		Query query1 =em.createQuery(req1);
		query1.setParameter("pNom", cl.getNomClient());
		query1.setParameter("pAdresse", cl.getAdresse());
		query1.setParameter("pCodepost", cl.getCodePostal());
		query1.setParameter("pVille", cl.getVille());
		query1.setParameter("pMail", cl.getEmail());
		query1.setParameter("pTel", cl.getTel());
		query1.setParameter("pMdp", cl.getMdp());
		query1.setParameter("pId", cl.getIdClient());
		return query1.executeUpdate();
	}

	@Override
	public int deleteClient(Client cl) {
		String req2="DELETE Client cl WHERE cl.idClient=:pId";
		Query query2=em.createQuery(req2);
		query2.setParameter("pId", cl.getIdClient());
		return query2.executeUpdate();
	}

	@Override
	public Client getClientbyId(Client cl) {
		String req3="SELECT cl FROM Client cl WHERE cl.idClient=:pId";
		Query query3=em.createQuery(req3);
		query3.setParameter("pId", cl.getIdClient());
		Client clOut =(Client) query3.getSingleResult();
		return clOut;
	}
	
	
	

}
