package agents;

import model.Cellule;
import model.Environnement;

public class Nemo extends Agent {

	private int reproduction;
	private int age ;

	public Nemo(int posX, int posY, Environnement environnement, int reproduction) {
		super(posX, posY, environnement);
		this.age = 0;
		this.reproduction = reproduction;
	}



	public void doIt(){
		if(this.timeToHaveChild() && this.canImove()){
			this.popBaby();
		}else{
			if (this.canImove()) {
				super.doIt();
			}
		}
		age ++;
	}


	private boolean timeToHaveChild() {
		return reproduction >= age;
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
	
	private void popBaby() {
		Cellule[][] cels = this.environnement.getEspace();
		Agent baby = new Nemo(0,0,this.environnement, this.reproduction);
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
