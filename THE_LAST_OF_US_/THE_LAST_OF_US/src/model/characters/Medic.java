package model.characters;

import exceptions.*;

public class Medic extends Hero {
	
	public Medic(String name, int maxHp, int attackDmg, int maxActions){
		 super( name, maxHp, attackDmg , maxActions);
		
	 }
	
	
	
	public void useSpecial() throws Exception{
		super.useSpecial();
		Character c= getTarget();
		if(c instanceof Zombie) 
			throw new InvalidTargetException();
			
		if(getActionsAvailable() == 0)
			throw new NotEnoughActionsException();
		
		c.setCurrentHp(c.getMaxHp());
		setActionsAvailable(getActionsAvailable()-1);
	}
	
}
