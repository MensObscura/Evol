package sma;

import agents.Agent;
import agents.Bille;
import model.Environnement;

public class SMABille  extends SMA {
	
	private int nbBilles;

	public SMABille(int nbBilles, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		super(taille, tAgent, vitesse, torique, visibleGrid, equit, seed);
		this.launch(nbBilles, seed,vitesse, torique, visibleGrid, equit);

	}
	
	public void launch(int nbBilles, int seed, int vitesse, boolean torique, boolean grille,boolean equit){
		this.nbBilles=nbBilles;
		this.equit = equit;
		this.visibleGrid = grille;
		this.vitesse = vitesse;
		this.environnement = new Environnement(this,this.taille, torique, agents);
		this.environnement.init(seed);

	}

	@Override
	public int getNbAgent() {
		
		return this.nbBilles;
	}

	@Override
	public Agent getNewAgent(int i) {
		
		return new Bille(0,0,this.environnement);
	}

	public int getNbBilles() {
		return this.nbBilles;
	}

}
