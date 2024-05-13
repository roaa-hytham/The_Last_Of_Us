package model.world;

public class TrapCell extends Cell{
	private int trapDamage;
	
	public TrapCell() {
		super();
		trapDamage=((int)(Math.random()*3)*10)+10;
	}

	public int getTrapDamage() {
		return trapDamage;
	}
	
	/*public static void main(String[]args) {
		TrapCell t= new TrapCell();
		TrapCell t1=new TrapCell();
		TrapCell t2=new TrapCell();
		TrapCell t3=new TrapCell();
		TrapCell t4=new TrapCell();
		System.out.println(t.getTrapDamage());
		System.out.println(t1.getTrapDamage());
		System.out.println(t2.getTrapDamage());
		System.out.println(t3.getTrapDamage());
		System.out.println(t4.getTrapDamage());
	}*/

}
