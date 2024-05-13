package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.*;

public interface Collectible {
	
	void pickUp(Hero h);
	
	void use(Hero h) ;
}