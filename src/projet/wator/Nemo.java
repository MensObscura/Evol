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

	public void doIt(){

		if(this.timeToHaveChild() && this.canImove()) {
			Agent baby = new Nemo(0,0,this.environnement, this.reproduction);
			if (this.popBaby(baby) == null) {
				if (this.canImove()) {
					randomWay();
				}
				if (repos != 0)
					repos--;
			}
			else {
				this.repos = r.nextInt(5) + 5;
			}
		}
		else{
			if (this.canImove()) {
				randomWay();
			}
			if (repos != 0)
				repos--;
		}
		age ++;
		this.shape.relocate(posX*10, posY*10);
	}

}
