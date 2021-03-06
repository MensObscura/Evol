package projet.billes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

import core.agents.Agent;
import core.model.Environnement;
import core.sma.SMASimulation;

public class SMABille  extends SMASimulation {
	
	private int nbBilles;

	public SMABille(int nbBilles, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		super(taille, tAgent, vitesse, torique, visibleGrid, equit, seed);
		this.launch(nbBilles, seed,vitesse, torique, visibleGrid, equit);

	}
	
	public void launch(int nbBilles, int seed, int vitesse, boolean torique, boolean grille,boolean equit){
		this.nbBilles=nbBilles;
		this.equit = equit;
		this.visibleGrid = grille;
		this.seed = seed;
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

	@Override
	public void round(){

			if(running){
				if(this.isEquit())
					Collections.shuffle(this.agents);

				try{
					ArrayList<Agent> agentBis = new ArrayList<Agent>(agents); 
					
					for(Agent a : agentBis){
						a.doIt();
					}
				}catch(ConcurrentModificationException e){
					e.printStackTrace();
				}
				
				this.setChanged();
				this.notifyObservers();
				tour++;

			}
	}
	
	@Override
	public void setVitesse(int i){
		if(i < 8)
			i=8;
		super.setVitesse(i);
	}

}
