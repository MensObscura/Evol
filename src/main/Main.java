package main;

import vue.VueFx;

public class Main {

	public static void main(String args[]){
		//System.out.println(args.length);
		
		if( args.length == 14 ||args.length == 10){

		VueFx fx = new VueFx();
		fx.run(args);
	
		}else{
			System.out.println("Error nb argument : main -billes nbBilles  taille  tAgent vitesse torique visibleGrid equit seed");
			System.out.println("Exemple d'options : main -billes   100      1000      2     2200    true     true     true   0");
		
			System.out.println("Error nb argument : main -wator nbNemos	nbRequins  taille  tAgent vitesse torique visibleGrid equit seed	ageReproductionNemo	ageReproductionNemo	faimRequin");
			System.out.println("Exemple d'options : main -wator   100       100    1000      2     2200    true     true     true   0				5					7				3	");
		}
	}

}
