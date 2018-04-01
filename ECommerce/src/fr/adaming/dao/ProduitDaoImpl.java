package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceUnit(unitName="PU")
	private EntityManager em;
	
	@Override
	public Produit addProduit(Produit pr) {
		
		em.persist(pr);
		
		return pr;
	}

	
	@Override
	public Produit getProduitById(Produit pr) {
		
		// Cr�ation du query
		
		String req="SELECT pr FROM Produit pr WHERE pr.id=:pIdPr";
		
		Query query=em.createQuery(req);
		
		// Passage des params		
		query.setParameter("pIdPr", pr.getIdProduit());
		
		// Envoi et recup du r�sultat
				
		return (Produit) query.getSingleResult();
	}
	
	@Override
	public Produit GetProduitByCat(Produit pr) {
		
		// Cr�ation du query
		
		String req="SELECT pr FROM Produit pr WHERE pr.cat_id=:pCat_id";
				
		Query query=em.createQuery(req);
		
		// Passage des params		
		query.setParameter("pCat_id", pr.getIdProduit());
				
		// Envoi et recup du r�sultat
						
		return (Produit) query.getSingleResult();
	
	}
	

	@Override
	public Produit getProduitByMotCle(Produit pr) {
		
		// Cr�ation de la requ�te jpql
		String req = "SELECT ad FROM Administrateur ad WHERE ad.description LIKE %recherche%";
		
		// Cr�ation objet query
		Query query=em.createQuery(req);
			
		
		return null;
	}



	
	

}
