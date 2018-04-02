package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {
	
	@PersistenceContext(unitName="PU")
	private EntityManager em;

	@Override
	public List<Commande> getAllCommandesCl(Commande cmd) {
		
		// requete jpql
		String req="SELECT cmd FROM Commande cmd";
										
		//Créer un objet query pour envoyer la requête jpql
		Query query=em.createQuery(req);
								
		//envoi de la requete et recup du résultat
						
		return query.getResultList();
	}

	@Override
	public List<Commande> getAllCommandesAdm(Commande cmd) {
		
		// requete jpql
		String req="SELECT cmd FROM Commande cmd";
												
		//Créer un objet query pour envoyer la requête jpql
		Query query=em.createQuery(req);
										
		//envoi de la requete et recup du résultat
								
		return query.getResultList();
	}

	@Override
	public Commande addCommande(Commande cmd) {
		
		em.persist(cmd);
		
		return cmd;
	}

	@Override
	public Commande getCommandeById(Commande cmd) {
		
		// Création du query
		
		String req="SELECT cmd FROM Commande cmd WHERE cmd.idCommande=:pIdCommande";
				
		Query query=em.createQuery(req);
				
		// Passage des params		
		query.setParameter("pIdCommande", cmd.getIdCommande());
				
		// Envoi et recup du résultat
						
		return (Commande) query.getSingleResult();
			
	}
	
	@Override
	public List<Commande> getCommandeByIdCl(Commande cmd,Client cl) {
		
		// Création du query
		
		String req="SELECT cmd FROM Commande cmd WHERE cmd.cl.idClient=:pClid";
						
		Query query=em.createQuery(req);
				
		// Passage des params		
		query.setParameter("pClid", cl.getIdClient());
						
		// Envoi et recup du résultat
								
		List<Commande> listeCommande=query.getResultList();
		return listeCommande;
	}

	@Override
	public int deleteCommande(Commande cmd) {
		
		em.remove(cmd);
		
		return 0;
	}

	

}
