package sma;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import agents.Agent;
import agents.Avatar;
import agents.Chasseur;
import agents.Mur;
import model.Environnement;
import model.PacManEnvironnement;

public class SMAPacMan extends SMA{

	private int murs;
	private int chasseurs;
	private Avatar avatar;

	public SMAPacMan(int nbChasseurs, int nbMurs, int taille, int tAgent, boolean torique, int seed) {
		super(taille, tAgent, torique, seed);
		this.launch(nbChasseurs, nbMurs, torique, seed);
	}

	public void launch(int nbChasseurs, int nbMurs, boolean torique, int seed){
		this.chasseurs = nbChasseurs;
		this.murs = nbMurs;
		//la tu a mis Environnement et pas PacMacEnvironement Voyons tu t'auto boycot !
		//this.environnement = new Environnement(this, this.taille, torique, agents);

		this.environnement = new PacManEnvironnement(this, this.taille, torique, agents);
		this.environnement.init(seed);
		// On place le joueur :
		int x =this.environnement.getRandomCoord(-1);
		int y =this.environnement.getRandomCoord(x);
		avatar = new Avatar(x,y,this.environnement);
		this.environnement.addAgent(avatar);
		((PacManEnvironnement)this.environnement).setAvatar(avatar);//temp a supprimer rapidement

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
			return new Chasseur(0,0, this.environnement);
		}
	}

	@Override
	public void run(){
		int x = ((PacManEnvironnement)this.environnement).avatar.getPosX();
		int y = ((PacManEnvironnement)this.environnement).avatar.getPosY();
		boolean stop = !((PacManEnvironnement)this.environnement).getFinish();
		while (running || !running && stop){	  


			x = ((PacManEnvironnement)this.environnement).avatar.getPosX();
			y = ((PacManEnvironnement)this.environnement).avatar.getPosY();

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

	public int getNbChasseurs() {
		return this.chasseurs;
	}

	public int getNbMurs(){
		return this.murs;
	}
	
	public Avatar getAvatar(){
		return this.avatar;
	}

}
