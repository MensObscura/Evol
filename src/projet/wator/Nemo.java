package projet.wator;

import java.awt.Color;

import core.agents.Agent;
import core.agents.AgentReproductible;
import core.model.Environnement;
import javafx.scene.shape.Circle;

public class Nemo extends AgentReproductible {

	public Nemo(int posX, int posY, Environnement environnement, int reproduction) {
		super(posX, posY, environnement, reproduction);
		this.checkTour = true;
		this.color = Color.ORANGE;
		this.shape = new Circle(this.environnement.getSMA().gettAgent(), javafx.scene.paint.Color.ORANGE);
		this.shape.relocate(posX*10 , posY*10 );
	}

	protected boolean timeToHaveChild() {
		boolean res = super.timeToHaveChild();
		
		if (res && r.nextInt(5) != 0)
			return false;
		else
			return res;
	}


	public void doIt(){

		if(this.timeToHaveChild() && this.canImove()) {
			if (this.popBaby() == null) {
				if (this.canImove()) {
					super.doIt();
				}
				if (repos != 0)
					repos--;
			}
		}
		else{
			if (this.canImove()) {
				super.doIt();
			}
			if (repos != 0)
				repos--;
		}
		age ++;
		this.shape.relocate(posX*10, posY*10);
	}

	protected Agent popBaby() {
		
		int[] c = getFreeCellule();
		Agent baby = new Nemo(0,0,this.environnement, this.reproduction);

		if (c != null) {
			baby.setPosX(c[0]);
			baby.setPosY(c[1]);
			this.environnement.getEspace()[c[0]][c[1]].setAgent(baby);
			this.environnement.addAgent(baby);
			this.repos = 3;
			
			return baby;
		}
		else {
			return null;
		}
	}

}
