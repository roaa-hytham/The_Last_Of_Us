package model.characters;

import exceptions.*;
//import engine.Game;

public class Fighter extends Hero {
	
	public Fighter(String name, int maxHp, int attackDmg, int maxActions){
		super(name, maxHp , attackDmg , maxActions);
		
	}
	
	public void useSpecial() throws Exception{
		super.useSpecial();
		attack();
		
	}
}
