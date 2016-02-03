package core.sma;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import core.agents.Agent;
import core.model.Environnement;

public abstract class SMA extends Observable{

	protected Environnement environnement;
	protected List<Agent> agents;
	protected int tAgent; 
	protected boolean running = false;
	protected int taille ;
	protected int tour;
	protected int seed;
	protected boolean visibleGrid;

	public SMA(int taille, int tAgent, boolean torique, int seed, boolean visibleGrid){
		this.agents = new ArrayList<Agent>();
		this.taille = taille;
		this.tour = 1;
		this.tAgent = tAgent;
		this.seed = seed;
		this.visibleGrid = visibleGrid;
	}
	
	public abstract void run();

	public abstract void round();

	public Environnement getEnvironnement() {
		return environnement;
	}


	public List<Agent> getAgents() {
		return agents;
	}


	public int gettAgent() {
		return tAgent;
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
	
	public int getSeed() {
		return seed;
	}


	public boolean isVisibleGrid() {
		return visibleGrid;
	}
	public abstract int getNbAgent();

	public abstract Agent getNewAgent(int i) ;

	
	public void setVisibleGrid(boolean b) {
		this.visibleGrid = b;

	}
	
	public boolean isRunning(){
		return running;
		
	}

	public void stopRunning() {
		this.running=false;
		
	}


}
