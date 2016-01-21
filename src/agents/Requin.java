package agents;

import java.util.Random;

import model.Cellule;
import model.Environnement;

public class Requin extends Agent {

	private int reproduction;
	private int manger;
	private int lastMeal;
	private int age ;

	public Requin(int posX, int posY, Environnement environnement, int reproduction, int manger) {
		super(posX, posY, environnement);
		this.manger = manger;
		this.lastMeal = 0;
		this.age = 0;
		this.reproduction = reproduction;
	}



	public void doIt(){
		if(!this.starve()){
			this.environnement.removeAgent(this);
			return;
		}
		if(this.canIeat()){
			this.eatThatNemo();
			this.lastMeal = 0;
		}
		if(this.timeToHaveChild() && this.canImove()){
			this.popBaby();
		}else{
			if (this.canImove()) {
				super.doIt();
			}
		}
		lastMeal ++;
		age ++;
	}



	private void eatThatNemo() {
		Cellule[][] cels = this.environnement.getEspace();
		

		if  (cels[this.posX-1][this.posY].getAgent() instanceof Nemo) {
			cels[this.posX][this.posY].removeAgent();
			this.posX = this.posX-1;
			
			cels[this.posX-1][this.posY].setAgent(this);
		}
		else if (cels[this.posX-1][this.posY-1].isEmpty()) {
			cels[this.posX-1][this.posY-1].removeAgent();
			this.posX = this.posX-1;
			this.posY = this.posY-1;
			
			cels[this.posX-1][this.posY-1].setAgent(this);
		}
		else if (cels[this.posX][this.posY-1].isEmpty()) {
			cels[this.posX][this.posY].removeAgent();
			this.posY = this.posY-1;
			
			cels[this.posX][this.posY-1].setAgent(this);
		}
		else if (cels[this.posX-1][this.posY+1].isEmpty()) {
			cels[this.posX-1][this.posY+1].removeAgent();
			this.posX = this.posX-1;
			this.posY = this.posY+1;
			
			cels[this.posX-1][this.posY+1].setAgent(this);
		}
		else if (cels[this.posX+1][this.posY+1].isEmpty()) {
			this.posX = this.posX+1;
			this.posY = this.posY+1;
			
			cels[this.posX+1][this.posY+1].setAgent(this);
		}
		else if (cels[this.posX+1][this.posY-1].isEmpty()) {
			this.posX = this.posX+1;
			this.posY = this.posY-1;
			
			cels[this.posX+1][this.posY-1].setAgent(this);
		}
		else if (cels[this.posX][this.posY+1].isEmpty()) {
			this.posY = this.posY+1;
			
			cels[this.posX][this.posY+1].setAgent(this);
		}
		else {
			this.posX = this.posX+1;
			
			cels[this.posX+1][this.posY].setAgent(this);
		}
		
	}



	private boolean canIeat() {
		Cellule[][] cels = this.environnement.getEspace();
		return cels[this.posX-1][this.posY].getAgent() instanceof Nemo
				|| cels[this.posX-1][this.posY-1].getAgent() instanceof Nemo
				|| cels[this.posX][this.posY-1].getAgent() instanceof Nemo
				|| cels[this.posX-1][this.posY+1].getAgent() instanceof Nemo
				|| cels[this.posX+1][this.posY+1].getAgent() instanceof Nemo
				|| cels[this.posX+1][this.posY-1].getAgent() instanceof Nemo
				|| cels[this.posX][this.posY+1].getAgent() instanceof Nemo
				|| cels[this.posX+1][this.posY].getAgent() instanceof Nemo;
	}

	private boolean canImove() {
		Cellule[][] cels = this.environnement.getEspace();
		return cels[this.posX-1][this.posY].isEmpty()
				|| cels[this.posX-1][this.posY-1].isEmpty()
				|| cels[this.posX][this.posY-1].isEmpty()
				|| cels[this.posX-1][this.posY+1].isEmpty()
				|| cels[this.posX+1][this.posY+1].isEmpty()
				|| cels[this.posX+1][this.posY-1].isEmpty()
				|| cels[this.posX][this.posY+1].isEmpty()
				|| cels[this.posX+1][this.posY].isEmpty();
	}


	private boolean timeToHaveChild() {

		return reproduction >= age;
	}

	private boolean starve() {

		return manger > lastMeal;
	}
	
	private void popBaby() {
		Cellule[][] cels = this.environnement.getEspace();
		Agent baby = new Requin(0,0,this.environnement, this.reproduction, this.manger);
		if  (cels[this.posX-1][this.posY].isEmpty()) {
			baby.setPosX(this.posX-1);
			baby.setPosY(this.posY);
			cels[this.posX-1][this.posY].setAgent(baby);
		}
		else if (cels[this.posX-1][this.posY-1].isEmpty()) {
			baby.setPosX(this.posX-1);
			baby.setPosY(this.posY-1);
			cels[this.posX-1][this.posY-1].setAgent(baby);
		}
		else if (cels[this.posX][this.posY-1].isEmpty()) {
			baby.setPosX(this.posX);
			baby.setPosY(this.posY-1);
			cels[this.posX][this.posY-1].setAgent(baby);
		}
		else if (cels[this.posX-1][this.posY+1].isEmpty()) {
			baby.setPosX(this.posX-1);
			baby.setPosY(this.posY+1);
			cels[this.posX-1][this.posY+1].setAgent(baby);
		}
		else if (cels[this.posX+1][this.posY+1].isEmpty()) {
			baby.setPosX(this.posX+1);
			baby.setPosY(this.posY+1);
			cels[this.posX+1][this.posY+1].setAgent(baby);
		}
		else if (cels[this.posX+1][this.posY-1].isEmpty()) {
			baby.setPosX(this.posX+1);
			baby.setPosY(this.posY);
			cels[this.posX+1][this.posY-1].setAgent(baby);
		}
		else if (cels[this.posX][this.posY+1].isEmpty()) {
			baby.setPosX(this.posX);
			baby.setPosY(this.posY+1);
			cels[this.posX][this.posY+1].setAgent(baby);
		}
		else {
			baby.setPosX(this.posX+1);
			baby.setPosY(this.posY);
			cels[this.posX+1][this.posY].setAgent(baby);
		}
	}

}
