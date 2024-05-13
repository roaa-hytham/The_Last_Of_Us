package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.*;
import model.collectibles.*;
import model.world.*;

public abstract class Hero extends Character {
	private int actionsAvailable;
	private int maxActions;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	private boolean specialAction;
	
	public Hero(String name,int maxHp, int attackDmg, int maxActions) {
		super(name,maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.vaccineInventory = new ArrayList<Vaccine>();
		this.supplyInventory = new ArrayList<Supply>();
		this.specialAction = false;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}
	
	public void attack() throws Exception{
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException();
		super.attack();
		actionsAvailable--;
	}
	
	public void defend(Character c) throws Exception {
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException();
		super.defend(c);
		actionsAvailable--;
	}
	
	public boolean canMove(Point p) {
		int x=(int)p.getX();
		int y=(int)p.getY();
		if(Game.map[y][x] instanceof CharacterCell) 
			return false;
		if(p.getX()<15 && p.getX()>0 && p.getY()<15 && p.getY()>0) 
			return true;
		return false;
		
		/*for(int i=0; i< Game.map.length; i++) {
			for(int j=0; j< Game.map[i].length; j++) {
				if(Game.map[i][j].getLocation().equals(p))
					return true;
			}
		}
		return false;*/
	}
	
	public void move(Direction d) throws Exception{
		Point p= getLocation();
		int x=(int) p.getX();
		int y=(int) p.getY();
		switch(d) {
		case UP:
			p.setLocation(x,y+1);
		case DOWN:
			p.setLocation(x,y-1);
		case LEFT:
			p.setLocation(x-1,y);
		case RIGHT:
			p.setLocation(x+1,y);
		default: p.setLocation(p);
		}
		if(!canMove(p))
			throw new MovementException();
		setLocation(p);
		Cell c=Game.map[(int)p.getY()][(int)p.getX()];
		if(c instanceof CollectibleCell) {
			((CollectibleCell) c).getCollectible().pickUp(this);
			CharacterCell hero=new CharacterCell(this);
			c=hero;
		}
		if(c instanceof TrapCell) {
			this.setCurrentHp(this.getCurrentHp()- ((TrapCell)c).getTrapDamage());
			}
	}
	
	public void useSpecial() throws Exception{
		if(supplyInventory.isEmpty())
			throw new NoAvailableResourcesException();
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException();
		supplyInventory.remove(0).use(this);
		specialAction= true;
		actionsAvailable--;
	}              
	
	public void cure() throws Exception{
		if (vaccineInventory.isEmpty())
			throw new NoAvailableResourcesException();
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException();
		if(!isAdjacent(getTarget()) || getTarget() instanceof Hero)
			throw new InvalidTargetException();
		getTarget().onCharacterDeath();	
		Point p=getTarget().getLocation();
		int x=(int)p.getX();
		int y=(int)p.getY();
		((CharacterCell)Game.map[y][x]).setCharacter(Game.availableHeroes.remove(0));
		vaccineInventory.remove(0).use(this);
		actionsAvailable--;
		
		

	}
	
}
