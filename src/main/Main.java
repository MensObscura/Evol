package main;

import vue.VueFx;

public class Main {

	public static void main(String args[]){
		
		if( (args.length == 13 && args[0].equals("-wator")) ||(args.length == 9 && args[0].equals("-billes"))|| (args.length == 8 && args[0].equals("-pacman")) ){

		VueFx fx = new VueFx();
		fx.run(args);
	
		}else{
			System.out.println("Error nb argument : main -billes nbBilles  taille  tAgent vitesse torique visibleGrid equit seed");
			System.out.println("Exemple d'options : main -billes   100      100       3     100     true      true     true   0");
		
			System.out.println("Error nb argument : main -wator nbNemos	nbRequins  taille  tAgent vitesse torique visibleGrid equit seed	ageReproductionNemo	ageReproductionNemo	faimRequin");
			System.out.println("Exemple d'options : main -wator   100       100    100       3      100    true     true     true   0				5                 7	               3	");
			
			System.out.println("Error nb argument : main -pacman nbChasseur nbMur  taille  tAgent torique visibleGrid seed	");
			System.out.println("Exemple d'options : main -pacman   10        500    100       3     true   	true 	0	");
			
			
		
		}
	}

}
