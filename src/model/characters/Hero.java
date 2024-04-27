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
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		if(!(this instanceof Fighter && specialAction == true)) {
			if(actionsAvailable == 0)
				throw new NotEnoughActionsException();
			actionsAvailable--;
		}
		if(getTarget() == null)
			throw new InvalidTargetException();
		if(!getTarget().inMap(getTarget().getLocation()))
			throw new InvalidTargetException();
		if(!isAdjacent(getTarget().getLocation()))
			throw new InvalidTargetException();
		if(getTarget() instanceof Hero)
			throw new InvalidTargetException();
		super.attack();
	}
	
	public void defend(Character c) {
		super.defend(c);
	}
	
	public void move(Direction d) throws MovementException, NotEnoughActionsException{
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException();
		Point p= new Point(getLocation());
		int x=(int) p.getX();
		int y=(int) p.getY();
		switch(d) {
			case UP:
				p.setLocation(x+1,y); break;
			case DOWN:
				p.setLocation(x-1,y); break;
			case LEFT:
				p.setLocation(x,y-1); break;
			case RIGHT:
				p.setLocation(x,y+1); break;
			default:
				p.setLocation(p);
		}
		if(!canMove(p))
			throw new MovementException();
		int xNew=(int) p.getX();
		int yNew=(int) p.getY();
		//Cell c= Game.map[xNew][yNew];
		if(Game.map[xNew][yNew] instanceof CollectibleCell) {
			((CollectibleCell) Game.map[xNew][yNew]).getCollectible().pickUp(this);
			Game.map[xNew][yNew]=new CharacterCell(null);
		}
		if(Game.map[xNew][yNew] instanceof TrapCell) {
			this.setCurrentHp(this.getCurrentHp()- ((TrapCell)Game.map[xNew][yNew]).getTrapDamage());
			Game.map[xNew][yNew]=new CharacterCell(null);
		}
		if(this.getCurrentHp()==0) {
			this.onCharacterDeath();
			return;
		}
		if(!(x==xNew && y==yNew)){
			Game.map[x][y]= new CharacterCell(null);
			setLocation(p);
			showAdjMove();
		}
		actionsAvailable--;
		((CharacterCell)Game.map[xNew][yNew]).setCharacter(this);
	}
	
	public boolean canMove(Point p) {
		int x=(int)p.getX();
		int y=(int)p.getY();
		if( !inMap(p) || (Game.map[x][y] instanceof CharacterCell && ((CharacterCell)Game.map[x][y]).isOccupied())) 
			return false;
		return true;
	}
	
	public void showAdjMove() {
		Game.map[(int)getLocation().getX()][(int)getLocation().getY()].setVisible(true);
		for(int j=0; j<Game.map.length; j++) {
			for(int k=0; k<Game.map[j].length; k++) {
				if(isAdjacent(new Point(j,k))) {
					if(Game.map[j][k]==null)
						Game.map[j][k]= new CharacterCell(null);
					Game.map[j][k].setVisible(true);
			}
			}}
	}
	
	public void useSpecial() throws NoAvailableResourcesException,NotEnoughActionsException, InvalidTargetException{
		supplyInventory.get(0).use(this);
		specialAction= true;
	}              
	
	public void cure() throws InvalidTargetException, NotEnoughActionsException, NoAvailableResourcesException{
		if(vaccineInventory.isEmpty())
			throw new NoAvailableResourcesException();
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException();
		if(getTarget() instanceof Hero)
			throw new InvalidTargetException();
		if(getTarget() == null)
			throw new InvalidTargetException();
		if(!isAdjacent(getTarget().getLocation()))
			throw new InvalidTargetException();
		actionsAvailable--;
		vaccineInventory.get(0).use(this);
		this.setTarget(null);
	}
	
	/*public void onCharacterDeath(){
		super.onCharacterDeath();
		Game.heroes.remove(this);
	}*/
	
	
	
}