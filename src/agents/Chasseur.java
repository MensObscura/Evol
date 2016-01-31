package agents;

import java.awt.Color;
import java.util.ArrayList;

import javafx.scene.shape.Circle;
import model.Environnement;
import sma.SMAPacMan;

public class Chasseur extends Agent {

	public Chasseur(int posX, int posY, Environnement environnement) {
		super(posX, posY, environnement);
		this.checkTour = false;
		this.color = Color.RED;
		this.shape = new Circle(this.environnement.getSMA().gettAgent(), javafx.scene.paint.Color.RED);
		this.shape.relocate(posX*10 , posY*10 );
	}
	
	public void doIt(){
		int[] coord = this.chooseBestWay();
				
		this.environnement.getEspace()[this.posX][this.posY].removeAgent();

		this.setPosX(coord[0]);
		this.setPosY(coord[1]);

		this.environnement.getEspace()[this.posX][this.posY].setAgent(this);
	}
	
	private int[] chooseBestWay() {

		ArrayList<int[]> bestWays = new ArrayList<int[]>();
		
		int[][] espace = ((SMAPacMan)this.environnement.getSMA()).getDistances();
		
		int dmin = espace[this.posX][this.posY];
		
		if (this.posX == 0) {
			if (this.environnement.isTorique() && dmin >= espace[espace.length-1][this.posY] && espace[espace.length-1][this.posY] != -1) {
				if (dmin != espace[espace.length-1][this.posY]) {
					bestWays.clear();
					dmin = espace[espace.length-1][this.posY];
				}
				bestWays.add(new int[]{espace.length-1, this.posY});
			}
		}
		else {
			if (dmin >= espace[this.posX-1][this.posY] && espace[this.posX-1][this.posY] != -1) {
				if (dmin != espace[this.posX-1][this.posY]) {
					bestWays.clear();
					dmin = espace[this.posX-1][this.posY];
				}
				bestWays.add(new int[]{this.posX-1, this.posY});
			}
		}
		
		if (this.posX == espace.length-1) {
			if (this.environnement.isTorique() && dmin >= espace[0][this.posY] && espace[0][this.posY] != -1) {
				if (dmin != espace[0][this.posY]) {
					bestWays.clear();
					dmin = espace[0][this.posY];
				}
				bestWays.add(new int[]{0, this.posY});
			}
		}
		else {
			if (dmin >= espace[this.posX+1][this.posY] && espace[this.posX+1][this.posY] != -1) {
				if (dmin != espace[this.posX+1][this.posY]) {
					bestWays.clear();
					dmin = espace[this.posX+1][this.posY];
				}
				bestWays.add(new int[]{this.posX+1, this.posY});
			}
		}
		
		if (this.posY == 0) {
			if (this.environnement.isTorique() && dmin >= espace[this.posX][espace.length-1] && espace[this.posX][espace.length-1] != -1) {
				if (dmin != espace[this.posX][espace.length-1]) {
					bestWays.clear();
					dmin = espace[this.posX][espace.length-1];
				}
				bestWays.add(new int[]{this.posX, espace.length-1});
			}
		}
		else {
			if (dmin >= espace[this.posX][this.posY-1] && espace[this.posX][this.posY-1] != -1) {
				if (dmin != espace[this.posX][this.posY-1]) {
					bestWays.clear();
					dmin = espace[this.posX][this.posY-1];
				}
				bestWays.add(new int[]{this.posX, this.posY-1});
			}
		}
		
		if (this.posY == espace.length-1) {
			if (this.environnement.isTorique() && dmin >= espace[this.posX][0] && espace[this.posX][0] != -1) {
				if (dmin != espace[this.posX][0]) {
					bestWays.clear();
					dmin = espace[this.posX][0];
				}
				bestWays.add(new int[]{this.posX, 0});
			}
		}
		else {
			if (dmin >= espace[this.posX][this.posY+1] && espace[this.posX][this.posY+1] != -1) {
				if (dmin != espace[this.posX][this.posY+1]) {
					bestWays.clear();
					dmin = espace[this.posX][this.posY+1];
				}
				bestWays.add(new int[]{this.posX, this.posY+1});
				
			}
		}
		int index = r.nextInt(bestWays.size());
		return bestWays.get(index);
	}
	
}
