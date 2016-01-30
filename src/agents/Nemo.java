package agents;

import java.awt.Color;

import javafx.scene.shape.Circle;
import model.Environnement;

public class Nemo extends AgentReproductible {

	public Nemo(int posX, int posY, Environnement environnement, int reproduction) {
		super(posX, posY, environnement, reproduction);
		this.checkTour = true;
		this.color = Color.ORANGE;
		this.shape = new Circle(3, javafx.scene.paint.Color.ORANGE);
		this.shape.relocate(posX*10 , posY*10 );
	}



	public void doIt(){
		int repro= r.nextInt(2);
		if(this.timeToHaveChild() && this.canImove() && repro == 0 ) {
			this.popBaby();
		}else{
			if (this.canImove()) {
				super.doIt();
			}
		}
		age ++;
		this.shape.relocate(posX*10, posY*10);
	}

	protected void popBaby() {
		int[] c = getFreeCellule();
		Agent baby = new Nemo(0,0,this.environnement, this.reproduction);


		if (c != null) {
			baby.setPosX(c[0]);
			baby.setPosY(c[1]);
			this.environnement.getEspace()[c[0]][c[1]].setAgent(baby);
			this.environnement.addAgent(baby);
		}
	}

}
