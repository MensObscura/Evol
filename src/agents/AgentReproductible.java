package agents;

import model.Cellule;
import model.Environnement;

public abstract class AgentReproductible extends Agent {
	
	protected int reproduction;
	protected int age ;

	public AgentReproductible(int posX, int posY, Environnement environnement, int reproduction) {
		super(posX, posY, environnement);
		this.age = 0;
		this.reproduction = reproduction;
	}
	
	protected boolean timeToHaveChild() {
		return reproduction <= age;
	}
	
	public void setReproduction(int reproduction) {
		this.reproduction = reproduction;
	}
	
	protected int[] getFreeCellule() {
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

	protected boolean canImove() {
		return getFreeCellule() != null;
	}
	
	protected abstract void popBaby();

}
