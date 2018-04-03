package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	private Categorie cat;
	
	@Override
	public List<Produit> getAllProduits(Produit pr) {
		
		// requete jpql
		String req="SELECT pr FROM Produit pr";
								
		//Créer un objet query pour envoyer la requête jpql
		Query query=em.createQuery(req);
						
		//envoi de la requete et recup du résultat
				
		return query.getResultList();
			
	}
	
	@Override
	public Produit addProduit(Produit pr) {
		
		pr.setImage("data:image/png;base64,"+Base64.encodeBase64String(cat.getPhoto()));
		em.persist(pr);
		
		return pr;
	}

	
	@Override
	public Produit getProduitById(Produit pr) {
		
		// Création du query
		
		String req="SELECT pr FROM Produit pr WHERE pr.id=:pIdPr";
		
		Query query=em.createQuery(req);
		
		// Passage des params		
		query.setParameter("pIdPr", pr.getIdProduit());
		
		// Envoi et recup du résultat
				
		return (Produit) query.getSingleResult();
	}
	
	@Override
	public List<Produit> GetProduitByCat(Categorie cat) {
		
		// Création du query
		
		String req3="SELECT pr FROM Produit pr WHERE pr.cat.idCategorie=:pCatid";
				
		Query query3=em.createQuery(req3);
		
		// Passage des params		
		query3.setParameter("pCatid", cat.getIdCategorie());
				
		// Envoi et recup du résultat
						
		 List<Produit> listeProdCat=query3.getResultList();
		 return listeProdCat;
	
	}
	

	@Override
	public Produit getProduitByMotCle(Produit pr) {
		
		// Création de la requête jpql
		String req = "SELECT ad FROM Administrateur ad WHERE ad.description LIKE %recherche%";
		
		// Création objet query
		Query query=em.createQuery(req);
			
		
		return null;
	}

	@Override
	public int deleteProduit(Produit pr) {
		
		em.remove(pr);
		
		return 0;
	}

	@Override
	public int updateProduit(Produit pr) {
		
		String req="UPDATE Produit pr SET pr.designation=:pDesignation, pr.description=:pDescription, pr.prix=:pPrix, pr.quantite=:pQuantite, pr.selectionne=:pSelectionne, pr.photo=:pPhoto WHERE pr.idproduit=:pIdproduit";
		
		Query query=em.createQuery(req);
		
		query.setParameter("pDesignation", pr.getDesignation());
		query.setParameter("pDescription", pr.getDescription());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQuantite", pr.getQuantite());
		query.setParameter("pSelectionne", pr.isSelectionne());
		query.setParameter("pPhoto", pr.getPhoto());
		
		int verif=query.executeUpdate();
		
		return verif;
	}
	
	

}
