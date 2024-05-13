package model.collectibles;

import model.characters.Hero;
import engine.Game;

public class Vaccine implements Collectible{
	
	public Vaccine(){
		
	}
	
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);	
		//Game.vCount--;
	}
	
	public void use(Hero h) {
		h.getVaccineInventory().remove(this);	
	}

}
