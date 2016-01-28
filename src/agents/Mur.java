package agents;

import model.Environnement;

public class Mur extends Agent {

	public Mur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
	}
	
	public void doIt(){
		// RIEN
	}

}
