package core.agents;

import java.awt.Color;
import java.util.Random;

import core.model.Cellule;
import core.model.Direction;
import core.model.Environnement;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Agent {

	protected int posX;
	protected  int posY;
	protected Color color;
	protected Random r = new Random();
	protected  Environnement environnement;
	protected boolean checkTour;
	protected int nextX;
	protected int nextY;

	protected Direction dir;

	protected Shape shape;


	public Agent(int posX, int posY,Environnement environnement) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.environnement = environnement;
		this.color = this.getRandomColor();
		this.shape = new Circle(this.environnement.getSMA().gettAgent(), getRandomFxColor());
		this.shape.relocate(posX * 10, posY *10);
		this.dir = this.getRandomDirection(null);
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
		this.shape.relocate(posX *10, posY *10);
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
		this.shape.relocate(posX *10, posY *10);
	}




	public Color getColor() {
		return this.color;

	}

	public Shape getRepresentation(){

		return this.shape;

	}

	private Color getRandomColor() {


		Random r = new Random();
		int color = r.nextInt(10);
		switch(color){
		case 0 : return Color.BLACK;
		case 1 : return Color.RED;
		case 2 : return Color.BLUE;
		case 3 : return Color.CYAN;
		case 4 : return Color.ORANGE;
		case 5 : return Color.PINK;
		case 6 : return Color.YELLOW;
		case 7 : return Color.MAGENTA;
		case 8 : return Color.GREEN;
		case 9 : return Color.LIGHT_GRAY;

		default:
			return Color.black;
		}


	}
	

	private Paint getRandomFxColor() {
		Random r = new Random();
		int color = r.nextInt(10);
		switch(color){
		case 0 : return javafx.scene.paint.Color.BLACK;
		case 1 : return javafx.scene.paint.Color.RED;
		case 2 : return javafx.scene.paint.Color.BLUE;
		case 3 : return javafx.scene.paint.Color.CYAN;
		case 4 : return javafx.scene.paint.Color.ORANGE;
		case 5 : return javafx.scene.paint.Color.PINK;
		case 6 : return javafx.scene.paint.Color.YELLOW;
		case 7 : return javafx.scene.paint.Color.MAGENTA;
		case 8 : return javafx.scene.paint.Color.GREEN;
		case 9 : return javafx.scene.paint.Color.GRAY;

		default:
			return javafx.scene.paint.Color.BLACK;
		}
	}

	public void doIt(){
		this.calculateNextCase(0);
		this.environnement.getEspace()[this.posX][this.posY].removeAgent();;

		this.setPosX(this.nextX);
		this.setPosY(this.nextY);

		this.environnement.getEspace()[this.posX][this.posY].setAgent(this);


	}

	/**
	 * On cherche la prochiane case, et on vérifie si elle est libre, sinon on change de direction et on recherche
	 */
	public void calculateNextCase(int tour){
		tour ++;
		switch(this.dir){

		case EST : this.nextX = this.posX; this.nextY = this.posY + 1; break;
		case OUEST : this.nextX = this.posX; this.nextY = this.posY -1; break;
		case NORD : this.nextX = this.posX -1 ; this.nextY = this.posY; break;
		case SUD : this.nextX = this.posX +1 ; this.nextY = this.posY; break;
		case NORDEST :  this.nextX = this.posX +1 ; this.nextY = this.posY +1; break;
		case SUDEST : this.nextX = this.posX -1 ; this.nextY = this.posY +1; break;
		case NORDOUEST : this.nextX = this.posX +1 ; this.nextY = this.posY -1; break;
		case SUDOUEST : this.nextX = this.posX -1 ; this.nextY = this.posY -1; break;
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

		if(!isNextCaseFree()){
			if(tour > 25  && checkTour == true){	
				this.nextX = this.posX ; 
				this.nextY = this.posY ;				
			}else if(tour > 25  && checkTour == false ){
				this.setDir(this.getRandomDirection(this.dir));
				calculateNextCase(tour);
			}else  if(tour > 2  && tour < 25 ){
				this.setDir(this.getRandomDirection(this.dir));
				calculateNextCase(tour);
			}else{
				this.setDir(this.getDirectionWithOpponentDirection(this.dir));
				calculateNextCase(tour);
			}
			
		}
		

	}

	protected void setDir(Direction dir) {
		this.dir = dir;
	}

	public Direction getDir() {
		return this.dir;
	}



	/**
	 * On regarde l'état de la case suivante
	 * @return
	 */
	public boolean isNextCaseFree(){

		if(this.nextY < this.environnement.getEspace().length && this.nextX  < this.environnement.getEspace().length && this.nextX >= 0 && this.nextY >= 0){

			return this.environnement.getEspace()[this.nextX][this.nextY].isEmpty();

		}
		return false;


	}


	/**
	 * prochiane case selon le dernier calcul
	 */
	public Cellule nextCase(){
		if(this.nextY < this.environnement.getEspace().length && this.nextX  < this.environnement.getEspace().length && this.nextX >= 0 && this.nextY >= 0){

			return this.environnement.getEspace()[this.nextX][this.nextY];

		}
		return null;
	}

	/** choisi un nouvelle direction en fonction de l'ancienne
	 * 
	 * @return une direction
	 */
	public Direction getRandomDirection(Direction origine){

		int rand = r.nextInt(8);
		Direction out =  null;
		switch(rand){
		case 0 : out = Direction.EST; break;
		case 1 : out = Direction.OUEST; break;
		case 2 : out = Direction.NORD; break;
		case 3 : out = Direction.SUD; break;
		case 4 : out = Direction.NORDEST; break;
		case 5 : out = Direction.SUDEST; break;
		case 6 : out = Direction.NORDOUEST; break;
		case 7 : out = Direction.SUDOUEST; break;
		default :
		}
		if(origine != null && origine.equals(out)){
			return this.getRandomDirection(origine);
		}

		return out;
	}


	public Direction getDirectionWithOpponentDirection(Direction origine){

		if(this.nextCase() != null && !this.nextCase().isEmpty()){
			switch((this.nextCase().getAgent()).getDir()){

			case EST :  return  Direction.OUEST; 
			case OUEST :  return  Direction.EST; 
			case NORD :   return  Direction.SUD; 
			case SUD :   return Direction.NORD; 
			case NORDEST :   return  Direction.SUDOUEST; 
			case SUDEST :   return  Direction.NORDOUEST; 
			case NORDOUEST :   return  Direction.SUDEST;
			case SUDOUEST :   return  Direction.NORDEST;
			}
		}

		if(this.nextCase() == null ){


			switch(origine){

			case EST :  return Direction.OUEST; 
			case OUEST : return Direction.EST;
			case NORD :   return  Direction.SUD;
			case SUD :   return  Direction.NORD; 
			default :
				if(this.nextY > (this.environnement.getEspace().length -1) && this.nextX  > (this.environnement.getEspace().length -1 )){
					return Direction.SUDOUEST;
				}

				if(this.nextY > (this.environnement.getEspace().length -1) && this.nextX < 0 ){
					return  Direction.NORDOUEST;
				}

				if( this.nextX  > (this.environnement.getEspace().length -1) &&  this.nextY < 0){
					return Direction.SUDEST;
				}

				if(this.nextX < 0 && this.nextY < 0){
					return Direction.NORDEST;
				}


				if(this.nextY > this.environnement.getEspace()[0].length -1 ){
					switch(origine){
					case NORDEST :  return Direction.NORDOUEST; 
					case SUDEST :  return Direction.SUDOUEST;
					}
				}
				if(this.nextX  > this.environnement.getEspace().length -1 ){
					switch(origine){
					case NORDEST :  return Direction.SUDEST; 
					case NORDOUEST :  return Direction.SUDOUEST;
					}
				}
				if(this.nextX < 0 ){
					switch(origine){
					case SUDOUEST :  return Direction.NORDOUEST;  
					case SUDEST :  return Direction.NORDEST;   
					}
				}
				if( this.nextY < 0){
					switch(origine){
					case SUDOUEST :  return Direction.SUDEST; 
					case NORDOUEST :  return Direction.NORDEST; 
					}
				}


			}
		}

		return getRandomDirection(origine);
	}


}
