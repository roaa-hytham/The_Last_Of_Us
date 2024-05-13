package model.collectibles;

import java.awt.Point;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible{
	
	public Vaccine(){
		
	}
	
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);	
		//Game.vCount++;
	}
	
	public void use(Hero h) {
		h.getVaccineInventory().remove(this);
		Game.zombies.remove(h.getTarget());
		Point p=h.getTarget().getLocation();
		int x=(int)p.getX();
		int y=(int)p.getY();
		int s= Game.availableHeroes.size();
		int rand= (int)(Math.random()*(s));
		Hero h1= Game.availableHeroes.remove(rand);
		Game.heroes.add(h1);
		((CharacterCell)Game.map[x][y]).setCharacter(h1);
		h1.setLocation(new Point(x,y));
		Game.showAdj(h1);
	}

}