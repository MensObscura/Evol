package main;

import sma.SMA;
import sma.SMAWator;
import vue.Vue;
import vue.VueFx;

public class Wator {

	public static void main(String args[]){

		
		if( args.length == 12){
			System.out.println("Début du main");

		SMAWator action = new SMAWator(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]),Boolean.parseBoolean(args[5]),Boolean.parseBoolean(args[6]),Boolean.parseBoolean(args[7]),Integer.parseInt(args[8]),Integer.parseInt(args[9]),Integer.parseInt(args[10]),Integer.parseInt(args[11]));
		Vue vue = new Vue (action, false);
		action.run();
//		VueFx fx = new VueFx();
//		fx.run(args);
		}else{
			System.out.println("Error nb argument : main  nbNemos	nbRequins  taille  tAgent vitesse torique visibleGrid equit seed	ageReproductionNemo	ageReproductionNemo	faimRequin");
			System.out.println("Exemple d'options : main   	100			100    1000      2     2200    true     true     true   0				5					7				3	");
		}
	}

}
