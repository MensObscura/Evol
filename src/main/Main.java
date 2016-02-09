package main;

import vue.VueFx;

public class Main {

	public static void main(String args[]){
		
		if( (args.length == 3 && args[0].equals("-wator")) ||(args.length == 3 && args[0].equals("-billes"))|| (args.length == 3 && args[0].equals("-pacman")) ){

		VueFx fx = new VueFx();
		fx.run(args);
	
		}else{
			System.out.println("Error nb argument : main -billes  taille  tailleAgent");
			System.out.println("Exemple d'options : main -billes   100     		4  ");
		
			System.out.println("Error nb argument : main -wator taille  tailleAgent ");
			System.out.println("Exemple d'options : main -wator   100  		4  ");
			
			System.out.println("Error nb argument : main -pacman	taille  tailleAgent	");
			System.out.println("Exemple d'options : main -pacman  	 100     	4  ");
			
			
		
		}
	}

}
