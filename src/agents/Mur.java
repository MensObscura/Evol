package agents;

import java.awt.Color;

import model.Environnement;

public class Mur extends Agent {

	public Mur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.BLACK;
	}
	
	public void doIt(){
		// RIEN
	}

}
