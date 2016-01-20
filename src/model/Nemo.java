package model;

import java.util.Random;

public class Nemo extends Bille {

	private int reproduction;
	private int age ;

	public Nemo(int posX, int posY, Environnement environnement, int reproduction) {
		super(posX, posY, environnement);

	}



	public void doIt(){
		if(this.timeToHaveChild()){
			this.environnement.addAgent(new Nemo(this.posX-1,this.posY,this.environnement,this.reproduction));//TODO
		}else{
			super.doIt();
		}
		age ++;
	}



	private boolean timeToHaveChild() {

		return reproduction >= age;
	}



}
