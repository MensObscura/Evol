package model;

import java.util.ArrayList;
import java.util.Random;

import agents.Agent;
import agents.Bille;
import agents.Nemo;
import agents.Requin;
import sma.SMA;

public class Environnement {

	private Cellule espace [][];
	private ArrayList<Agent> agents;
	private int taille;
	private int nbAgents;
	private boolean torique;
	private SMA sma;
	




	public Environnement(SMA sma,int taille, boolean torique, ArrayList<Agent> agents){

		this.espace= new Cellule [taille][taille];
		this.sma = sma;
		this.agents =agents;
		this.taille = taille;
		this.nbAgents =sma.getNbAgent();
		this.torique = torique;

	}

	//ici on determine l'init en fonction du seed
	public void init(int seed){

		for(int i = 0 ; i < this.espace.length; i++){
			for(int j = 0; j < this.espace[i].length; j++){
				this.espace[i][j] = new Cellule();
			}
		}
		if( this.nbAgents > 0)
			switch(seed){
			case 0 : randomPut(); break;
			case 1 : binaryPut(); break;
			default :randomPut();

			}

	}


	public Cellule[][] getEspace(){

		return this.espace;

	}

	/**
	 * on set les Agent au hasard
	 */
	public void  randomPut(){

		this.agents.clear();
		for (int i = 0 ; i < this.nbAgents; i++){

			int x =getRandomCoord(-1);
			int y =getRandomCoord(x);
			Agent a = this.sma.getNewAgent(i);
			a.setPosX(x);
			a.setPosY(y);

		

			this.agents.add(a);
			this.espace[x][y].setAgent(a);


		}
	}


	/**
	 * on set les Agent au hasard
	 */
	public void  binaryPut(){

		int x = 0;
		int y = 0;


		for (int i = 0 ; i < this.nbAgents; i++){

		
			Agent a = this.sma.getNewAgent(i);
			a.setPosX(x);
			a.setPosY(y);

			this.agents.add(a);
			this.espace[x][y].setAgent(a);

			x =( (x+2 ) % (this.espace.length));
			if( x == 0){
				y ++;
			}

		}
	}

	public int getRandomCoord(int x){



		Random r = new Random();
		int out =r.nextInt(this.taille);
		if(x == -1)
			return out;

		// si la case n'est pas déjà prise on return sinon on cherche un nouveau y
		if(this.espace[x][out].isEmpty()){
			return out;
		}

		return getRandomCoord(x);

	}

	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public int getTaille() {
		return taille;
	}


	public int getNbAgents() {
		return nbAgents;
	}

	public boolean isTorique() {
		return torique;
	}

	public void clearSpace() {
		this.agents.clear();
		 this.nbAgents =0;
		this.init(0);


	}

	public void addAgent(Agent a){
		this.agents.add(a);
		this.espace[a.getPosX()][a.getPosY()].setAgent(a);
	}

	public void setTorique(boolean b) {
		this.torique =b;

	}

	public void removeAgent(Agent a) {
		
		this.agents.remove(a);
		this.espace[a.getPosX()][a.getPosY()].setAgent(null);


	}




}
