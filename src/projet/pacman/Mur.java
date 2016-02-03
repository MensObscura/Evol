package projet.pacman;

import java.awt.Color;

import core.agents.Agent;
import core.model.Environnement;
import javafx.scene.shape.Rectangle;

public class Mur extends Agent {

	public Mur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.GRAY;
		this.shape = new Rectangle(this.environnement.getSMA().gettAgent()*2,this.environnement.getSMA().gettAgent()*2,javafx.scene.paint.Color.GRAY);
		this.shape.relocate(posX*10 , posY*10 );
	}
	@Override
	public void doIt(){
		// RIEN
	}

}
