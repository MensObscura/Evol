package agents;

import java.awt.Color;


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
		this.color = Color.DARK_GRAY;
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
		
		int[] c = getNemoCellule();
		
		if (c != null) {
			cels[this.posX][this.posY].removeAgent();
			this.posX = c[0];
			this.posY = c[1];
			this.environnement.removeAgent(cels[this.posX][this.posY].getAgent());
			cels[this.posX][this.posY].setAgent(this);
		}
		
	}

	private boolean canIeat() {
		return getNemoCellule() != null;
	}

	private boolean timeToHaveChild() {

		return reproduction <= age;
	}

	public void setReproduction(int reproduction) {
		this.reproduction = reproduction;
	}



	public void setManger(int manger) {
		this.manger = manger;
	}



	private boolean starve() {

		return manger > lastMeal;
	}
	
	private int[] getNemoCellule() {
		Cellule[][] cels = this.environnement.getEspace();
		
		int x;
		int y;
		
		if (this.environnement.isTorique()) {

			if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][this.posY].getAgent() instanceof Nemo) {
				x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
				y = this.posY;
				return new int[]{x, y};
			}
			
			if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].getAgent() instanceof Nemo) {
				x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
				y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
				return new int[]{x, y};
			}
			
			if (cels[this.posX][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].getAgent() instanceof Nemo) {
				x = this.posX;
				y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
				return new int[]{x, y};
			}
			if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][(this.posY+1)%this.environnement.getTaille()].getAgent() instanceof Nemo) {
				x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
				y = (this.posY+1)%this.environnement.getTaille();
				return new int[]{x, y};
			}
			
			if (cels[(this.posX+1)%this.environnement.getTaille()][(this.posY+1)%this.environnement.getTaille()].getAgent() instanceof Nemo) {
				x = (this.posX+1)%this.environnement.getTaille();
				y = (this.posY+1)%this.environnement.getTaille();
				return new int[]{x, y};
			}

			if (cels[(this.posX+1)%this.environnement.getTaille()][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].getAgent() instanceof Nemo) {
				x = (this.posX+1)%this.environnement.getTaille();
				y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
				return new int[]{x, y};
			}
				
				
			if (cels[this.posX][(this.posY+1)%this.environnement.getTaille()].getAgent() instanceof Nemo) {
				x = this.posX;
				y = (this.posY+1)%this.environnement.getTaille();
				return new int[]{x, y};
			}
				
			if (cels[(this.posX+1)%this.environnement.getTaille()][this.posY].getAgent() instanceof Nemo) {
				x = (this.posX+1)%this.environnement.getTaille();
				y = this.posY;
				return new int[]{x, y};
			}
				
			return null;

		}
		else {
			if (this.posX != 0 && cels[this.posX-1][this.posY].getAgent() instanceof Nemo) {
				x = this.posX-1;
				y = this.posY;
				return new int[]{x, y};
			}
			
			if (this.posY != 0 && cels[this.posX][this.posY-1].getAgent() instanceof Nemo) {
				x = this.posX;
				y = this.posY-1;
				return new int[]{x, y};
			}

			if (this.posX != 0 && this.posY != this.environnement.getTaille()-1 && cels[this.posX-1][this.posY+1].getAgent() instanceof Nemo) {
				x = this.posX-1;
				y = this.posY+1;
				return new int[]{x, y};
			}
			
			if (this.posY != this.environnement.getTaille()-1 && this.posX != this.environnement.getTaille()-1 && cels[this.posX+1][this.posY+1].getAgent() instanceof Nemo) {
				x = this.posX+1;
				y = this.posY+1;
				return new int[]{x, y};
			}
			
			if (this.posX != this.environnement.getTaille()-1 && this.posY != 0 && cels[this.posX+1][this.posY-1].getAgent() instanceof Nemo) {
				x = this.posX+1;
				y = this.posY-1;
				return new int[]{x, y};
			}
				
			if (this.posY != this.environnement.getTaille()-1 && cels[this.posX][this.posY+1].getAgent() instanceof Nemo) {
				x = this.posX;
				y = this.posY+1;
				return new int[]{x, y};
			}
				
			if (this.posX != this.environnement.getTaille()-1 && cels[this.posX+1][this.posY].getAgent() instanceof Nemo) {
				x = this.posX+1;
				y = this.posY;
				return new int[]{x, y};
			}
				
			return null;
	
		}
	}
	
	private int[] getFreeCellule() {
		Cellule[][] cels = this.environnement.getEspace();
		
		int x;
		int y;
		
		if (this.environnement.isTorique()) {

			if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][this.posY].isEmpty()) {
				x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
				y = this.posY;
				return new int[]{x, y};
			}
			
			if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].isEmpty()) {
				x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
				y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
				return new int[]{x, y};
			}
			
			if (cels[this.posX][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].isEmpty()) {
				x = this.posX;
				y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
				return new int[]{x, y};
			}
			
			if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][(this.posY+1)%this.environnement.getTaille()].isEmpty()) {
				x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
				y = (this.posY+1)%this.environnement.getTaille();
				return new int[]{x, y};
			}
			
			if (cels[(this.posX+1)%this.environnement.getTaille()][(this.posY+1)%this.environnement.getTaille()].isEmpty()) {
				x = (this.posX+1)%this.environnement.getTaille();
				y = (this.posY+1)%this.environnement.getTaille();
				return new int[]{x, y};
			}

			if (cels[(this.posX+1)%this.environnement.getTaille()][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].isEmpty()) {
				x = (this.posX+1)%this.environnement.getTaille();
				y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
				return new int[]{x, y};
			}
				
				
			if (cels[this.posX][(this.posY+1)%this.environnement.getTaille()].isEmpty()) {
				x = this.posX;
				y = (this.posY+1)%this.environnement.getTaille();
				return new int[]{x, y};
			}
				
			if (cels[(this.posX+1)%this.environnement.getTaille()][this.posY].isEmpty()) {
				x = (this.posX+1)%this.environnement.getTaille();
				y = this.posY;
				return new int[]{x, y};
			}
				
			return null;

		}
		else {
			if (this.posX != 0 && cels[this.posX-1][this.posY].isEmpty()) {
				x = this.posX-1;
				y = this.posY-1;
				return new int[]{x, y};
			}
			
			if (this.posY != 0 && cels[this.posX][this.posY-1].isEmpty()) {
				x = this.posX;
				y = this.posY-1;
				return new int[]{x, y};
			}

			if (this.posX != 0 && this.posY != this.environnement.getTaille()-1 && cels[this.posX-1][this.posY+1].isEmpty()) {
				x = this.posX-1;
				y = this.posY+1;
				return new int[]{x, y};
			}
			
			if (this.posY != this.environnement.getTaille()-1 && this.posX != this.environnement.getTaille()-1 && cels[this.posX+1][this.posY+1].isEmpty()) {
				x = this.posX+1;
				y = this.posY+1;
				return new int[]{x, y};
			}
			
			if (this.posX != this.environnement.getTaille()-1 && this.posY != 0 && cels[this.posX+1][this.posY-1].isEmpty()) {
				x = this.posX+1;
				y = this.posY-1;
				return new int[]{x, y};
			}
				
			if (this.posY != this.environnement.getTaille()-1 && cels[this.posX][this.posY+1].isEmpty()) {
				x = this.posX;
				y = this.posY+1;
				return new int[]{x, y};
			}
				
			if (this.posX != this.environnement.getTaille()-1 && cels[this.posX+1][this.posY].isEmpty()) {
				x = this.posX+1;
				y = this.posY;
				return new int[]{x, y};
			}
				
			return null;
	
		}
	}
	
	private boolean canImove() {
		return getFreeCellule() != null;
	}
	
	private void popBaby() {
		int[] c = getFreeCellule();
		Agent baby = new Requin(0,0,this.environnement, this.reproduction, this.manger);
		
		if (c != null) {
			baby.setPosX(c[0]);
			baby.setPosY(c[1]);
			this.environnement.getEspace()[c[0]][c[1]].setAgent(baby);
			this.environnement.addAgent(baby);
		}
	}


}
