package agents;

import java.awt.Color;

import javafx.scene.shape.Rectangle;
import model.Environnement;

public class Arrivee extends Mur{

	public Arrivee(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.WHITE;
		this.shape = new Rectangle(this.environnement.getSMA().gettAgent()*2+3,this.environnement.getSMA().gettAgent()*2+3,javafx.scene.paint.Color.WHITE);
		this.shape.relocate(posX*10 , posY*10 );
	}
	@Override
	public void doIt(){
		// RIEN
	}

}
