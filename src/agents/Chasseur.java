package agents;

import model.Cellule;
import model.Environnement;

public class Chasseur extends Agent {

	public Chasseur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
	}
	
	public void doIt(){
		int[] coord = this.chooseBestWay();
		
		this.environnement.getEspace()[this.posX][this.posY].removeAgent();;

		this.setPosX(coord[0]);
		this.setPosY(coord[1]);

		this.environnement.getEspace()[this.posX][this.posY].setAgent(this);
	}
	
	private int[] chooseBestWay() {
		int[] coord = new int[2];
		Cellule[][] espace = this.environnement.getEspace();
		
		int dmin = espace[this.posX][this.posY].getDistance();
		
		if (this.posX == 0) {
			if (this.environnement.isTorique() && dmin > espace[espace.length-1][this.posY].getDistance()) {
				dmin = espace[espace.length-1][this.posY].getDistance();
				coord = new int[]{espace.length-1, this.posY};
			}
		}
		else {
			if (dmin > espace[this.posX-1][this.posY].getDistance()) {
				dmin = espace[this.posX-1][this.posY].getDistance();
				coord = new int[]{this.posX-1, this.posY};
			}
		}
		
		if (this.posX == espace.length-1) {
			if (this.environnement.isTorique() && dmin > espace[0][this.posY].getDistance()) {
				dmin = espace[0][this.posY].getDistance();
				coord = new int[]{0, this.posY};
			}
		}
		else {
			if (dmin > espace[this.posX+1][this.posY].getDistance()) {
				dmin = espace[this.posX+1][this.posY].getDistance();
				coord = new int[]{this.posX+1, this.posY};
			}
		}
		
		if (this.posY == 0) {
			if (this.environnement.isTorique() && dmin > espace[this.posX][espace.length-1].getDistance()) {
				dmin = espace[this.posX][espace.length-1].getDistance();
				coord = new int[]{this.posX, espace.length-1};
			}
		}
		else {
			if (dmin > espace[this.posX][espace.length-1].getDistance()) {
				dmin = espace[this.posX][espace.length-1].getDistance();
				coord = new int[]{this.posX, espace.length-1};
			}
		}
		
		if (this.posY == espace.length-1) {
			if (this.environnement.isTorique() && dmin > espace[this.posX][0].getDistance()) {
				dmin = espace[this.posX][0].getDistance();
				coord = new int[]{this.posX, 0};
			}
		}
		else {
			if (dmin > espace[this.posX][0].getDistance()) {
				dmin = espace[this.posX][0].getDistance();
				coord = new int[]{this.posX, 0};
			}
		}

		return coord;
	}
	
}
