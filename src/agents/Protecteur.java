package agents;

import java.awt.Color;
import java.util.Random;

import javafx.scene.shape.Circle;
import model.Environnement;
import sma.SMAPacMan;

public class Protecteur extends Bille {

	
	private int age;
	private boolean isAboutToDie;
	
	public Protecteur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour=false;
		this.isAboutToDie = false;
		this.color = Color.BLUE;
		this.shape = new Circle(this.environnement.getSMA().gettAgent()+2,javafx.scene.paint.Color.BLUEVIOLET);
		this.shape.relocate(posX * 10, posY *10);
		this.age = new Random().nextInt(this.environnement.getTaille()/3)+20;
	}

	@Override
	public void doIt() {
		age --;
		
		if(age == 3)
			this.aboutToDie();
		if(age < 0)
			this.die();
	}

	public void die() {
		this.environnement.removeAgent(this);
		((SMAPacMan)this.environnement.getSMA()).notifyDeath();
	}
	
	public boolean isAboutToDie(){
		return this.isAboutToDie;
	}
	public void aboutToDie(){
		this.shape = new Circle(this.environnement.getSMA().gettAgent()+2,javafx.scene.paint.Color.ORCHID);
		this.shape.relocate(posX * 10, posY *10);
		this.isAboutToDie=true;
		
	}
}
