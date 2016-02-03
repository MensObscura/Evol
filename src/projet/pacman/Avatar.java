package projet.pacman;

import java.awt.Color;

import core.agents.Agent;
import core.model.Cellule;
import core.model.Direction;
import core.model.Environnement;
import javafx.scene.shape.Circle;

public class Avatar extends Agent {

	private int protege;

	public Avatar(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.protege=0;
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
		Cellule  next = this.getNextCaseBeforeCalcul();
		if(this.protege >0)
		this.protege --;
		if(next != null && next.getAgent() instanceof Arrivee ){
			((SMAPacMan)this.environnement.getSMA()).win();
			return;
		}
		if(next != null && next.getAgent() instanceof Protecteur ){
			this.protege =  ((Protecteur)next.getAgent()).isAboutToDie()?30:20;
			System.out.println(this.protege);
			((Protecteur)next.getAgent()).die();
			this.setProtected(true);
			((SMAPacMan)this.environnement.getSMA()).upScore();
			
			
		}
		if(this.protege == 0){
			this.setProtected(false);
		}
		
		if(this.isNextCaseFree()){
			this.environnement.getEspace()[this.nextX][this.nextY].setAgent(this);
			this.environnement.getEspace()[this.posX][this.posY].removeAgent();
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

	public boolean isProtected() {
		return this.protege >0;
	}
	
	public int roundProtected() {
		return this.protege;
	}

}
