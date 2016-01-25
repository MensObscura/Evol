package sma;

import model.Environnement;

public class SMAWator extends SMA {

	public SMAWator(int nbNemos, int nbRequins, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed, int reproNemo, int reproRequin, int faimRequin){
		super(taille, tAgent, vitesse, torique, visibleGrid, equit, seed);
		this.launch(nbNemos, nbRequins, seed,vitesse, torique, visibleGrid, equit,reproNemo, reproRequin, faimRequin);///tmp

	}
	
	public void launch(int nbNemos, int nbRequins, int seed, int vitesse, boolean torique, boolean grille,boolean equit,int reproNemo,int reproRequin,int faimRequin){

		this.equit = equit;
		this.visibleGrid = grille;
		this.vitesse = vitesse;
		this.environnement = new Environnement(this.taille, nbNemos, nbRequins, seed, torique, agents, reproNemo, reproRequin, faimRequin);

	}

}
