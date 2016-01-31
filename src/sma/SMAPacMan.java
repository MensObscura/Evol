package sma;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import agents.Agent;
import agents.Avatar;
import agents.Chasseur;
import agents.Mur;
import model.Environnement;

public class SMAPacMan extends SMA{

	private int murs;
	private int chasseurs;
	private Avatar avatar;
	private boolean finish;
	
	private int distances [][];

	public SMAPacMan(int nbChasseurs, int nbMurs, int taille, int tAgent, boolean torique, boolean grille, int seed) {
		super(taille, tAgent, torique, seed, grille);
		this.launch(nbChasseurs, nbMurs, torique, seed, grille);
	}

	public void launch(int nbChasseurs, int nbMurs, boolean torique, int seed, boolean visibleGrid){
		this.finish = false;
		this.chasseurs = nbChasseurs;
		this.murs = nbMurs;
		this.visibleGrid = visibleGrid;
		
		// Init tableau des distances
		this.distances = new int[taille][taille];
			
		this.environnement = new Environnement(this, this.taille, torique, agents);
				
		this.environnement.init(seed);
				
		// On place le joueur :
		int x =this.environnement.getRandomCoord(-1);
		int y =this.environnement.getRandomCoord(x);
		avatar = new Avatar(x,y,this.environnement);
		this.environnement.addAgent(avatar);
				
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
		while (running || !running){	  

			this.round();
		}
	}

	@Override
	public void round(){
		if(running && !finish){
			try{
				this.calculDistances();
				
				ArrayList<Agent> agentBis = new ArrayList<Agent>(agents); 
				for(Agent a : agentBis){
					a.doIt();
					if (a instanceof Chasseur && this.distances[a.getPosX()][a.getPosY()] == 1) {
						finish = true;
					}
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
		
	public int[][] getDistances(){
		return this.distances;
	}
	
	public void printDistance() {
		for (int i=0; i < taille; i++) {
			for (int j=0; j < taille; j++) {
				System.out.print(this.distances[i][j]+" ");
			}
			System.out.println();
		}
	}

	public void calculDistances() {
		
		for (int i=0; i < taille; i++) {
			for (int j=0; j < taille; j++) {
				this.distances[i][j] = -1;
			}
		}
		
		int x_depart = avatar.getPosX();
		int y_depart = avatar.getPosY();
		
		this.distances[x_depart][y_depart] = 0;
		calculDistancesVoisines(x_depart, y_depart);
		//printDistance();
	}

	public void calculDistancesVoisines(int x, int y) {
		int distance_actu = this.distances[x][y];
		if (x == 0) {
			if (environnement.isTorique()) {
				if (!(environnement.getEspace()[this.taille-1][y].getAgent() instanceof Mur) && (this.distances[taille-1][y] == -1 || distance_actu + 1 < this.distances[taille-1][y])) {
					this.distances[this.taille-1][y] = distance_actu + 1;
					calculDistancesVoisines(this.taille-1, y);
				}
			}
		}
		else {
			if (!(environnement.getEspace()[x-1][y].getAgent() instanceof Mur) && (this.distances[x-1][y] == -1 || distance_actu + 1 < this.distances[x-1][y])) {
				this.distances[x-1][y] = distance_actu + 1;
				calculDistancesVoisines(x-1, y);
			}
		}

		if (x == taille-1) {
			if (environnement.isTorique()) {
				if (!(environnement.getEspace()[0][y].getAgent() instanceof Mur) && (this.distances[0][y] == -1 || distance_actu + 1 < this.distances[0][y])) {
					this.distances[0][y] = distance_actu + 1;
					calculDistancesVoisines(0, y);
				}
			}
		}
		else {
			if (!(environnement.getEspace()[x+1][y].getAgent() instanceof Mur) && (this.distances[x+1][y] == -1 || distance_actu + 1 < this.distances[x+1][y])) {
				this.distances[x+1][y] = distance_actu + 1;
				calculDistancesVoisines(x+1, y);
			}
		}

		if (y == 0) {
			if (environnement.isTorique()) {
				if (!(environnement.getEspace()[x][taille-1].getAgent() instanceof Mur) && (this.distances[x][taille-1] == -1 || distance_actu + 1 < this.distances[x][taille-1])) {
					this.distances[x][taille-1] = distance_actu + 1;
					calculDistancesVoisines(x, taille-1);
				}
			}
		}
		else {
			if (!(environnement.getEspace()[x][y-1].getAgent() instanceof Mur) && (this.distances[x][y-1] == -1 || distance_actu + 1 < this.distances[x][y-1])) {
				this.distances[x][y-1] = distance_actu + 1;
				calculDistancesVoisines(x, y-1);
			}
		}

		if (y == taille-1) {
			if (environnement.isTorique()) {
				if (!(environnement.getEspace()[x][0].getAgent() instanceof Mur) && (this.distances[x][0] == -1 || distance_actu + 1 < this.distances[x][0])) {
					this.distances[x][0] = distance_actu + 1;
					calculDistancesVoisines(x, 0);
				}
			}
		}
		else {
			if (!(environnement.getEspace()[x][y+1].getAgent() instanceof Mur) && (this.distances[x][y+1] == -1 || distance_actu + 1 < this.distances[x][y+1])) {
				this.distances[x][y+1] = distance_actu + 1;
				calculDistancesVoisines(x, y+1);
			}
		}
	}
	


}
