package model;

public class Agent {
	
	private int posX;
	private int posY;
	private Cellule espace[][];
	
	
	public Agent(int posX, int posY, Cellule[][] espace) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.espace = espace;
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	public void doIt(){
		
	}
	
	

}
