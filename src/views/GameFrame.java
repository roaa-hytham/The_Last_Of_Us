package views;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import engine.Game;
import exceptions.*;
import model.characters.*;
import model.collectibles.*;
import model.world.*;

@SuppressWarnings("serial")
 class GameFrame extends JFrame implements ActionListener{
	 static int width= 900;
	 static int height= 650;
	 static JPanel menuPanel= new JPanel();
	 static JLabel menu= new JLabel();
	 static JButton start= new JButton();
	 static JPanel guide= new JPanel();
	 static JTextPane textGuide= new JTextPane();
	 static JTextPane textGuide2= new JTextPane();
	 static Hero player;
	 static JPanel mapGrid= new JPanel();
	 static JButton PlayerBtn;
	 static ArrayList<Hero> heros;
	 static ArrayList<CharacterCell> array;
	 static JButton next=new JButton("next");
	 static JButton[][] mapBtns= new JButton[15][15];
	 static JPanel side= new JPanel();
	 static JTextPane text= new JTextPane();
	 static JPanel win= new JPanel();
	 static JPanel gameOver= new JPanel();
	 static JButton end= new JButton();
	 JButton quit= new JButton();
	 JLabel back=new JLabel();
	 JPanel selectPanel= new JPanel();
	 ImageIcon icon;
	 JButton selectbtn;
	 JLabel selecText=new JLabel();
	 ArrayList<JButton> btns= new ArrayList<JButton>();
	 ArrayList<JLabel> Charlabels= new ArrayList<JLabel>();
	 ArrayList<JTextPane> texts= new ArrayList<JTextPane>();
	 JPanel select;
	 ArrayList<JButton> labels; 
	 ImageIcon currIcon;
	 ImageIcon z= new ImageIcon();
	 JPanel rem=new JPanel();
	 ArrayList<Hero> remHeros= new ArrayList<Hero>();
	 JButton herobutton;
	 ArrayList<JButton> remBtns= new ArrayList<JButton>();
	 ArrayList<Hero> k= new ArrayList<Hero>();
	 JTextPane heroText= new JTextPane();
	 JLabel heroLabel= new JLabel();
	 JPanel buttons= new JPanel();
	 JPanel curr= new JPanel();
	 JPanel sideText=new JPanel();
	 JButton up= new JButton();
	 JButton down= new JButton();
	 JButton left= new JButton();
	 JButton right= new JButton();
	 JButton attack= new JButton();
	 JButton special= new JButton();
	 JButton cure= new JButton();
	 JButton endTurn=new JButton();
	 JLabel losemsg= new JLabel();
	 JLabel winmsg= new JLabel();

	public GameFrame() throws IOException{
	//............... GameFrame .........................	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,-7, width+10, height+30);
		setLayout(null);
		setBackground(Color.black);
		setResizable(false);
		ImageIcon meow = new ImageIcon("mewo.png");
		setIconImage(meow.getImage());
		setTitle("The Last Of Us (but it really isnt)");
		win.setBackground(new Color(0x544a46));
		win.setBounds((width/2)-250, (height/2)-200, 450, 350);
		win.setLayout(null);

		gameOver.setBackground(new Color(0x544a46));
		gameOver.setBounds((width/2)-250, (height/2)-200, 450, 350);
		gameOver.setLayout(null);
		
		losemsg.setBounds(65,10,300,300);
		BufferedImage img2  = ImageIO.read(new File("over2.png"));
		Image tmp2 = img2.getScaledInstance(300,250,Image.SCALE_SMOOTH);
		ImageIcon over= new ImageIcon(tmp2);
		losemsg.setIcon(over);
		
		winmsg.setBounds(50,10,300,300);
		BufferedImage img4  = ImageIO.read(new File("win1.png"));
		Image tmp4 = img4.getScaledInstance(325,300,Image.SCALE_SMOOTH);
		ImageIcon winn= new ImageIcon(tmp4);
		winmsg.setIcon(winn);
		
		end.setBounds(370, 300, 80, 50);
		end.setBackground(new Color(0x544a46));
		end.setFocusable(false);
		end.setBorderPainted(false);
		BufferedImage img3= ImageIO.read(new File("next1.png"));
		Image tmp3= img3.getScaledInstance(80,50,Image.SCALE_SMOOTH);
		ImageIcon nextBtn= new ImageIcon(tmp3);
		end.setIcon(nextBtn);
		end.addActionListener(this);
		
		win.setVisible(true);
		gameOver.add(losemsg);
		gameOver.add(end);
		win.add(winmsg);
		gameOver.setVisible(false);
		win.setVisible(false);
		
		//............... menu .........................
		menuPanel.setLayout(null);
		menuPanel.setBounds(0,0,width,height );
		menuPanel.setBackground(null);
		menu.setBounds(0,0,width,height);
		start.addActionListener(this);
		BufferedImage img  = ImageIO.read(new File("background3.png"));
		Image tmp = img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
		ImageIcon background= new ImageIcon(tmp);
		menu.setIcon(background);
		menuPanel.setVisible(true);
		loadMenu();
		add(menuPanel);

		//............... guide .........................
		guide.setLayout(null);
		guide.setBackground(new Color(0x6c635e));
		guide.setBounds(85,85,width-175,height-165);
		next.addActionListener(this);
		startGuide();
		
		//............... selection .........................
		selectPanel.setBackground(new Color(0x3d253b));
		selectPanel.setBounds(0,0,width,height);
		selectPanel.setLayout(null);
		selectPanel.setBackground(null);
		Game.loadHeros("heros.csv");
		startSelec();
		selectPanel.setVisible(false);
		
		//............... grid .........................
		mapGrid.setBackground(new Color(0x3d253b));
		mapGrid.setBounds(0, 0, height, height);
		mapGrid.setLayout(new GridLayout(15,15,0,0));
		mapGrid.setVisible(false);
		
		//............... side .........................
		side.setLayout(null);
		side.setBounds(width-250,0,300,GameFrame.height);
		side.setBackground(new Color(0x3d253b));
		up.addActionListener(this);
		down.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
		attack.addActionListener(this);
		special.addActionListener(this);
		cure.addActionListener(this);
		endTurn.addActionListener(this);
		startSide();
		side.setVisible(false);
		
		back.setBounds(0,0,width,height);
		BufferedImage img1  = ImageIO.read(new File("background3.png"));
		Image tmp1 = img1.getScaledInstance(width,height,Image.SCALE_SMOOTH);
		ImageIcon background1= new ImageIcon(tmp1);
		back.setIcon(background1);
		setContentPane(back);
		add(menuPanel);
		add(guide);
		add(selectPanel);
		add(mapGrid);
		add(side);
		add(gameOver);
		add(win);
		setVisible(true);
	}
	
	public static void loadMenu() throws IOException {
		menuPanel.setBackground(new Color(0x544a46));
		start.setBounds(((width-325)/2)-3, 375, 325, 88);
		BufferedImage img  = ImageIO.read(new File("startBtn4.png"));
		Image tmp = img.getScaledInstance(325,88,Image.SCALE_SMOOTH);
		ImageIcon startBtn= new ImageIcon(tmp);
		start.setIcon(startBtn);
		start.setBorderPainted(false); 
		start.setContentAreaFilled(false); 
		start.setFocusPainted(false); 
		start.setOpaque(true);
		start.setBorder(null);
		start.setBackground(null);
		start.setIcon(startBtn);

		menu.setBackground(new Color(0x544a46));
		menu.setBackground(null);
		menu.setOpaque(true);
		menuPanel.add(menu);
		menuPanel.add(start);
		start.setVisible(true);
		menuPanel.setVisible(true);
	}
	
	public static void startGuide() throws IOException {
		String s="Welcome to our very scuffed game!!"+"\n"
		+"Go on a (very short) adventure to cure what was supposed to be zombies but skeletones"
		+ "were cuter anyway... you need to cure as many 'zombies' as you can turning them into heros you can use"
		+"  to build your little community and"
		+ "\nsurvive the apocalypse (i know i pselled that worng)"+"\n"
		+"When a hero or zombie attacks their target defends with half their attack dmg"+"\n"
		+"All zombies attack an adjacent hero when you end your turn\n(pretend we animated that)"+"\n"
		+"There are 3 types of heros with a special action each"+"\n"
		;
		String s2="           Medic: can heal any hero"+"\n"+"                including themself\n\n"
				+"           Fighter: beats up zombies for"+"\n"+"                 no action points\n\n"
				+"           Explorer: sees whole map for 1 turn "+"\n"+"                (yeah Devs said you can do that normally)"+"\n"+"\n"
				;
		textGuide.setBounds(25,10,width-200,280);
		textGuide.setText(s);
		textGuide.setForeground(Color.white);
		textGuide.setBackground(null);
		textGuide.setEditable(false);
		textGuide.setOpaque(true);
		textGuide.setVisible(true);
		textGuide.setFont(new Font("MV Boli", Font.BOLD, 15));
		textGuide2.setBounds(25,250,width-400,250);
		textGuide2.setText(s2);
		textGuide2.setForeground(Color.white);
		textGuide2.setBackground(null);
		textGuide2.setEditable(false);
		textGuide2.setOpaque(true);
		textGuide2.setVisible(true);
		textGuide2.setFont(new Font("MV Boli", Font.BOLD, 15));
		
		JLabel med= new JLabel();
		med.setBounds(15,5,53,53);
		med.setIcon(new ImageIcon("MedCrop.gif"));
		
		JLabel figh= new JLabel();
		figh.setBounds(15,85,53,53);
		figh.setIcon(new ImageIcon("FighCrop.gif"));
		
		JLabel exp= new JLabel();
		exp.setBounds(15,165,53,53);
		exp.setIcon(new ImageIcon("ExpCrop.gif"));
		
		textGuide2.add(med);
		textGuide2.add(figh);
		textGuide2.add(exp);
		
		next.setBounds(width-300, height-270, 90, 50);
		next.setBackground(null);
		next.setFocusable(false);
		next.setBorderPainted(false);
		BufferedImage img= ImageIO.read(new File("next1.png"));
		Image tmp= img.getScaledInstance(90,50,Image.SCALE_SMOOTH);
		ImageIcon nextBtn= new ImageIcon(tmp);
		next.setIcon(nextBtn);
		next.setVisible(true);

		guide.add(textGuide);
		guide.add(textGuide2);
		guide.add(next);
		guide.setVisible(false);
	}
	
	public void startSelec() throws IOException {
		selectPanel.setBackground(new Color(0x523b3e));
		selectPanel.removeAll();
		JLabel text= new JLabel();
		text.setOpaque(true);
		text.setBounds(10,0,500,100);
		text.setText("Select A Player");
		text.setForeground(Color.white);
		text.setFont(new Font("MV Boli", Font.BOLD, 40));
		text.setBackground(null);
		
	//............... HerosPanel .........................	
		select= new JPanel();
		select.setBounds(3,100,(GameFrame.width)-10,(GameFrame.height)-175);
		select.setBackground(new Color(0x523b3e));
		select.setLayout(new GridLayout(2,4,10,40));
		select.setVisible(true);
		selectPanel.add(select);
		selectPanel.add(text);
		addHeros();
	}
		
	public void addHeros() throws IOException{
		btns.clear();
		Charlabels.clear();
		texts.clear();
		Game.heros.clear();
		Game.availableHeros.clear();
		Game.loadHeros("heros.csv");
		for(int i=0; i<Game.availableHeros.size(); i++){
			Hero h= Game.availableHeros.get(i);		
			if(h instanceof Explorer)
				icon= new ImageIcon("selExp.gif");
			if(h instanceof Fighter)
				icon= new ImageIcon("selFigh.gif");		
			if(h instanceof Medic)
				icon= new ImageIcon("selMed.gif");
		//............... HeroButton .........................	
			JButton b= new JButton();
			b.setBackground(null);
			b.setBounds(60, 0, 90, 90);
			b.setForeground(Color.white);
			b.setFont(new Font("MV Boli", Font.BOLD, 19));
			b.setFocusable(false);
			b.addActionListener(this);
			btns.add(b);
			
		//............... HeroLabel .........................		
			JLabel l= new JLabel();
			l.setBackground(null);
			l.setBounds(60, 0, 90, 90);
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setIcon(icon);
			l.setOpaque(true);
			Charlabels.add(l);
	
		//............... String ........................
			String s="Name: "+h.getName()+"\n"
					+"HP: "+h.getCurrentHp()+"\n"
					+"Dmg: "+h.getAttackDmg()+"\n"
					+"Actions: "+h.getActionsAvailable()+"\n\n";
			
		//............... HeroText ........................	
			JTextPane text= new JTextPane();
			text.setText(s);
			text.setBounds(10, 100, 180, 120);
			text.setEditable(false);
			text.setFocusable(false);
			text.setForeground(Color.white);
			text.setBackground(null);
			text.setFont(new Font("MV Boli", Font.BOLD, 17));
			texts.add(text);
					
		//............... HeroPanel .........................	
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setVisible(true);
			panel.setBackground(null);
			panel.add(Charlabels.get(i));
			panel.add(texts.get(i));
			panel.add(btns.get(i));
					
		//............... HerosPanel .........................
			select.add(panel);
			select.setBackground(null);
		}
	}
		
	public static void setIconMap(int i,int j) throws IOException {
		if(i==0 && j==0) {
			BufferedImage img  = ImageIO.read(new File("Tile7.1.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);
			return;
		}
	
		if(i==14 && j==14) {
			BufferedImage img  = ImageIO.read(new File("Tile3.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);			
			return;
		}
		
		if(i==14 && j==0) {				
			BufferedImage img  = ImageIO.read(new File("Tile1.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);			
			return;
		}
	
		if(i==0 && j==14) {
			BufferedImage img  = ImageIO.read(new File("Tile9.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);
			return;
		}
	
		if(i==0) {				
			BufferedImage img  = ImageIO.read(new File("Tile8.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);			
			return;
		}
	
		if(j==0) {
			BufferedImage img  = ImageIO.read(new File("Tile6.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);							
			return;
		}
	
		if(j==14) {
			BufferedImage img  = ImageIO.read(new File("Tile4.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);
			return;
		}

		if(i==14) {
			BufferedImage img  = ImageIO.read(new File("Tile2.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			mapBtns[i][j].setIcon(tile);
			return;
		}
		
		BufferedImage img  = ImageIO.read(new File("Tile5.png"));
		Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
		ImageIcon tile= new ImageIcon(tmp);
		mapBtns[i][j].setBackground(null);
		mapBtns[i][j].setIcon(tile);						
	}
	
	public static void setIcon2(int i,int j) throws IOException {
		if(Game.map[i][j].isVisible()) {
		if(Game.map[i][j] instanceof CharacterCell){
			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Explorer){
				ImageIcon icon=new ImageIcon("curExpCrop.gif");
				((CharacterCell) Game.map[i][j]).setSafe(true);
				mapBtns[i][j].setBackground(new Color(0x3d253b));
				mapBtns[i][j].setIcon(icon);
				mapBtns[i][j].setVisible(true);
			}		
	
			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Fighter){
				ImageIcon icon=new ImageIcon("curFighCrop.gif");
				((CharacterCell) Game.map[i][j]).setSafe(true);				
				mapBtns[i][j].setBackground(new Color(0x3d253b));
				mapBtns[i][j].setIcon(icon);
				mapBtns[i][j].setVisible(true);
			}		

			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Medic){
				ImageIcon icon=new ImageIcon("curMedCrop.gif");
				((CharacterCell) Game.map[i][j]).setSafe(true);			
				mapBtns[i][j].setBackground(new Color(0x3d253b));
				mapBtns[i][j].setIcon(icon);
				mapBtns[i][j].setVisible(true);
			}		

			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie){						
				mapBtns[i][j].setBackground(new Color(0x3d253b));
				mapBtns[i][j].setIcon(new ImageIcon("zomCrop.gif"));
				mapBtns[i][j].setText(((CharacterCell) Game.map[i][j]).getCharacter().getName());
			}
		}						
			if (Game.map[i][j] instanceof CollectibleCell) {
				if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Supply){
					mapBtns[i][j].setBackground(new Color(0x3d253b));
					mapBtns[i][j].setIcon(new ImageIcon("suppCrop.gif"));			
				}	
		
				if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine){
					mapBtns[i][j].setBackground(new Color(0x3d253b));
					mapBtns[i][j].setIcon(new ImageIcon("vaccCrop.gif"));						
				}		
			}		
		}	
	}
	
	public static  void paintMap() throws IOException {
		for(int i=14; i>=0; i--)
			for(int j=0; j<15; j++) {
				mapBtns[i][j]=new JButton();
				mapBtns[i][j].setFocusable(false);
				mapBtns[i][j].setBorder(null);
				setIconMap(i,j);
			}
	}
				
	public static void loadmap() throws IOException {
		for(int i=0; i<Game.map.length; i++) {
			for(int j=0; j<Game.map[i].length; j++){
				setIcon2(i,j);
				if(Game.map[i][j] instanceof CharacterCell)
					array.add(((CharacterCell) Game.map[i][j]));
				mapGrid.add(mapBtns[i][j], i,j);
			}
		}
	}
			
	public void reloadMap() throws IOException {
		for(int i=0; i<Game.map.length; i++) 
			for(int j=0; j<Game.map[i].length; j++)
				setIconMap(i,j);
				
		for(int i=0; i<Game.map.length; i++) 
			for(int j=0; j<Game.map[i].length; j++)
				setIcon2(i,j);
	}
	
	public void addListener() {
		for(int i=0; i<mapBtns.length; i++) 
			for(int j=0; j<mapBtns[i].length; j++) 
				mapBtns[i][j].addActionListener(this);
	}
	
	public static void startGrid() throws IOException {
		Game.startGame(player);	
		heros= new ArrayList<>();
		array= new ArrayList<>();
		paintMap();
		loadmap();
		PlayerBtn= mapBtns[0][0];
	}
	
	public void startSide() throws IOException {
		rem.setBounds(0,0,300,324);
		rem.setBackground(new Color(0x3d253b));
		rem.setLayout(new GridLayout(4,2));
		addremHeros();
		rem.setVisible(true);
		
		curr.setBounds(0,478,300,100);
		curr.setLayout(null);
		curr.setBackground(new Color(0x3d253b));
		
		buttons.setBounds(0,327,300,148);
		buttons.setLayout(null);
		buttons.setBackground(new Color(0x3d253b));
		
		text.setForeground(Color.white);
		text.setFont(new Font("MV Boli", Font.PLAIN, 16));
		text.setEditable(false);
		text.setBackground(null);
		sideText.setBackground(new Color(0x3d253b));
		sideText.setBounds(0,581,300,90);
		sideText.add(text);
		
		attack.setBounds(10, 10, 45, 45);
		BufferedImage img1= ImageIO.read(new File("atkBtn.png"));
		Image tmp1= img1.getScaledInstance(attack.getWidth(),attack.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon atkBtn= new ImageIcon(tmp1);
		attack.setIcon(atkBtn);
		attack.setBackground(new Color(0x393f4a));
		attack.setOpaque(true);
		attack.setVisible(true);
		
		special.setBounds(70, 10, 45, 45);
		special.setBackground(new Color(0x393f4a));
		special.setIcon(new ImageIcon("suppBtn.png"));
		
		cure.setBounds(130, 10, 45, 45);
		BufferedImage img2= ImageIO.read(new File("vaccBtn.png"));
		Image tmp2= img2.getScaledInstance(cure.getWidth()-7,cure.getHeight()-7,Image.SCALE_SMOOTH);
		ImageIcon cureBtn= new ImageIcon(tmp2);
		cure.setBackground(new Color(0x393f4a));
		cure.setIcon(cureBtn);
		
		endTurn.setBounds(145,80,90,35);
		endTurn.setText("End turn");
		endTurn.setMargin(new Insets(0,0,0,0));
		endTurn.setFont(new Font("MV Boli", Font.BOLD, 16));
		endTurn.setForeground(Color.white);
		endTurn.setBackground(new Color(0x393f4a));
		endTurn.setFocusable(false);
		
		up.setBounds(45,60,43,28);
		BufferedImage img3= ImageIO.read(new File("uparrow3.png"));
		Image tmp3= img3.getScaledInstance(up.getWidth(),up.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon upBtn= new ImageIcon(tmp3);
		up.setIcon(upBtn);
		up.setBackground(null);
		up.setFocusable(false);
		up.setBorderPainted(false);
		
		down.setBounds(45,125,43,28);
		BufferedImage img4= ImageIO.read(new File("downarrow3.png"));
		Image tmp4= img4.getScaledInstance(down.getWidth(),down.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon downBtn= new ImageIcon(tmp4);
		down.setIcon(downBtn);
		down.setBackground(null);
		down.setFocusable(false);
		down.setBorderPainted(false);
		
		left.setBounds(20,85,28,43);
		BufferedImage img5= ImageIO.read(new File("leftarrow3.png"));
		Image tmp5= img5.getScaledInstance(left.getWidth(),left.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon leftBtn= new ImageIcon(tmp5);
		left.setIcon(leftBtn);
		left.setBackground(null);
		left.setFocusable(false);
		left.setBorderPainted(false);
		
		right.setBounds(85,85,28,43);
		BufferedImage img6= ImageIO.read(new File("rightarrow3.png"));
		Image tmp6= img6.getScaledInstance(right.getWidth(),right.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon rightBtn= new ImageIcon(tmp6);
		right.setIcon(rightBtn);
		right.setBackground(null);
		right.setFocusable(false);
		right.setBorderPainted(false);
		
		buttons.add(up);
		buttons.add(down);
		buttons.add(left);
		buttons.add(right);
		buttons.add(attack);
		buttons.add(special);
		buttons.add(cure);
		buttons.add(endTurn);
		
		side.setBackground(Color.white);
		side.add(rem);
		side.add(curr);
		side.add(buttons);
		side.add(sideText);
	}
	
	public void loadCurr(){
		heroLabel.setIcon(null);
		heroText.setBounds(60, 10, 200, 200);
		String s= player.getName()+"\n"
				+"HP: "+ player.getCurrentHp()
				+"         "
				+"dmg: "+ player.getAttackDmg()+"\n"
				+"vaccines: "+player.getVaccineInventory().size()
				+"      "+"Actions: "+ player.getActionsAvailable()+"\n"
				+"supplies: "+player.getSupplyInventory().size()
				+"      "
				+"Max: "+ player.getMaxActions();
				
		heroText.setText(s);
		heroText.setBackground(null);
		heroText.setFont(new Font("MV Boli", Font.BOLD, 12));
		heroText.setForeground(Color.white);
		heroText.setVisible(true);
		heroText.setEditable(false);
		heroText.setMargin(new Insets(5,5,5,5));
		
		heroLabel.setIcon(null);
		heroLabel.setBounds(5,10,53,53);
		heroLabel.setBackground(null);
		heroLabel.setOpaque(true);
		heroLabel.setVisible(true);
		
		if(player instanceof Explorer)
			heroLabel.setIcon(new ImageIcon("curExpCrop.gif"));
		if(player instanceof Fighter)
			heroLabel.setIcon(new ImageIcon("curFighCrop.gif"));
		if(player instanceof Medic)
			heroLabel.setIcon(new ImageIcon("curMedCrop.gif"));
		curr.add(heroLabel);
		curr.add(heroText);
	}
	
	public void addremHeros() {
		remHeros.clear();
		remBtns.clear();
		rem.removeAll();

		for(int i=0; i<Game.heros.size(); i++){
			if(Game.heros.get(i).equals(player))
				continue;
			JButton heroLabel= new JButton();
			if(Game.heros.get(i) instanceof Explorer)
				heroLabel.setIcon(new ImageIcon("ExpCrop.gif"));
			if(Game.heros.get(i) instanceof Fighter)
				heroLabel.setIcon(new ImageIcon("FighCrop.gif"));
			if(Game.heros.get(i) instanceof Medic)
				heroLabel.setIcon(new ImageIcon("MedCrop.gif"));
			heroLabel.setBackground(new Color(0x393f4a));
			heroLabel.setOpaque(true);
			heroLabel.setVisible(true);
			remBtns.add(heroLabel);
			
			String s="Name: "+Game.heros.get(i).getName()+"\n"
					+"HP: "+Game.heros.get(i).getCurrentHp()+"\n"
					+"Dmg: "+Game.heros.get(i).getAttackDmg()+"\n"
					+"Actions: "+Game.heros.get(i).getActionsAvailable();
			JTextPane remText= new JTextPane();
			remText.setText(s);
			remText.setFocusable(false);
			remText.setForeground(Color.white);
			remText.setBackground(new Color(0x3d253b));
			remText.setOpaque(true);
			remText.setEditable(false);
			remText.setFont(new Font("MV Boli", Font.PLAIN, 12));
			remText.setVisible(true);
			
			JPanel remHero= new JPanel();
			remHero.setBackground(new Color(0x3d253b));
			remHero.setVisible(true);
			remHero.setLayout(new BorderLayout());
			remHero.add(heroLabel, BorderLayout.WEST);
			remHero.add(remText, BorderLayout.CENTER);
			remHeros.add(Game.heros.get(i));
			rem.add(remHero);
		}
		
		for(int i=0; i<remBtns.size(); i++)
			remBtns.get(i).addActionListener(this);
		side.add(rem);
	}
	
	public static void switchtoGuide() {
		menu.setVisible(false);
		guide.setVisible(true);
	}
	
	public static void win(){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e2) {
		}
		text.setText("you winnn");
		mapGrid.setVisible(false);
		side.setVisible(false);
		win.add(end);
		win.setVisible(true);
	}

	public static void gameover(){
		if(Game.checkGameOver()) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e2) {
			}
			text.setText("game over");
			mapGrid.setVisible(false);
			side.setVisible(false);
			gameOver.add(end);
			gameOver.setVisible(true);
		}
	}
	
	public boolean inMap(Point p) {
		if(p.getX()<15 && p.getX()>=0 && p.getY()<15 && p.getY()>=0) 
			return true;
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		text.setText("");	
		if(e.getSource()==end) {
//			gameOver.setVisible(false);
//			win.setVisible(false);
//			mapGrid.setVisible(false);
//			side.setVisible(false);
//			menuPanel.setVisible(true);
			newGame();
		}
		if(e.getSource()==start) {
			menuPanel.setVisible(false);
			guide.setVisible(true);
		}
		if(e.getSource()== next) {
			guide.setVisible(false);
			selectPanel.setVisible(true);
		}
		
		for(int i=0; i<Game.availableHeros.size(); i++) {
			if(e.getSource()==btns.get(i)) {
				player=Game.availableHeros.get(i);
				selectPanel.setVisible(false);
				try {
					startGrid();
				} catch (IOException e1) {
				}
				addListener();
				mapGrid.setVisible(true);
				loadCurr();
				addremHeros();
				side.add(curr);
				side.add(rem);
				side.setVisible(true);
			}
		}
		
		if(e.getSource()==up) {
			boolean trap=false;
			if(inMap(new Point((player.getLocation().x)+1,(player.getLocation().y)))
					&&Game.map[(player.getLocation().x)+1][(player.getLocation().y)] instanceof TrapCell)
				trap=true;
			try {		
				player.move(Direction.UP);
			} catch (MovementException e1) {
				text.setText("cant move here");
				return;
			}
			catch(NotEnoughActionsException e2) {
				text.setText("not enough actions");
				return;
			}
			try {
				reloadMap();
			}
			catch( IOException e3) {
			}
			if(trap)
				text.setText("you fell into a hidden trap");
			System.out.println("up");
			PlayerBtn= mapBtns[player.getLocation().x][player.getLocation().y];
			loadCurr();
			addremHeros();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==down) {
			boolean trap=false;
			if(inMap(new Point((player.getLocation().x)-1,(player.getLocation().y)))
					&&Game.map[(player.getLocation().x)-1][(player.getLocation().y)] instanceof TrapCell)
				trap=true;
			try {
				player.move(Direction.DOWN);
			} catch (MovementException e1) {
				text.setText("cant move here");
				return;
			}
			catch(NotEnoughActionsException e2) {
				text.setText("not enough actions");
				return;
			}
			try {
				reloadMap();
			}
			catch( IOException e3) {
			}
			if(trap)
				text.setText("you fell into a hidden trap");
			System.out.println("down");
			PlayerBtn= mapBtns[player.getLocation().x][player.getLocation().y];
			loadCurr();
			addremHeros();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==left) {
			boolean trap=false;
			if(inMap(new Point(player.getLocation().x,(player.getLocation().y)-1))
					&&Game.map[player.getLocation().x][(player.getLocation().y)-1] instanceof TrapCell)
				trap=true;
			try {
				player.move(Direction.LEFT);
			} catch (MovementException e1) {
				text.setText("cant move here");
				return;
			}
			catch(NotEnoughActionsException e2) {
				text.setText("not enough actions");
				return;
			}
			try {
				reloadMap();
			}
			catch( IOException e3) {
			}
			if(trap)
				text.setText("you fell into a hidden trap");
			System.out.println("left");
			PlayerBtn= mapBtns[player.getLocation().x][player.getLocation().y];
			loadCurr();
			addremHeros();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==right) {
			boolean trap=false;
			if(inMap(new Point(player.getLocation().x,(player.getLocation().y)+1))
					&&Game.map[player.getLocation().x][(player.getLocation().y)+1] instanceof TrapCell)
				trap=true;
			try {
				player.move(Direction.RIGHT);
			} catch (MovementException e1) {
				text.setText("cant move here");
				return;
			}
			catch(NotEnoughActionsException e2) {
				text.setText("not enough actions");
				return;
			}
			try {
				reloadMap();
			}
			catch( IOException e3) {
			}
			if(trap)
				text.setText("you fell into a hidden trap");
			System.out.println("right");
			PlayerBtn= mapBtns[player.getLocation().x][player.getLocation().y];
			loadCurr();
			addremHeros();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==attack) {	
			try {
				reloadMap();
			} catch (IOException e3) {
			}
			int xTarget=0;
			int yTarget=0;
			boolean dead=false;
			if(player.getTarget()!= null) {
				xTarget=player.getTarget().getLocation().x;
				yTarget=player.getTarget().getLocation().y;
			if( player.getTarget().getCurrentHp() <= player.getAttackDmg())
				dead=true;
			}
			try {
				player.attack();
			} 
			catch( NotEnoughActionsException e2) {
				text.setText("not enough action points");
				return;
			}
			catch (InvalidTargetException | NullPointerException e1) {
				text.setText("select adjacent zombie tartget");
				return;
			}	
			PlayerBtn=mapBtns[player.getLocation().x][player.getLocation().y];
			if(player instanceof Explorer)
				PlayerBtn.setIcon(new ImageIcon("ExpAtkCrop.gif"));
			if(player instanceof Fighter)
				PlayerBtn.setIcon(new ImageIcon("FighAtkCrop.gif"));
			if (player instanceof Medic)
				PlayerBtn.setIcon(new ImageIcon("MedAtkCrop.gif"));

			ImageIcon zomAtked= new ImageIcon();
			if(dead)
				zomAtked=new ImageIcon("zomDeadCrop.gif");
			else {
				zomAtked=new ImageIcon("zomAtkedCrop.gif");
			}
			mapBtns[xTarget][yTarget].setIcon(zomAtked);
			loadCurr();
			addremHeros();	
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==special) {
			try {
				player.useSpecial();
			} catch (NoAvailableResourcesException e1) {
				text.setText("no supplies in inventory");
				return;
			}
			catch(NotEnoughActionsException e2) {
				text.setText("no enough actions");
				return;
			}
			catch(InvalidTargetException e3) {
				if(player instanceof Medic)
					text.setText("select adjacent Hero target");
				return;
			}
			if(player instanceof Explorer) {
				try {
					reloadMap();
				}
				catch(IOException e4) {
				}
			}
			text.setText("special used");
			loadCurr();
			if(player instanceof Medic)
				addremHeros();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==cure) {
			try {
				player.cure();
			}  catch (NoAvailableResourcesException e1) {
				text.setText("no vaccines in inventory");
				return;
			}
			catch(NotEnoughActionsException e2) {
				text.setText("no enough actions");
				return;
			}
			catch(InvalidTargetException e3) {
				text.setText("select adjacent target");
				return;
			}
			try {
				reloadMap();
			}
			catch(IOException e4) {
			}
			text.setText("Zombie cured");
			addremHeros();
			loadCurr();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		if(e.getSource()==endTurn) {
			try {
				Game.endTurn();
			} catch (InvalidTargetException | NotEnoughActionsException e1) {
			}
			try {
				reloadMap();
			}
			catch(IOException e2) {
			}
			addremHeros();
			loadCurr();
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		
		for(int i=0; i<mapBtns.length; i++)
			for(int j=0; j<mapBtns[i].length; j++)
				if(e.getSource()==mapBtns[i][j] && Game.map[i][j] instanceof CharacterCell) {
					text.setText("target set");
					player.setTarget(((CharacterCell)Game.map[i][j]).getCharacter());
				}
		for(int i=0; i<remBtns.size(); i++)
			if(e.getSource()== remBtns.get(i)) {
				text.setText("Hero selected");
				player=remHeros.get(i);
				addremHeros();
				loadCurr();
			}
	}
	
	public void newGame() {
		dispose();
		try {
			new GameFrame();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public static void main(String[]args) throws IOException{
		new GameFrame();
	}
}