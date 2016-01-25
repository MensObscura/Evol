package sma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Observable;

import agents.Agent;
import agents.Nemo;
import agents.Requin;
import model.Environnement;

public class SMA extends Observable{

	protected Environnement environnement;
	protected ArrayList<Agent> agents;
	protected int tAgent; 
	protected int vitesse;
	protected boolean visibleGrid;
	protected boolean equit;
	protected int seed;
	protected boolean running = false;//tmp false 
	protected int taille ;

	public SMA(int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		this.agents = new ArrayList<Agent>();
		this.taille = taille;
		this.vitesse = vitesse;
		this.visibleGrid = visibleGrid;
		this.equit = equit;
		this.seed = seed;

		this.tAgent = tAgent;

	}
	
	public void run(){

		System.out.println("Début du run");
		int nemo = 0;
		int requin = 0;
		while (running || !running){
			nemo = 0;
			requin = 0;
			if(running){
				if(this.isEquit())
					Collections.shuffle(this.agents);

				try{
					ArrayList<Agent> agentBis = new ArrayList<Agent>(agents); 
					for(Agent a : agentBis){
						if(a instanceof Nemo){
							nemo ++;
						}
						
						if(a instanceof Requin){
							requin ++;
						}
						a.doIt();
					}
				}catch(ConcurrentModificationException e){
					System.out.println("Clique doucement ducon : "+e);
				}
				
				this.setChanged();
				this.notifyObservers();
				System.out.println("Nemos :" + nemo );
				System.out.println("Requins : "+ requin);
			}
			try {
				Thread.sleep(this.vitesse);
			} catch (InterruptedException e) {
				System.out.println("Sleep fail : "+e);
			}
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
		this.environnement.clearSpace();
		this.setChanged();
		this.notifyObservers();
	}

	public void setTorique(boolean b) {
		this.environnement.setTorique(b);

	}


	public void setVisibleGrid(boolean b) {
		this.visibleGrid = b;

	}


	public void setEquitable(boolean b) {
		this.equit=true;

	}


	public void setVitesse(int i) {
		this.vitesse=i;

	}

}
