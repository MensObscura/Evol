package model;

import java.util.Random;

public class Agent {

	private int posX;
	private int posY;

	private int nextX;
	private int nextY;

	private Environnement environnement;
	private Direction dir;


	public Agent(int posX, int posY,Environnement environnement) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.environnement = environnement;
		this.dir = this.getRandomDirection(null);
		

	}


	public int getPosX() {
		return posX;
	}


	private void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	private void setPosY(int posY) {
		this.posY = posY;
	}



	private void setDir(Direction dir) {
		this.dir = dir;
	}

	public Direction getDir() {
		return this.dir;
	}


	/**
	 * 
	 */
	public void doIt(){

		this.calculateNextCase();

		this.environnement.getEspace()[this.posX][this.posY].removeAgent();;

		this.setPosX(this.nextX);
		this.setPosY(this.nextY);

		this.environnement.getEspace()[this.posX][this.posY].setAgent(this);


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
	 * On cherche la prochiane case, et on vérifie si elle est libre, sinon on change de direction et on recherche
	 */
	public void calculateNextCase(){

		switch(this.dir){

		case EST : this.nextX = this.posX; this.nextY = posY + 1; break;
		case OUEST : this.nextX = this.posX; this.nextY = posY -1; break;
		case NORD : this.nextX = this.posX -1 ; this.nextY = posY; break;
		case SUD : this.nextX = this.posX +1 ; this.nextY = posY; break;
		case NORDEST :  this.nextX = this.posX +1 ; this.nextY = posY +1; break;
		case SUDEST : this.nextX = this.posX -1 ; this.nextY = posY +1; break;
		case NORDOUEST : this.nextX = this.posX +1 ; this.nextY = posY -1; break;
		case SUDOUEST : this.nextX = this.posX -1 ; this.nextY = posY -1; break;
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
			this.setDir(this.getRandomDirection(this.dir));
			calculateNextCase();
		}




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
		Random r = new Random();
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

		Direction out =  null;
		if(this.nextCase() != null && !this.nextCase().isEmpty()){

			switch(this.nextCase().getAgent().getDir()){

			case EST : out = Direction.OUEST; break;
			case OUEST : out = Direction.EST; break;
			case NORD :  out = Direction.SUD; break;
			case SUD :  out = Direction.NORD; break;
			case NORDEST :  out = Direction.SUDOUEST; break;
			case SUDEST :  out = Direction.NORDOUEST; break;
			case NORDOUEST :   out = Direction.SUDEST; break;
			case SUDOUEST :  out = Direction.NORDEST; break;
			}
		}

		if(this.nextCase() == null ){


			switch(origine){

			case EST : out = Direction.OUEST; break;
			case OUEST : out = Direction.EST; break;
			case NORD :  out = Direction.SUD; break;
			case SUD :  out = Direction.NORD; break;
			default : 
				if(this.nextY > this.environnement.getEspace().length ){
					switch(origine){
					case NORDEST :  out = Direction.NORDOUEST; break;
					case SUDEST :  out = Direction.SUDOUEST; break;
					}
				}
				if(this.nextX  > this.environnement.getEspace().length ){
					switch(origine){
					case NORDEST :  out = Direction.SUDEST; break;
					case NORDOUEST :   out = Direction.SUDOUEST; break;
					}
				}
				if(this.nextX < 0 ){
					switch(origine){
					case SUDEST :  out = Direction.NORDEST; break;
					case SUDOUEST :  out = Direction.NORDOUEST; break;
					}
				}
				if( this.nextY < 0){
					switch(origine){
					case NORDOUEST :   out = Direction.NORDEST; break;
					case SUDOUEST :  out = Direction.SUDEST; break;
					}
				}
				
				if(this.nextY > this.environnement.getEspace().length && this.nextX  > this.environnement.getEspace().length ){
					 out = Direction.SUDOUEST;
				}
				
				if(this.nextY > this.environnement.getEspace().length && this.nextX < 0 ){
					 out = Direction.NORDOUEST;
				}
				
				if( this.nextX  > this.environnement.getEspace().length &&  this.nextY < 0){
					out = Direction.SUDEST;
				}
				
				if(this.nextX > 0 && this.nextY > 0){
					out = Direction.NORDEST;
				}
			}
		}

		if(origine != null && out != null && origine.equals(out) ){
			return this.getRandomDirection(origine);
		}

		return out;
	}


}
