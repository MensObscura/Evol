package projet.billes;

import core.agents.Agent;
import core.model.Environnement;

public class Bille extends Agent {

	public Bille(int posX, int posY,Environnement environnement) {
		super( posX,  posY, environnement);
		this.checkTour = false;
		
	}

}
