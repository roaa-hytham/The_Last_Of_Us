package model.characters;

import exceptions.*;
import model.characters.Character;

public class Medic extends Hero {
	
	public Medic(String name, int maxHp, int attackDmg, int maxActions){
		 super( name, maxHp, attackDmg , maxActions);
		
	 }
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		super.attack();
	}
	public void defend(Character c) {
		super.defend(c);
	}
	
	public void useSpecial() throws NoAvailableResourcesException,NotEnoughActionsException,InvalidTargetException{
		if(getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException();
		if(getTarget() == null)
			throw new InvalidTargetException();
		if(getTarget() instanceof Zombie) 
			throw new InvalidTargetException();
		if(!isAdjacent(getTarget().getLocation()) && !(getTarget().getLocation().equals(getLocation())))
			throw new InvalidTargetException();
		
		super.useSpecial();
		getTarget().setCurrentHp(getTarget().getMaxHp());
	}
	
}