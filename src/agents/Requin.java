package agents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.scene.shape.Circle;
import model.Cellule;
import model.Environnement;

public class Requin extends AgentReproductible {

	private int manger;
	private int lastMeal;
	private Nemo toEat;

	public Requin(int posX, int posY, Environnement environnement, int reproduction, int manger) {
		super(posX, posY, environnement, reproduction);
		this.manger = manger;
		this.lastMeal = 0;
		this.checkTour = true;
		this.color = Color.DARK_GRAY;
		this.shape = new Circle(4, javafx.scene.paint.Color.BLACK);
		this.shape.relocate(posX *10, posY *10);
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
		else{
			if(this.timeToHaveChild() && this.canImove()){
				if (this.popBaby() == null) {
					if (this.canImove()) {
						super.doIt();
					}
					if (repos != 0)
						repos--;
				}
			}
			else{
				if (this.canImove()) {
					super.doIt();
				}
				if (repos != 0)
					repos--;
			}
		}
		lastMeal ++;
		age ++;
		this.shape.relocate(posX*10, posY*10);
	}



	private void eatThatNemo() {
		Cellule[][] cels = this.environnement.getEspace();

		int[] c = new int [2];
		c[0] = this.toEat.getPosX();
		c[1] =this.toEat.getPosY();


		if (c != null) {
			cels[this.posX][this.posY].removeAgent();
			this.posX = c[0];
			this.posY = c[1];
			this.environnement.removeAgent(cels[this.posX][this.posY].getAgent());
			cels[this.posX][this.posY].setAgent(this);
		}

	}

	private boolean canIeat() {

		int[] i = getNemoCellule();
		if(i !=null)
			this.toEat = (Nemo) this.environnement.getEspace()[i[0]][i[1]].getAgent();
		return i != null && this.toEat != null;
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
		ArrayList<Integer> checkPassage = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7}));
		Collections.shuffle(checkPassage);
		for(int i = 0; i<checkPassage.size();i++ ){
			if (this.environnement.isTorique()) {
				switch((Integer)checkPassage.get(i)){
				case 0 :
					if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][this.posY].getAgent() instanceof Nemo) {
						x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
						y = this.posY;
						return new int[]{x, y};
					}
					break;
				case 1 :
					if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].getAgent() instanceof Nemo) {
						x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
						y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
						return new int[]{x, y};
					}
					break;
				case 2 :
					if (cels[this.posX][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].getAgent() instanceof Nemo) {
						x = this.posX;
						y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
						return new int[]{x, y};
					}
					break;
				case 3 :
					if (cels[(this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1)][(this.posY+1)%this.environnement.getTaille()].getAgent() instanceof Nemo) {
						x = (this.posX == 0)?(this.environnement.getTaille()-1):(this.posX-1);
						y = (this.posY+1)%this.environnement.getTaille();
						return new int[]{x, y};
					}
					break;
				case 4 :
					if (cels[(this.posX+1)%this.environnement.getTaille()][(this.posY+1)%this.environnement.getTaille()].getAgent() instanceof Nemo) {
						x = (this.posX+1)%this.environnement.getTaille();
						y = (this.posY+1)%this.environnement.getTaille();
						return new int[]{x, y};
					}
					break;
				case 5 :
					if (cels[(this.posX+1)%this.environnement.getTaille()][(this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1)].getAgent() instanceof Nemo) {
						x = (this.posX+1)%this.environnement.getTaille();
						y = (this.posY == 0)?(this.environnement.getTaille()-1):(this.posY-1);
						return new int[]{x, y};
					}
					break;
				case 6 :
					if (cels[this.posX][(this.posY+1)%this.environnement.getTaille()].getAgent() instanceof Nemo) {
						x = this.posX;
						y = (this.posY+1)%this.environnement.getTaille();
						return new int[]{x, y};
					}
					break;
				case 7 :
					if (cels[(this.posX+1)%this.environnement.getTaille()][this.posY].getAgent() instanceof Nemo) {
						x = (this.posX+1)%this.environnement.getTaille();
						y = this.posY;
						return new int[]{x, y};
					}
					break;

				default:
					return null;
				}
				return null;
			}
			else {
				switch((Integer)checkPassage.get(i)){
				case 0 :
					if (this.posX != 0 && cels[this.posX-1][this.posY].getAgent() instanceof Nemo) {
						x = this.posX-1;
						y = this.posY;
						return new int[]{x, y};
					}break;
				case 1 :
					if (this.posY != 0 && cels[this.posX][this.posY-1].getAgent() instanceof Nemo) {
						x = this.posX;
						y = this.posY-1;
						return new int[]{x, y};
					}break;
				case 2:

					if (this.posX != 0 && this.posY != this.environnement.getTaille()-1 && cels[this.posX-1][this.posY+1].getAgent() instanceof Nemo) {
						x = this.posX-1;
						y = this.posY+1;
						return new int[]{x, y};
					}break;
				case 3 :

					if (this.posY != this.environnement.getTaille()-1 && this.posX != this.environnement.getTaille()-1 && cels[this.posX+1][this.posY+1].getAgent() instanceof Nemo) {
						x = this.posX+1;
						y = this.posY+1;
						return new int[]{x, y};
					}break;
				case 4 :

					if (this.posX != this.environnement.getTaille()-1 && this.posY != 0 && cels[this.posX+1][this.posY-1].getAgent() instanceof Nemo) {
						x = this.posX+1;
						y = this.posY-1;
						return new int[]{x, y};
					}break;
				case 5 :

					if (this.posY != this.environnement.getTaille()-1 && cels[this.posX][this.posY+1].getAgent() instanceof Nemo) {
						x = this.posX;
						y = this.posY+1;
						return new int[]{x, y};
					}break;
				case 6 :

					if (this.posX != this.environnement.getTaille()-1 && cels[this.posX+1][this.posY].getAgent() instanceof Nemo) {
						x = this.posX+1;
						y = this.posY;
						return new int[]{x, y};
					}
					break;
				case 7 :

					if (this.posX != 0 && this.posY !=  0 && cels[this.posX-1][this.posY-1].getAgent() instanceof Nemo) {
						x = this.posX-1;
						y = this.posY-1;
						return new int[]{x, y};
					}break;
				default:
					return null;
				}

				return null;

			}
		}
		return null;
	}

	protected Agent popBaby() {
		int[] c = getFreeCellule();
		Agent baby = new Requin(0,0,this.environnement, this.reproduction, this.manger);

		if (c != null) {
			baby.setPosX(c[0]);
			baby.setPosY(c[1]);
			this.environnement.getEspace()[c[0]][c[1]].setAgent(baby);
			this.environnement.addAgent(baby);
			this.repos = 3;
			
			return baby;
		}
		else {
			return null;
		}
	}


}
