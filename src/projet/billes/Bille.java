package projet.billes;

import core.agents.Agent;
import core.model.Environnement;

public class Bille extends Agent {

	public Bille(int posX, int posY,Environnement environnement) {
		super( posX,  posY, environnement);
		this.checkTour = false;
		
	}

	@Override
	public void doIt() {
		this.calculateNextCase(0);
		this.environnement.getEspace()[this.posX][this.posY].removeAgent();;

		this.setPosX(this.nextX);
		this.setPosY(this.nextY);

		this.environnement.getEspace()[this.posX][this.posY].setAgent(this);
	}

}
