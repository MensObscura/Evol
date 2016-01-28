package agents;

import model.Environnement;

public class Avatar extends Agent {

	public Avatar(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
	}
	
	public void doIt(){
		//TODO Ajouter un listener de touches
	}
	
	public boolean canMoveontheLeft() {
		if (this.environnement.isTorique()) {
			if (this.posX == 0) {
				return !(this.environnement.getEspace()[this.environnement.getTaille()-1][this.posY].getAgent()
						instanceof Mur);
			}
		}
		else {
			if (this.posX == 0) {
				return false;
			}
		}
		
		return !(this.environnement.getEspace()[this.posX-1][this.posY].getAgent()
				instanceof Mur);
	}
	
	public boolean canMoveontheRight() {
		if (this.environnement.isTorique()) {
			if (this.posX == this.environnement.getTaille()-1) {
				return !(this.environnement.getEspace()[0][this.posY].getAgent()
						instanceof Mur);
			}
		}
		else {
			if (this.posX == this.environnement.getTaille()-1) {
				return false;
			}
		}
		
		return !(this.environnement.getEspace()[this.posX+1][this.posY].getAgent()
				instanceof Mur);
	}
	
	public boolean canMoveontheTop() {
		if (this.environnement.isTorique()) {
			if (this.posY == 0) {
				return !(this.environnement.getEspace()[this.posX][this.environnement.getTaille()-1].getAgent()
						instanceof Mur);
			}
		}
		else {
			if (this.posY == 0) {
				return false;
			}
		}
		
		return !(this.environnement.getEspace()[this.posX][this.posY-1].getAgent()
				instanceof Mur);
	}
	
	public boolean canMoveontheBottom() {
		if (this.environnement.isTorique()) {
			if (this.posY == this.environnement.getTaille()-1) {
				return !(this.environnement.getEspace()[this.posX][0].getAgent()
						instanceof Mur);
			}
		}
		else {
			if (this.posY == this.environnement.getTaille()-1) {
				return false;
			}
		}
		
		return !(this.environnement.getEspace()[this.posX][this.posY+1].getAgent()
				instanceof Mur);
	}

}
