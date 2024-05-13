package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.*;

public class Supply implements Collectible{
	
	public Supply(){
		
	}
	
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);	
	}
	
	public void use(Hero h) {
		h.getSupplyInventory().remove(0);
	}

}