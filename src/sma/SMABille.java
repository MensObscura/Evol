package sma;

import model.Environnement;

public class SMABille  extends SMA {

	public SMABille(int nbBilles, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		super(taille, tAgent, vitesse, torique, visibleGrid, equit, seed);
		this.launch(nbBilles, seed,vitesse, torique, visibleGrid, equit);///tmp

	}
	
	public void launch(int nbBilles, int seed, int vitesse, boolean torique, boolean grille,boolean equit){

		this.equit = equit;
		this.visibleGrid = grille;
		this.vitesse = vitesse;
		this.environnement = new Environnement(this.taille,nbBilles, seed, torique, agents);

	}

}
