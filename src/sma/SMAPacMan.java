package sma;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import agents.Agent;
import agents.Arrivee;
import agents.Avatar;
import agents.Chasseur;
import agents.Mur;
import agents.Protecteur;
import model.Cellule;
import model.Environnement;

public class SMAPacMan extends SMA{

	private int murs;
	private int chasseurs;
	private Avatar avatar;
	private Arrivee arrivee;
	private boolean finish;
	private int vitesse;
	private int score;
	private String state;

	private int distances [][];
	private int protege;
	private boolean protecteur;

	public SMAPacMan(int nbChasseurs, int nbMurs, int taille, int tAgent, boolean torique, boolean grille, int seed) {
		super(taille, tAgent, torique, seed, grille);
		this.launch(nbChasseurs, nbMurs, torique, seed, grille);
	}

	public void launch(int nbChasseurs, int nbMurs, boolean torique, int seed, boolean visibleGrid){
		this.finish = false;
		this.chasseurs = nbChasseurs;
		this.murs = nbMurs;
		this.visibleGrid = visibleGrid;
		this.vitesse = 550;
		this.protege= 0;
		this.protecteur=false;
		this.state="";
		// Init tableau des distances
		this.distances = new int[taille][taille];


		this.environnement = new Environnement(this, this.taille, torique, agents);

		this.environnement.init(seed);

		// On place le joueur :
		int x =this.environnement.getRandomCoord(-1);
		int y =this.environnement.getRandomCoord(x);
		avatar = new Avatar(x,y,this.environnement);
		this.environnement.addAgent(avatar);
		x =this.environnement.getRandomCoord(-1);
		y =this.environnement.getRandomCoord(x);
		arrivee = new Arrivee(x,y,this.environnement);
		this.environnement.addAgent(arrivee);

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
				Cellule  next = avatar.getNextCaseBeforeCalcul();
				if(next != null && next.getAgent() instanceof Arrivee ){
					this.finish = true;
					this.state +=" Win";
				}
				if(next != null && next.getAgent() instanceof Protecteur ){
					((Protecteur)next.getAgent()).die();
					this.avatar.setProtected(true);
					this.score++;
					this.state = "Score : "+this.score;
					this.protege = 20;
				}
				if(this.protege == 0){
					this.avatar.setProtected(false);
				}
				if(this.protecteur == false){
					this.protecteur=true;
					int x =this.environnement.getRandomCoord(-1);
					int y =this.environnement.getRandomCoord(x);
					Protecteur protecteur = new Protecteur(x,y,this.environnement);
					this.environnement.addAgent(protecteur);
				}
				this.avatar.doIt();
				if(!finish){
					this.calculDistances();

					ArrayList<Agent> agentBis = new ArrayList<Agent>(agents); 
					for(Agent a : agentBis){
						if(!( a instanceof Avatar) && !finish)
							a.doIt();
						if (a instanceof Chasseur && this.distances[a.getPosX()][a.getPosY()] <= 1) {
							this.state +=" Loose";
							finish = true;
						}

					}



				}
			}catch(ConcurrentModificationException e){

			}

			this.setChanged();
			this.notifyObservers();
			tour++;
			this.protege --;
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

	public boolean isProtege(){
		return this.protege>0;
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

	public  int getVitesse(){
		return this.vitesse;
	}

	public boolean addVitesse(int i) {
		if(this.vitesse > 100){
			this.vitesse = this.vitesse - i;
			System.out.println("speed");
			return true;
		}
		return false;
	}

	public boolean slowVitesse(int i) {
		if(this.vitesse < 1000){
			System.out.println("slow");
			this.vitesse = this.vitesse + i;
			return true;
		}
		return false;

	}

	public void notifyDeath() {
		this.protecteur=false;		
	}
	
	public String getState(){
		return this.state;
	}
	




}
