package core.sma;

import core.agents.Agent;

public abstract class SMASimulation extends SMA {

	protected int vitesse;
	protected boolean equit;

	public SMASimulation(int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed){
		super(taille, tAgent, torique, seed, visibleGrid);
		
		this.vitesse = vitesse;
		this.equit = equit;
		this.tAgent = tAgent;

	}
	
	@Override
	public void run(){
		
		while (running || !running){
		 this.round();
		 try {
				Thread.sleep(this.vitesse);
			} catch (InterruptedException e) {
				System.out.println("Sleep fail : "+e);
			}
		}
	}

	public abstract void round();

	public int getVitesse() {
		return vitesse;
	}


	public boolean isEquit() {
		return equit;
	}



	public void setEquitable(boolean b) {
		this.equit=true;

	}


	public void setVitesse(int i) {
		this.vitesse=i;

	}

	public abstract int getNbAgent();

	public abstract Agent getNewAgent(int i) ;
}
