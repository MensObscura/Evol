package core.model;

import core.agents.Agent;

public class Cellule {

	private Agent agent;

	public Cellule(){

	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void removeAgent() {
		this.agent = null;
	}

	public boolean isEmpty(){
		
		if(this.agent == null)
			return true;
		return false;
	}

}
