package model;

import agents.Agent;
import agents.Bille;
import agents.Chasseur;
import agents.Mur;
import agents.Nemo;
import agents.Requin;

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
		case "chasseur" : if(args.length == 1 ){return new Chasseur(x,y,environnement);} break;
		case "mur" : if(args.length == 1 ){return new Mur(x,y,environnement);} break;
		default:
			return null;
		}
		return null;
	}
}
