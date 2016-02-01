package agents;

import java.awt.Color;

import javafx.scene.shape.Circle;
import model.Cellule;
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
		case SUD: this.setDir(dir); break;
		case NORD: this.setDir(dir); break;
		case OUEST:this.setDir(dir); break;
		case EST: this.setDir(dir); break;
		default: break;
		}
	}

	public void doIt(){
		this.getNextCaseBeforeCalcul();
		if(this.isNextCaseFree()){

			this.setPosX(this.nextX);
			this.setPosY(this.nextY);
		}

	}

	public Cellule getNextCaseBeforeCalcul(){
		switch(this.dir){

		case EST : this.nextX = this.posX; this.nextY = this.posY + 1; break;
		case OUEST : this.nextX = this.posX; this.nextY = this.posY -1; break;
		case NORD : this.nextX = this.posX -1 ; this.nextY = this.posY; break;
		case SUD : this.nextX = this.posX +1 ; this.nextY = this.posY; break;
		}

		if(this.environnement.isTorique()){

			if(this.nextY < this.environnement.getEspace().length || this.nextX < this.environnement.getEspace().length ){
				this.nextY = this.nextY % this.environnement.getEspace().length;
				this.nextX = this.nextX % this.environnement.getEspace().length;
			}


			if(this.nextY < 0){
				this.nextY = this.nextY + this.environnement.getEspace().length -1;

			}
			if(this.nextX < 0 ){
				this.nextX = this.nextX + this.environnement.getEspace().length -1;
			}
		}
		if(this.nextY < this.environnement.getEspace().length && this.nextX  < this.environnement.getEspace().length && this.nextX >= 0 && this.nextY >= 0){

			return this.environnement.getEspace()[this.nextX][this.nextY];

		}
		return null;
	}

	public void setProtected(boolean protege){
		if(protege){
			this.shape = new Circle(this.environnement.getSMA().gettAgent()+1, javafx.scene.paint.Color.YELLOW);
			this.shape.relocate(this.posX*10 , this.posY*10 );
		}else{
			this.shape = new Circle(this.environnement.getSMA().gettAgent()+1, javafx.scene.paint.Color.LIMEGREEN);
			this.shape.relocate(posX*10 , posY*10 );
		}
	}

}
