package agents;

import java.awt.Color;

import javafx.scene.shape.Circle;
import model.Direction;
import model.Environnement;
import model.PacManEnvironnement;

public class Avatar extends Agent {

	public Avatar(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.GREEN;
		this.shape = new Circle(this.environnement.getSMA().gettAgent()+1, javafx.scene.paint.Color.LIMEGREEN);
		this.shape.relocate(posX*10 , posY*10 );
		
		// ça plante ici
		//this.setDirection(null);
		
		
		
	}
	
/*	public void setDirection(Direction dir) {*/
		/** lool dat recusivité !! t'appelle setDirection(dir) dans setDirection(dir) ! je créé changeDirectionTemporairement**/
	/*	switch (dir) {
		case SUD: if (canMoveontheBottom()) {this.setDirection(dir);} break;
		case NORD: if (canMoveontheTop()) {this.setDirection(dir);} break;
		case OUEST: if (canMoveontheRight()) {this.setDirection(dir);} break;
		case EST: if (canMoveontheLeft()) {this.setDirection(dir);} break;
			default: break;
		}
	}*/
	
	
	public void changeDirection(Direction dir) {
		/** lool dat recusivité !! t'appelle setDirection(dir) dans setDirection(dir) ! je créé ChangeDirectionTemporairement**/
		switch (dir) {
		case SUD: if (canMoveontheBottom()) {this.setDir(dir);} break;
		case NORD: if (canMoveontheTop()) {this.setDir(dir);} break;
		case OUEST: if (canMoveontheRight()) {this.setDir(dir);} break;
		case EST: if (canMoveontheLeft()) {this.setDir(dir);} break;
			default: break;
		}
	}
	
	public void doIt(){
		if (this.getDir() != null)
			this.calculateNextCase(0);
		
		/** Maj de l'avatar dans l'environnement // l'avatar ne doit pas être dans l'environnement mais dans le sma sinon ça plante MAis je rend ça possible le temps de tes reglages**/
	//	((PacManEnvironnement)this.environnement).avatar.setPosX(posX);
		//((PacManEnvironnement)this.environnement).avatar.setPosY(posY);
		/** ce que tu fait est bizzare car l'avatar dans l'environement est le meme que partout puisque c'est un pointeur...*/
		
		this.setPosX(this.nextX);
		this.setPosY(this.nextY);
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
