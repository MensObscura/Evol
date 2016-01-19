package main;

import model.SMA;
import vue.Vue;

public class Main {

	public static void main(String args[]){

		
		if( args.length == 8){
			System.out.println("Début du main");
		SMA action = new SMA(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Boolean.parseBoolean(args[4]),Boolean.parseBoolean(args[5]),Boolean.parseBoolean(args[6]),Integer.parseInt(args[7]));
		Vue vue = new Vue (action);
		action.run();
		}else{
			System.out.println("Error nb argument : main  nbBilles  taille  tAgent vitesse torique visibleGrid equit seed");
			System.out.println("Exemple d'options : main   100       1000      2     2200    true     true     true   0");
		}
	}

}
