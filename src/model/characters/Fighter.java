package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero {
	
	public Fighter(String name, int maxHp, int attackDmg, int maxActions){
		super(name, maxHp , attackDmg , maxActions);
	}
	
	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
		if(getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException();
		if(this.getTarget() == null)
			throw new InvalidTargetException();
		super.useSpecial();
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		super.attack();
	}
	public void defend(Character c) {
		super.defend(c);
	}
}