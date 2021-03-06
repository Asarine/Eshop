package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{

	@EJB
	private ICategorieDao categorieDao;
	
	@Override
	public Categorie addCategorie(Categorie cat) {
		return categorieDao.addCategorie(cat);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		return categorieDao.getAllCategorie();
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		return categorieDao.deleteCategorie(cat);
	}

	@Override
	public int updateCategorie(Categorie cat) {
		return categorieDao.updateCategorie(cat);
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		return categorieDao.getCategorieById(cat);
	}
	
	

}
