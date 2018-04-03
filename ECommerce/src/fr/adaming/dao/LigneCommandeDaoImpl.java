package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao{
	
	@PersistenceContext(unitName="PU")
	private EntityManager em;

	@Override
	public List<LigneCommande> getLignesCommandeByIdCommande(Commande com) {
		String req="SELECT lgComm FROM LigneCommande AS lgComm WHERE lgComm.commande.idCommande=:pIdComm";
		Query query=em.createQuery(req);
		query.setParameter("pIdComm", com.getIdCommande());
		return query.getResultList();
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lgComm) {
		em.persist(lgComm);
		return lgComm;
	}

}
