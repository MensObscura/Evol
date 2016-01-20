package model;

import java.awt.Color;
import java.util.Random;

public abstract class Agent {

	protected int posX;
	protected  int posY;
	protected Color color;

	protected  Environnement environnement;



	public Agent(int posX, int posY,Environnement environnement) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.environnement = environnement;
		this.color = this.getRandomColor();
		

	}


	public int getPosX() {
		return posX;
	}


	protected void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	protected void setPosY(int posY) {
		this.posY = posY;
	}




	/**
	 * DO it
	 */
	public abstract void doIt();


	public Color getColor() {
		return this.color;
		
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


}
