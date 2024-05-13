package model.world;

import model.characters.Character;

public class CharacterCell extends Cell{
	private Character character;
	private boolean isSafe;
	
	public CharacterCell(Character character) {
		super();
		this.character=character;
		if (character != null)
			setOccupied(true);
	}
	
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character=character;
	}
	public boolean isSafe() {
		return isSafe;
	}
	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
	
	/*public static void main (String[]args) {
		Medic m=new Medic("ellie",40,40,10);
		CharacterCell c=new CharacterCell(m);
		System.out.println(c.character);
	}*/
	
}