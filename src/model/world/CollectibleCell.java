package model.world;

import model.collectibles.*;

public class CollectibleCell extends Cell{
	private Collectible collectible;

	public CollectibleCell(Collectible collectible) {
		super();
		this.collectible=collectible;
		if (collectible != null)
			setOccupied(true);
	}
	
	public Collectible getCollectible() {
		return collectible;
	}
	
}