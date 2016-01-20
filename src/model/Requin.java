package model;

import java.util.Random;

public class Requin extends Bille {

	private int reproduction;
	private int manger;
	private int lastMeal;
	private int age ;

	public Requin(int posX, int posY, Environnement environnement, int reproduction, int manger) {
		super(posX, posY, environnement);

	}



	public void doIt(){
		if(this.starve()){
			this.environnement.removeAgent(this);
			return;
		}
		if(this.canIeat()){
			this.eatThatNemo();
		}
		if(this.timeToHaveChild()){
			this.environnement.addAgent(new Requin(this.posX-1,this.posY,this.environnement,this.reproduction, this.manger));//TODO
		}else{
			super.doIt();
		}
		lastMeal ++;
		age ++;
	}



	private void eatThatNemo() {
		// TODO Auto-generated method stub
		
	}



	private boolean canIeat() {
		// TODO Auto-generated method stub
		return false;
	}



	private boolean timeToHaveChild() {

		return reproduction >= age;
	}

	private boolean starve() {

		return manger < lastMeal;
	}

}
