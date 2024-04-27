package model.collectibles;

import java.awt.Point;
import engine.Game;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible{
	
	public Vaccine(){
		
	}
	
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);	
	}
	
	public void use(Hero h) {
		h.getVaccineInventory().remove(this);
		Game.zombies.remove(h.getTarget());
		Point p=h.getTarget().getLocation();
		int x=(int)p.getX();
		int y=(int)p.getY();
		int s= Game.availableHeros.size();
		int rand= (int)(Math.random()*(s));
		Hero h1= Game.availableHeros.remove(rand);
		Game.heros.add(h1);
		((CharacterCell)Game.map[x][y]).setCharacter(h1);
		h1.setLocation(new Point(x,y));
		Game.showAdj(h1);
	}
	
	public static void main(String[]args) {
		System.out.println("Heloo");
	}

}