package fr.adaming.model;

import java.util.List;

public class Panier {
	
	private List<LigneCommande> listeLignes;

	public Panier() {
		super();
	}

	public List<LigneCommande> getListeLignes() {
		return listeLignes;
	}

	public void setListeLignes(List<LigneCommande> listeLignes) {
		this.listeLignes = listeLignes;
	}

}
