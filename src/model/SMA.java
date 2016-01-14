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

	public SMA(int nbBilles, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		this.agents = new ArrayList<Agent>();
		this.environnement = new Environnement(taille,nbBilles, seed, torique, agents);

		this.tAgent = tAgent;
		this.vitesse = vitesse;
		this.visibleGrid = visibleGrid;
		this.equit = equit;
		this.seed = seed;
	}


	public void run(){

		System.out.println("Début du run");
		while (true){
			if(this.isEquit())
				Collections.shuffle(this.agents);

			System.out.println("On fait parler les agent");
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



}
