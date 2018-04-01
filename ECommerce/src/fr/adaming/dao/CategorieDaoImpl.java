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

	@Override
	public int deleteCategorie(Categorie cat) {
		String req1="DELETE Categorie AS cat WHERE cat.idCategorie=:pId";
		Query query1=em.createQuery(req1);
		query1.setParameter("pId", cat.getIdCategorie());
		return query1.executeUpdate();
		}

	@Override
	public int updateCategorie(Categorie cat) {
		String req2="UPDATE Categorie AS cat SET cat.nomCategorie=:pNomCat, cat.photo=:pPhoto, cat.description=:pDesc WHERE cat.idCategorie=:pId";
		Query query2=em.createQuery(req2);
		query2.setParameter("pNomCat", cat.getNomCategorie());
		query2.setParameter("pPhoto", cat.getPhoto());
		query2.setParameter("pDesc", cat.getDescription());
		query2.setParameter("pId", cat.getIdCategorie());
		return query2.executeUpdate();
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		String req3="SELECT cat FROM Categorie AS cat WHERE cat.idCategorie=:pId";
		Query query3=em.createQuery(req3);
		query3.setParameter("pId", cat.getIdCategorie());
		Categorie catOut=(Categorie) query3.getSingleResult();
		catOut.setImage("data:image/png;base64,"+Base64.encodeBase64String(catOut.getPhoto()));
		return catOut;
	}


}
