package core.agents;

import core.model.Environnement;
import projet.billes.Bille;
import projet.pacman.Arrivee;
import projet.pacman.Chasseur;
import projet.pacman.Mur;
import projet.pacman.Protecteur;
import projet.wator.Nemo;
import projet.wator.Requin;

public class AgentFactory {

	public static AgentFactory factory;
	
	public AgentFactory()
	{
		
	}
	
	public static AgentFactory getInstance(){
		if(factory == null){
			factory = new AgentFactory();
		}
		return factory;
	}
	
	
	public Agent getAgent(String agent,Environnement environnement, int x, int y, String args[]){
		
		switch(agent){
		case "bille" : return new Bille(x,y,environnement);
		case "requin" : if(args.length == 2  ){return new Requin(x,y,environnement, Integer.parseInt(args[0]), Integer.parseInt(args[1]));} break;
		case "nemo" : if(args.length == 1 ){return new Nemo(x,y,environnement, Integer.parseInt(args[0]));} break;
		case "chasseur" : return new Chasseur(x,y,environnement);
		case "mur" : return new Mur(x,y,environnement);
		case "arrivee" : return new Arrivee(x,y,environnement);
		case "protecteur" : return new Protecteur(x,y,environnement);
		default:
			return null;
		}
		return null;
	}
}
