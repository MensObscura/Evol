package model;

import java.util.ArrayList;
import java.util.Random;

import agents.Agent;
import agents.Bille;
import agents.Nemo;
import agents.Requin;

public class Environnement {

	private Cellule espace [][];
	private ArrayList<Agent> agents;
	private int taille;
	private int nbAgents;
	private boolean torique;

	private int nbRequins;
	private int nbNemos;

	
	private int reproductionNemo;
	private int reproductionRequin;
	private int faimRequin;


	public Environnement(int taille, int nbBilles, int seed,boolean torique, ArrayList<Agent> agents){

		this.espace= new Cellule [taille][taille];

		this.agents =agents;
		this.taille = taille;
		this.nbAgents =nbBilles;
		this.torique = torique;

		this.nbNemos= 0;
		this.nbRequins = 0;

		this.init(seed);

	}

	public Environnement(int taille, int nbNemos, int nbRequins, int seed,boolean torique, ArrayList<Agent> agents, int reproductionNemo, int reproductionRequin, int faimRequin){

		this.espace= new Cellule [taille][taille];

		this.agents =agents;
		this.taille = taille;
		this.nbAgents =nbNemos+nbRequins;
		this.torique = torique;

		this.nbNemos= nbNemos;
		this.nbRequins = nbRequins;

		this.reproductionNemo = reproductionNemo;
		this.reproductionRequin = reproductionRequin;
		this.faimRequin = faimRequin;
		this.init(seed);

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


		System.out.println("Ajout d'agents");
		for (int i = 0 ; i < this.nbAgents; i++){

			int x =getRandomCoord(-1);
			int y =getRandomCoord(x);

			Agent a;

			if (!this.isWator()) {
				a = new Bille(x,y,this);
			}
			else {
				if (i < this.nbNemos) {
					a = new Nemo(x,y,this,this.reproductionNemo);
				}
				else {
					a = new Requin(x,y,this,this.reproductionRequin,this.faimRequin);
				}
			}

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

			Agent a;

			if (!this.isWator()) {
				a = new Bille(x,y,this);
			}
			else {
				if (i < this.nbNemos) {
					a = new Nemo(x,y,this,this.reproductionNemo);
				}
				else {
					a = new Requin(x,y,this,this.reproductionRequin,this.faimRequin);
				}
			}

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

	public int getNbNemos() {
		return nbNemos;
	}

	public int getNbRequins() {
		return nbRequins;
	}

	public boolean isWator() {
		return nbRequins != 0 && nbNemos != 0;
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
	}

	public void setTorique(boolean b) {
		this.torique =b;

	}

	public void removeAgent(Agent a) {
		this.espace[a.getPosX()][a.getPosY()].setAgent(null);
		this.agents.remove(a);


	}

	public int getReproductionNemo() {
		return reproductionNemo;
	}

	public void setReproductionNemo(int reproductionNemo) {
		this.reproductionNemo = reproductionNemo;
		for (Agent a : this.agents){

			if(a instanceof Nemo ){
				((Nemo)a).setReproduction(reproductionNemo);
			}

		}
	}

	public int getReproductionRequin() {
		return reproductionRequin;
	}

	public void setReproductionRequin(int reproductionRequin) {
		this.reproductionRequin = reproductionRequin;
		for (Agent a : this.agents){

			if(a instanceof Requin ){
				((Requin)a).setReproduction(reproductionRequin);
			}

		}
	}

	public int getFaimRequin() {
		System.out.println(this.faimRequin);
		return faimRequin;
	}

	public void setFaimRequin(int faimRequin) {
		System.out.println("setté");
		this.faimRequin = faimRequin;

		for (Agent a : this.agents){

			if(a instanceof Requin ){
				((Requin)a).setManger(faimRequin);
			}

		}
	}


}
