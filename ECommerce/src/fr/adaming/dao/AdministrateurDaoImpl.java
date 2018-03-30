package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Administrateur;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	@Override
	public Administrateur isExist(Administrateur ad) {
		
		// Cr�ation de la requ�te jpql
		String req = "SELECT ad FROM Administrateur ad WHERE ad.mail=:pMail AND ad.mdp=:pMdp";
		
		// Cr�ation objet query
		Query query=em.createQuery(req);
		
		// Passage des params
		query.setParameter("pMail", ad.getMail());
		query.setParameter("pMdp", ad.getMdp());
		
		// Envoi de la requ�te et r�cup�ration du r�sultat
		return (Administrateur) query.getSingleResult();
		
	}

}
