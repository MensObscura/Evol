package agents;

import java.awt.Color;

import javafx.scene.shape.Circle;
import model.Cellule;
import model.Environnement;

public class Nemo extends Agent {

	private int reproduction;
	private int age ;

	public Nemo(int posX, int posY, Environnement environnement, int reproduction) {
		super(posX, posY, environnement);
		this.age = 0;
		this.reproduction = reproduction;
		this.checkTour = true;
		this.color = Color.ORANGE;
		this.shape = new Circle(3, javafx.scene.paint.Color.ORANGE);
		this.shape.relocate(posX*10 , posY*10 );
	}



	public void doIt(){
		int repro= r.nextInt(2);
		if(this.timeToHaveChild() && this.canImove() && repro == 0 ) {
			this.popBaby();
		}else{
			if (this.canImove()) {
				super.doIt();
			}
		}
		age ++;
		this.shape.relocate(posX*10, posY*10);
	}


	private boolean timeToHaveChild() {
		return reproduction <= age;
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
				y = this.posY;
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
		Agent baby = new Nemo(0,0,this.environnement, this.reproduction);


		if (c != null) {
			baby.setPosX(c[0]);
			baby.setPosY(c[1]);
			this.environnement.getEspace()[c[0]][c[1]].setAgent(baby);
			this.environnement.addAgent(baby);
		}
	}



	public void setReproduction(int reproduction) {
		this.reproduction = reproduction;
	}

}
