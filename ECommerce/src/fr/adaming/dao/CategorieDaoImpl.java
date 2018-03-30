package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao{

	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	@Override
	public Categorie addCategorie(Categorie cat) {
		em.persist(cat);
		return cat;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		String req="SELECT cat FROM Categorie AS cat";
		Query query=em.createQuery(req);
		List<Categorie> listeCat=query.getResultList();
		for (Categorie cat : listeCat){
			cat.setImage("data:image/png;base64,"+Base64.encodeBase64String(cat.getPhoto()));
		}
		return listeCat;
	}


}
