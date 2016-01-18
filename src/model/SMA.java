package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

public class SMA  extends Observable{

	private Environnement environnement;
	private ArrayList<Agent> agents;
	private int tAgent; 
	private int vitesse;
	private boolean visibleGrid;
	private boolean equit;
	private int seed;
	private boolean running = true;//tmp false 
	private int taille ;

	public SMA(int nbBilles, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		this.agents = new ArrayList<Agent>();
		this.taille = taille;
		this.launch(nbBilles, seed, torique, visibleGrid, equit);///tmp
	
		this.tAgent = tAgent;
		this.vitesse = vitesse;
		this.visibleGrid = visibleGrid;
		this.equit = equit;
		this.seed = seed;
	}


	public void run(){

		System.out.println("Début du run");
		while (running){
			if(this.isEquit())
				Collections.shuffle(this.agents);

			for(Agent a : agents){
				a.doIt();
			}
			try {
				Thread.sleep(this.vitesse);
			} catch (InterruptedException e) {
				System.out.println("Sleep fail : "+e);
			}
			this.setChanged();
			this.notifyObservers();

		}
		System.out.println("Fin du run");

	}


	public Environnement getEnvironnement() {
		return environnement;
	}


	public ArrayList<Agent> getAgents() {
		return agents;
	}


	public int gettAgent() {
		return tAgent;
	}


	public int getVitesse() {
		return vitesse;
	}





	public boolean isVisibleGrid() {
		return visibleGrid;
	}


	public boolean isEquit() {
		return equit;
	}


	public int getSeed() {
		return seed;
	}
	
	public void changeRunning(){
		this.running = !this.running;
	}

	public void clearAgent(){

		this.environnement.getAgents().clear();
		this.setChanged();
		this.notifyObservers();
	}

	public void launch( int nbBilles, int seed, boolean torique, boolean grille,boolean equit){
		
		this.equit = equit;
		this.visibleGrid = grille;
		this.environnement = new Environnement(this.taille,nbBilles, seed, torique, agents);
		
	}

}
