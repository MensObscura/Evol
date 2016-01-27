package sma;

import agents.Agent;
import agents.Bille;
import agents.Nemo;
import agents.Requin;
import model.Environnement;

public class SMAWator extends SMA {

	private int nbRequins;
	private int nbNemos;


	private int reproductionNemo;
	private int reproductionRequin;
	private int faimRequin;

	public SMAWator(int nbNemos, int nbRequins, int taille, int tAgent, int vitesse, boolean torique, boolean visibleGrid, boolean equit, int seed, int reproNemo, int reproRequin, int faimRequin){
		super(taille, tAgent, vitesse, torique, visibleGrid, equit, seed);


		this.launch(nbNemos, nbRequins, seed,vitesse, torique, visibleGrid, equit,reproNemo, reproRequin, faimRequin);

	}

	public void launch(int nbNemos, int nbRequins, int seed, int vitesse, boolean torique, boolean grille,boolean equit,int reproNemo,int reproRequin,int faimRequin){
		this.nbNemos= nbNemos;
		this.nbRequins = nbRequins;

		this.reproductionNemo = reproNemo;
		this.reproductionRequin = reproRequin;
		this.faimRequin = faimRequin;
		this.equit = equit;
		this.visibleGrid = grille;
		this.vitesse = vitesse;

		this.environnement = new Environnement(this, this.taille, torique, agents);
		this.environnement.init(seed);

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
		return faimRequin;
	}

	public void setFaimRequin(int faimRequin) {
		this.faimRequin = faimRequin;

		for (Agent a : this.agents){

			if(a instanceof Requin ){
				((Requin)a).setManger(faimRequin);
			}

		}
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

	@Override
	public int getNbAgent() {

		return nbRequins + nbNemos;
	}

	public Agent getNewAgent(int i){

		if (i < this.nbNemos) {
			return new Nemo(0,0,this.environnement,this.reproductionNemo);
		}
		else {
			return new Requin(0,0,this.environnement,this.reproductionRequin,this.faimRequin);
		}
	}

}
