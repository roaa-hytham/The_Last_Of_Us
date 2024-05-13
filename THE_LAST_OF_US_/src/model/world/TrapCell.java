package model.world;

public class TrapCell extends Cell{
	private int trapDamage;
	
	public TrapCell() {
		super();
		trapDamage=((int)(Math.random()*3)*10)+10;
		setOccupied(true);
	}

	public int getTrapDamage() {
		return trapDamage;
	}
	

}