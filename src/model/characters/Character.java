package model.characters;

import java.awt.Point;
import engine.Game;
import exceptions.*;
import model.world.CharacterCell;

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
		}
		else {
			if(currentHp > maxHp) 
				this.currentHp = maxHp;
			else 
				this.currentHp = currentHp;
		}
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public boolean isAdjacent(Point enemy) {
		Point you= this.location;
		int youX= (int) you.getX();
		int youY= (int) you.getY();
		int enemyX= (int) enemy.getX();
		int enemyY= (int) enemy.getY();
		if((Math.abs(enemyY - youY)==1 || Math.abs(enemyX-youX)==1 ) && (Math.abs(enemyX-youX)+ Math.abs(enemyY - youY)) <=2)
			return true;
		return false;
	}
	
	public boolean inMap(Point p) {
		if(p.getX()<15 && p.getX()>=0 && p.getY()<15 && p.getY()>=0) 
			return true;
		return false;
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		this.target.setCurrentHp((this.target.getCurrentHp())-attackDmg);
		this.target.defend(this);
		if(this.currentHp ==0)
			this.onCharacterDeath();
		if(target.currentHp==0) {
			target.onCharacterDeath();
			this.setTarget(null);
		}
	}

	public void defend(Character c){
		setTarget(c);
		target.setCurrentHp((target.getCurrentHp())-(attackDmg/2));
		
	}
	
	public void onCharacterDeath(){
		Point p=location;
		int x=(int)p.getX();
		int y=(int)p.getY();
		Game.map[x][y]= new CharacterCell(null);
		if(this instanceof Hero) {
			Game.heros.remove(this);
		}
		if(this instanceof Zombie) {
			Game.zombies.remove(this);
			Game.spawnZombie(1);
		}
	}
	
	
}










