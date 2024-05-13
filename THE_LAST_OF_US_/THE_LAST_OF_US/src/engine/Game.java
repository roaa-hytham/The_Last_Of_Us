package engine;

import java.io.BufferedReader;
import model.world.*;
import model.characters.*;
import model.collectibles.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;

public class Game {
	public static Cell [][] map ;
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	//public static int vCount;
	
	public static void loadHeroes(String filePath)  throws IOException {		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
		}
		br.close();
	}
	
	

	public static String getAvailableHeroes() {
		String s="";
		for (int i = 0; i < availableHeroes.size(); i++) 
			s=s+availableHeroes.get(i);
		return s;
	}

	public static void startGame(Hero mc) {
		spawnSupply(5);
		spawnVaccine(5);
		spawnTrap(5);
		spawnZombie(10);
		CharacterCell hero= new CharacterCell(mc);
		Game.map[0][0]=hero;
		heroes.add(mc);
	}
	
	public static void spawnSupply(int suppCount) {
		while(suppCount < 5) {
			int rand1=((int)(Math.random()*15)+1);
			int rand2=((int)(Math.random()*15)+1);
			Cell supp=Game.map[rand1][rand2];
			if(!supp.isOccupied()) {
				supp.setOccupied(true);
				Supply sup=new Supply();
				CollectibleCell c=new CollectibleCell(sup);
				supp=c;
				suppCount++;
				}
			}
	}
	
	public static void spawnVaccine(int vacCount) {
		while(vacCount < 5) {
			int rand1=((int)(Math.random()*15)+1);
			int rand2=((int)(Math.random()*15)+1);
			Cell vacc=Game.map[rand1][rand2];
			if(!vacc.isOccupied()) {
				vacc.setOccupied(true);
				Vaccine vac=new Vaccine();
				CollectibleCell c=new CollectibleCell(vac);
				vacc=c;
				vacCount++;
				//vCount++;
				}
			}
	}
	
	public static void spawnTrap(int trapCount) {
		while(trapCount < 5) {
			int rand1=((int)(Math.random()*15)+1);
			int rand2=((int)(Math.random()*15)+1);
			Cell trap=Game.map[rand1][rand2];
			if(!trap.isOccupied()) {
				trap.setOccupied(true);
				TrapCell c=new TrapCell();
				trap=c;
				trapCount++;
				}
			}
	}
	
	public static void spawnZombie(int zCount) {
		while(zCount < 10) {
			int rand1=((int)(Math.random()*15)+1);
			int rand2=((int)(Math.random()*15)+1);
			Cell z=Game.map[rand1][rand2];
			if(!z.isOccupied()) {
				z.setOccupied(true);
				Zombie zom=new Zombie();
				CharacterCell c=new CharacterCell(zom);
				z=c;
				zombies.add(zom);
				zCount++;
				}
			}
	}
	
	//((int)(Math.random()*3)*10)+10

	public static boolean checkWin() {
		if(zombies.isEmpty() && heroes.size() >= 5 )
			return true;
		return false;
	}
	
	public static boolean checkGameOver() {
		if(!zombies.isEmpty() && (availableHeroes.size()+heroes.size()<5))
				return true;
		return false;
	}
	
	public static void endTurn() throws Exception{
		for(int i=0; i<zombies.size(); i++) {
			zombies.get(i).attack();
		}
		for(int i=0; i<heroes.size(); i++) {
			Hero h=heroes.get(i);
			h.setActionsAvailable(h.getMaxActions());
			h.setTarget(null);
			h.setSpecialAction(false);
			spawnZombie(1);
		}
		
	}
	
}
