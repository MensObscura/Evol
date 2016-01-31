package agents;

import java.awt.Color;

import javafx.scene.shape.Circle;
import model.Environnement;

public class Protecteur extends Bille {

	public Protecteur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour=false;
		this.color = Color.BLUE;
		this.shape = new Circle(this.environnement.getSMA().gettAgent()+2,javafx.scene.paint.Color.BLUEVIOLET);
		this.shape.relocate(posX * 10, posY *10);
	}

}
