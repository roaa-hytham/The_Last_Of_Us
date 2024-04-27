package engine;

import java.io.BufferedReader;

import java.awt.Point;
import model.collectibles.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import exceptions.*;
import model.characters.*;
import model.world.*;

public class Game {
	public static Cell [][] map = new Cell[15][15];
	public static boolean isInAttic = false;
	public static ArrayList <Hero> availableHeros = new ArrayList<Hero>();
	public static ArrayList <Hero> heros =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	
	public static void loadHeros(String filePath)  throws IOException {		
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
			availableHeros.add(hero);
			line = br.readLine();
		}
		br.close();
	}

	public static String getAvailableHeroes() {
		String s="";
		for (int i = 0; i < availableHeros.size(); i++) 
			s=s+availableHeros.get(i);
		return s;
	}
	
	public static void initiateMap() {
		for(int i=0; i<Game.map.length; i++)
			for(int j=0; j<Game.map[i].length; j++)
				map[i][j]=new CharacterCell(null);	
	}
	
	public static void showAdj(Hero h) {
		int x=(int)h.getLocation().getX();
		int y=(int)h.getLocation().getY();
		map[x][y].setVisible(true);
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j]== null)
					map[i][j]=new CharacterCell(null);
				if(h.isAdjacent(new Point(i,j))) 
					map[i][j].setVisible(true);
			}
	}

	public static void startGame(Hero mc) {
		initiateMap();
		availableHeros.remove(mc);
		heros.add(mc);
		Game.map[0][0]= new CharacterCell(mc);
		mc.setLocation(new Point(0,0));
		spawnZombie(10);
		spawnSupply(5);
		spawnVaccine(5);
		spawnTrap(5);
		showAdj(mc);
	}
	
	public static void spawnSupply(int suppCount) {
		int i=0;
		boolean vis=false;
		while(i<suppCount) {
			int rand1=((int)(Math.random()*15));
			int rand2=((int)(Math.random()*15));
			Cell supp=Game.map[rand1][rand2];
			if(supp instanceof CharacterCell && !supp.isOccupied() ) {
				if(supp.isVisible()==true)
					vis=true;
				Supply sup=new Supply();
				Game.map[rand1][rand2]=new CollectibleCell(sup);
				if(vis==true)
					Game.map[rand1][rand2].setVisible(true);
				i++;
			}
		}
	}
	
	public static void spawnVaccine(int vacCount) {
		int i=0;
		boolean vis=false;
		while(i<vacCount) {
			int rand1= (int)(Math.random()*15);
			int rand2= (int)(Math.random()*15);
			Cell vacc=Game.map[rand1][rand2];
			if(vacc instanceof CharacterCell && !vacc.isOccupied() )  {
				if(vacc.isVisible()==true)
					vis=true;
				Vaccine vac=new Vaccine();
				Game.map[rand1][rand2]=new CollectibleCell(vac);
				if(vis==true)
					Game.map[rand1][rand2].setVisible(true);
				i++;
			}
		}
	}
	
	public static void spawnTrap(int trapCount) {
		int i=0;
		boolean vis=false;
		while(i<trapCount) {
			int rand1=((int)(Math.random()*15));
			int rand2=((int)(Math.random()*15));
			Cell trap=Game.map[rand1][rand2];
			if(trap instanceof CharacterCell && !trap.isOccupied() ) {
				if(trap.isVisible()==true)
					vis=true;
				Game.map[rand1][rand2]=new TrapCell();
				if(vis==true)
					Game.map[rand1][rand2].setVisible(true);
				i++;
			}
		}
	}
	
	public static void spawnZombie(int zCount) {
		int i=0;
		boolean vis=false;
		while(i < zCount) {
			int rand1=((int)(Math.random()*15));
			int rand2=((int)(Math.random()*15));
			Cell z=Game.map[rand1][rand2];
			if(z instanceof CharacterCell && !z.isOccupied() ) {
				if(z.isVisible()==true)
					vis=true;
				map[rand1][rand2].setOccupied(true);
				Zombie zom=new Zombie();
				zom.setLocation(new Point(rand1,rand2));
				map[rand1][rand2]=new CharacterCell(zom);
				zombies.add(zom);	
				if(vis==true)
					Game.map[rand1][rand2].setVisible(true);
				i++;
			}
		}
	}
	
	public static int vaccOnMap() {
		int vacNum=0;
		for(int i=0; i<Game.map.length; i++)
			for(int j=0; j<Game.map[i].length; j++)
				if(map[i][j] instanceof CollectibleCell && ((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine) 
					vacNum++;
		return vacNum;		
	}
	
	public static int vaccInv() {
		int vacNum=0;
		for(int i=0; i<heros.size(); i++)
			if(!(heros.get(i).getVaccineInventory().isEmpty()))
				vacNum=vacNum +heros.get(i).getVaccineInventory().size();
		return vacNum;
	}
	
	public static boolean checkWin() {
		int remainingVaccines = 0;
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				if (map[i][j] instanceof CollectibleCell
						&& ((CollectibleCell) map[i][j]).getCollectible() instanceof Vaccine)
					remainingVaccines++;
		
		for (Hero hero : heros)
			remainingVaccines += hero.getVaccineInventory().size();
		return heros.size() >= 5 && remainingVaccines == 0;
	}
	
	public static boolean checkGameOver() {
		if (heros.size() > 0) {
			for (int i = 0; i < map.length; i++)
				for (int j = 0; j < map[i].length; j++)
					if (map[i][j] instanceof CollectibleCell
							&& ((CollectibleCell) map[i][j]).getCollectible() instanceof Vaccine)
						return false;
			for (Hero hero : heros)
				if (hero.getVaccineInventory().size() > 0)
					return false;
		}
		return true;
	}
	
	public static void endTurn() throws InvalidTargetException, NotEnoughActionsException{
		zomEndTurn();
		if(zombies.size()<10)
			spawnZombie(1);
		clearMap();
		heroEndTurn(); 
	}
	
	public static void zomEndTurn() throws InvalidTargetException, NotEnoughActionsException{
		for(int i=0; i<zombies.size(); i++) {
			Zombie z=zombies.get(i);
			zombies.get(i).attack();
			if(z.getCurrentHp()==0)
				i--;
		}
	}
	public static void heroEndTurn() {
		for(int i=0; i<heros.size(); i++) {
			heros.get(i).setSpecialAction(false);
			heros.get(i).setActionsAvailable(heros.get(i).getMaxActions());
			heros.get(i).setTarget(null);
			showAdj(heros.get(i));
		}
	}
	
	public static void clearMap() {
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++) 
				map[i][j].setVisible(false);
	}
	
	public static void main(String[] args) throws InvalidTargetException, NotEnoughActionsException{
		Fighter f=new Fighter(" ",400,40,5);
		f.setLocation(new Point(0,0));
		startGame(f);
		System.out.println(map[0][1].isVisible());
		System.out.println(map[1][0].isVisible());
		System.out.println(map[0][0].isVisible());
		System.out.println(map[1][1].isVisible());
	}
	
}