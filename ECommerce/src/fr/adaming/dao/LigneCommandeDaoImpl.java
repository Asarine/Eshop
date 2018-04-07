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

	@Override
	public int updateLigneCommande(LigneCommande lgComm) {
		String req1="UPDATE LigneCommande AS lComm SET lComm.quantite=:pQuantite, lComm.prix=:pPrix, lComm.prod.idProduit=:pIdProduit, lComm.commande.idCommande=:pIdCommande WHERE lComm.idLComm=:pId";
		Query query1=em.createQuery(req1);
		query1.setParameter("pQuantite", lgComm.getQuantite());
		query1.setParameter("pPrix", lgComm.getPrix());
		query1.setParameter("pIdProduit", lgComm.getProd().getIdProduit());
		query1.setParameter("pIdCommande", lgComm.getCommande().getIdCommande());
		query1.setParameter("pId", lgComm.getIdLComm());
		
		return query1.executeUpdate();
	}

	@Override
	public void deleteLigneCommande(LigneCommande lgComm) {
		String req="DELETE LigneCommande AS lComm WHERE lComm.idLComm=:pId";
		Query query=em.createQuery(req);
		query.setParameter("pId", lgComm.getIdLComm());
		int i=query.executeUpdate();
	}

	@Override
	public LigneCommande getLigneById(long id) {
		return em.find(LigneCommande.class, id);
	}

	@Override
	public int updateLigneCommande2(LigneCommande lgComm) {
		String req1="UPDATE LigneCommande AS lComm SET lComm.quantite=:pQuantite, lComm.prix=:pPrix, lComm.prod.idProduit=:pIdProduit WHERE lComm.idLComm=:pId";
		Query query1=em.createQuery(req1);
		query1.setParameter("pQuantite", lgComm.getQuantite());
		query1.setParameter("pPrix", lgComm.getPrix());
		query1.setParameter("pIdProduit", lgComm.getProd().getIdProduit());
		query1.setParameter("pId", lgComm.getIdLComm());
		
		return query1.executeUpdate();
	}

}
