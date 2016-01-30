package model;

import java.util.ArrayList;

import agents.Agent;
import agents.Avatar;
import agents.Mur;
import sma.SMA;

public class PacManEnvironnement extends Environnement {
	
	private int distances [][];
	private Avatar avatar;

	public PacManEnvironnement(SMA sma, int taille, boolean torique, ArrayList<Agent> chasseurs) {
		// Init environnement, place les chasseurs et les murs :
		super(sma, taille, torique, chasseurs);
		
		// On place le joueur :
		int x =getRandomCoord(-1);
		int y =getRandomCoord(x);
		
		avatar = new Avatar(x,y,this);
		this.agents.add(avatar);
		this.espace[x][y].setAgent(avatar);
		
		// Init tableau des distances
		this.distances = new int[taille][taille];
		
		for (int i=0; i < taille; i++) {
			for (int j=0; j < taille; j++) {
				this.distances[i][j] = -1;
			}
		}
		
		
	}
	
	public int[][] getDistances(){
		return this.distances;
	}
	
	public void calculDistances() {
		int x_depart = avatar.getPosX();
		int y_depart = avatar.getPosY();
		
		this.distances[x_depart][y_depart] = 0;
		calculDistancesVoisines(x_depart, y_depart);
		
	}
	
	public void calculDistancesVoisines(int x, int y) {
		int distance_actu = this.distances[x][y];
		if (x == 0) {
			if (this.isTorique()) {
				if (!(this.espace[this.getTaille()-1][y].getAgent() instanceof Mur) && this.distances[this.getTaille()-1][y] == -1) {
					this.distances[this.getTaille()-1][y] = distance_actu + 1;
					calculDistancesVoisines(this.getTaille()-1, y);
				}
			}
		}
		else {
			if (!(this.espace[x-1][y].getAgent() instanceof Mur) && this.distances[x-1][y] == -1) {
				this.distances[x-1][y] = distance_actu + 1;
				calculDistancesVoisines(x-1, y);
			}
		}
		
		if (x == this.getTaille()-1) {
			if (this.isTorique()) {
				if (!(this.espace[0][y].getAgent() instanceof Mur) && this.distances[0][y] == -1) {
					this.distances[0][y] = distance_actu + 1;
					calculDistancesVoisines(0, y);
				}
			}
		}
		else {
			if (!(this.espace[x+1][y].getAgent() instanceof Mur) && this.distances[x+1][y] == -1) {
				this.distances[x+1][y] = distance_actu + 1;
				calculDistancesVoisines(x+1, y);
			}
		}
		
		if (y == 0) {
			if (this.isTorique()) {
				if (!(this.espace[x][this.getTaille()-1].getAgent() instanceof Mur) && this.distances[x][this.getTaille()-1] == -1) {
					this.distances[x][this.getTaille()-1] = distance_actu + 1;
					calculDistancesVoisines(x, this.getTaille()-1);
				}
			}
		}
		else {
			if (!(this.espace[x][y-1].getAgent() instanceof Mur) && this.distances[x][y-1] == -1) {
				this.distances[x][y-1] = distance_actu + 1;
				calculDistancesVoisines(x, y-1);
			}
		}
		
		if (y == this.getTaille()-1) {
			if (this.isTorique()) {
				if (!(this.espace[x][0].getAgent() instanceof Mur) && this.distances[x][0] == -1) {
					this.distances[x][0] = distance_actu + 1;
					calculDistancesVoisines(x, 0);
				}
			}
		}
		else {
			if (!(this.espace[x][y+1].getAgent() instanceof Mur) && this.distances[x][y+1] == -1) {
				this.distances[x][y+1] = distance_actu + 1;
				calculDistancesVoisines(x, y+1);
			}
		}
	}

}
