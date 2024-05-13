package model.world;

public  abstract class Cell {
	private boolean isVisible;
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
	

	
	

}