package model.world;

import java.awt.Point;

import engine.Game;

public  abstract class Cell {
	private boolean isVisible;
	//private Point location;
	private boolean occupied;
	
	public Cell() {
		
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	

	/*public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Cell find(Point p) {
		for(int i=0; i< Game.map.length; i++) {
			for(int j=0; j< Game.map[i].length; j++) {
				if(Game.map[i][j].getLocation().equals(p))
					return Game.map[i][j];
			}
			}
		return null;
					
	}*/
	

}
