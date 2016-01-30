package sma;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import agents.Agent;
import agents.Chasseur;
import agents.Mur;
import model.Environnement;
import model.PacManEnvironnement;

public class SMAPacMan extends SMA{
	
	private int murs;
	private int chasseurs;

	public SMAPacMan(int nbChasseurs, int nbMurs, int taille, int tAgent, boolean torique, int seed) {
		super(taille, tAgent, torique, seed);

		this.launch(nbChasseurs, nbMurs, torique, seed);
	}

	public void launch(int nbChasseurs, int nbMurs, boolean torique, int seed){
		this.chasseurs = nbChasseurs;
		this.murs = nbMurs;
		this.environnement = new Environnement(this, this.taille, torique, agents);
		this.environnement.init(seed);
		
	}

	@Override
	public int getNbAgent() {
		return murs+chasseurs;
	}

	@Override
	public Agent getNewAgent(int i) {
		if (i < murs) {
			return new Mur(0,0,this.environnement);
		}
		else {
			return new Chasseur(0,0,(PacManEnvironnement) this.environnement);
		}
	}

	@Override
	public void run(){
		
		while (running || !running){
			//TODO attendre que le joueur est touch� une touche de d�placement
			this.round();
		}
	}

	@Override
	public void round(){
			if(running){
				try{
					ArrayList<Agent> agentBis = new ArrayList<Agent>(agents); 
					for(Agent a : agentBis){
						a.doIt();
					}
				}catch(ConcurrentModificationException e){
				
				}
				
				this.setChanged();
				this.notifyObservers();
				tour++;
			}
	}

}