package model.characters;

import java.awt.Point;
import engine.Game;
import exceptions.InvalidTargetException;

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;
	
	public Character() {
		
	}
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) {
			this.currentHp = 0;
			onCharacterDeath();
		}
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public boolean isAdjacent(Character c) {
		Point you= getLocation();
		Point enemy= c.getLocation();
		double youX= you.getX();
		double youY= you.getY();
		double enemyX= enemy.getX();
		double enemyY= enemy.getY();
		if(Math.abs(enemyX-youX) == 1 || Math.abs(enemyY - youY) == 1)
			return true;
		return false;
	}
	
	
	public void attack() throws Exception{
		if((this instanceof Zombie) && (getTarget() instanceof Zombie))
			throw new InvalidTargetException();
		if((this instanceof Hero) && (getTarget() instanceof Hero))
			throw new InvalidTargetException();
		if(! isAdjacent(getTarget()))
			throw new InvalidTargetException();
		attackHelper(getAttackDmg());
		getTarget().defend(this);
	}
	
	public void attackHelper(int x){
		getTarget().setCurrentHp(getTarget().getCurrentHp()-x);
	//	if(getTarget().getCurrentHp() == 0)
	//		getTarget().onCharacterDeath();
	//		already done in setCurrentHp(int x);
	}

	public void defend(Character c) throws Exception{
	//	if(getCurrentHp() == 0)
	//		onCharacterDeath();
	//		already done in setCurrentHp(int x);
		setTarget(c);
		attackHelper(getAttackDmg()/2);
	}
	
	public void onCharacterDeath(){
		if(this instanceof Hero) {
			Game.availableHeroes.remove(this);
			Game.heroes.remove(this);
		}
		if(this instanceof Zombie)
			Game.zombies.remove(this);
	}
	
	
}











