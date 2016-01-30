package agents;

import java.awt.Color;

import model.Direction;
import model.Environnement;
import model.PacManEnvironnement;

public class Avatar extends Agent {

	public Avatar(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.GREEN;
		this.setDirection(null);
	}
	
	public void setDirection(Direction dir) {
		switch (dir) {
		case SUD: if (canMoveontheBottom()) {this.setDirection(dir);} break;
		case NORD: if (canMoveontheTop()) {this.setDirection(dir);} break;
		case OUEST: if (canMoveontheRight()) {this.setDirection(dir);} break;
		case EST: if (canMoveontheLeft()) {this.setDirection(dir);} break;
			default: break;
		}
	}
	
	
	public void doIt(){
		if (this.getDir() != null)
			this.calculateNextCase(0);
		
		// Maj de l'avatar dans l'environnement
		((PacManEnvironnement)this.environnement).avatar.setPosX(posX);
		((PacManEnvironnement)this.environnement).avatar.setPosY(posY);
		
		// Recalcul des distances
		((PacManEnvironnement)this.environnement).calculDistances();
		if (isFinish()) {
			((PacManEnvironnement)this.environnement).setToFinish();
		}
	}
	
	private boolean isFinish() {
		//TODO
		return false;
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
