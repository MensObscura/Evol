package projet.pacman;

import java.awt.Color;
import java.util.Random;

import core.model.Environnement;
import javafx.scene.shape.Circle;
import projet.billes.Bille;

public class Protecteur extends Agent {

	
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
			int x =this.environnement.getRandomCoord(-1);
			int y =this.environnement.getRandomCoord(x);
			Protecteur protecteur = new Protecteur(x,y,this.environnement);
			this.environnement.addAgent(protecteur);
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
