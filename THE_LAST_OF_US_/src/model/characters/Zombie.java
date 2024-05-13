package model.characters;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Zombie extends Character {
	private static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		for(int i=0; i<Game.map.length; i++) {
			for(int j=0; j<Game.map[i].length; j++) {
				if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell)Game.map[i][j]).getCharacter() instanceof Hero
						&& isAdjacent(new Point(i,j))) {
					setTarget(((CharacterCell)Game.map[i][j]).getCharacter());
					break;
				}
			}
		}
		//if(getTarget()instanceof Zombie)
		//	setTarget(null);
		if(getTarget() == null )
			return;
		super.attack();
		setTarget(null);
	}

	public void defend(Character c) {
		super.defend(c);
	}
	
	/*public void onCharacterDeath(){
		super.onCharacterDeath();
		Game.zombies.remove(this);
		Game.spawnZombie(1);
	}*/
	
	

}

