package main;

import vue.VueFx;

public class Main {

	public static void main(String args[]){
		
		if( args.length == 13 ||args.length == 9){

		VueFx fx = new VueFx();
		fx.run(args);
	
		}else{
			System.out.println("Error nb argument : main -billes nbBilles  taille  tAgent vitesse torique visibleGrid equit seed");
			System.out.println("Exemple d'options : main -billes   100      10 0      2     2200    true     true     true   0");
		
			System.out.println("Error nb argument : main -wator nbNemos	nbRequins  taille  tAgent vitesse torique visibleGrid equit seed	ageReproductionNemo	ageReproductionNemo	faimRequin");
			System.out.println("Exemple d'options : main -wator   100       100    100       2     2200    true     true     true   0				5                 7	               3	");
		}
	}

}
