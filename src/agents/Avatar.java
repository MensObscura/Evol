package agents;

import java.awt.Color;

import javafx.scene.shape.Circle;
import model.Direction;
import model.Environnement;

public class Avatar extends Agent {

	public Avatar(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.GREEN;
		this.shape = new Circle(this.environnement.getSMA().gettAgent()+1, javafx.scene.paint.Color.LIMEGREEN);
		this.shape.relocate(posX*10 , posY*10 );
		this.setDir(Direction.NORD);
		
	}
	
	public void changeDirection(Direction dir) {
		switch (dir) {
		case SUD: if (canMoveontheBottom()) {this.setDir(dir);} break;
		case NORD: if (canMoveontheTop()) {this.setDir(dir);} break;
		case OUEST: if (canMoveontheRight()) {this.setDir(dir);} break;
		case EST: if (canMoveontheLeft()) {this.setDir(dir);} break;
			default: break;
		}
	}
	
	public void doIt(){
		this.calculateNextCase(0); 
		
		this.setPosX(this.nextX);
		this.setPosY(this.nextY);
		
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
