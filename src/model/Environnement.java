package model;

public class Environnement {

	private Cellule espace [][];

	public Environnement(int taille){
		
		this.espace= new Cellule [taille][taille];

	}

	public void init(){

	}

	public Cellule[][] getEspace(){

		return this.espace;

	}

}
