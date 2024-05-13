package model.characters;

import exceptions.*;
import model.characters.*;
import engine.Game;

public class Explorer extends Hero {
	
	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		super.attack();
	}
	public void defend(Character c) {
		super.defend(c);
	}
	
	public void seeMap() {
		for(int i=0; i< Game.map.length ; i++ ) {
			for(int j=0; j< Game.map[i].length; j++) {
				Game.map[i][j].setVisible(true);
			}
		}
	}
	
	public void useSpecial() throws NoAvailableResourcesException,NotEnoughActionsException, InvalidTargetException {
		if(getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException();
		super.useSpecial();
		seeMap();
	}


	
}