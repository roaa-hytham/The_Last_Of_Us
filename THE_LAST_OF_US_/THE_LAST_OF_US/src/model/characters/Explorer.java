package model.characters;

import exceptions.*;
import engine.Game;

public class Explorer extends Hero {
	
	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
	}
	
	public void seeMap() {
		for(int i=0; i< Game.map.length ; i++ ) {
			for(int j=0; j< Game.map[i].length; j++) {
				Game.map[i][j].setVisible(true);
			}
			}
	}
	
	public void useSpecial() throws Exception {
		super.useSpecial();
		if(getActionsAvailable() == 0)
			throw new NotEnoughActionsException();
		seeMap();
		setActionsAvailable(getActionsAvailable()-1);
		
		}


	
}
