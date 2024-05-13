package views;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ActionListener{
	
	public static int width=1100;
	public static int hight=800;
	
	public static JPanel menuPanel= new JPanel();
	public static JLabel menu= new JLabel();
	public static JButton start= new JButton();
	public static JButton quit= new JButton();
	
	public static JPanel guide= new JPanel();
	public static JTextPane textGuide= new JTextPane();
	public static JTextPane textGuide2= new JTextPane();
	public static JLabel back=new JLabel();
	public static JButton next=new JButton("next");
	
	public static JPanel selectPanel= new JPanel();
	public static Hero player;
	public static ImageIcon icon;
	public static JButton selectbtn;
	public static JLabel selecText=new JLabel();
	public static ArrayList<JButton> btns= new ArrayList<JButton>();
	public static ArrayList<JLabel> Charlabels= new ArrayList<JLabel>();
	public static ArrayList<JTextPane> texts= new ArrayList<JTextPane>();
	public static JPanel select;
	
	public static JPanel mapGrid= new JPanel();
	public static JButton PlayerBtn;
	public static ArrayList<Hero> heros;
	public static ArrayList<CharacterCell> array;
	public static ArrayList<JButton> labels; 
	public static ImageIcon currIcon;
	public static JButton[][] mapBtns= new JButton[15][15];
	
	public static JPanel side= new JPanel();
	public static ImageIcon z= new ImageIcon();
	public static JPanel rem=new JPanel();
	//public static JPanel remHero= new JPanel();
	public static ArrayList<Hero> remHeros= new ArrayList<Hero>();
	public static JButton herobutton;
	public static ArrayList<JButton> remBtns= new ArrayList<JButton>();
	public static ArrayList<Hero> k= new ArrayList<Hero>();
	public static JTextPane heroText= new JTextPane();
	public static JLabel heroLabel= new JLabel();
	public static JPanel butts= new JPanel();
	public static JPanel curr= new JPanel();
	public static JPanel sideText=new JPanel();
	public static JTextPane text= new JTextPane();
	public static JButton up= new JButton();
	public static JButton down= new JButton();
	public static JButton left= new JButton();
	public static JButton right= new JButton();
	public static JButton attack= new JButton();
	public static JButton special= new JButton();
	public static JButton cure= new JButton();
	public static JButton endTurn=new JButton();
//	public static JLabel bomb= new JLabel();
	
	public static JPanel win= new JPanel();
	public static JPanel gameOver= new JPanel();
	public static JLabel losemsg= new JLabel();
	public static JLabel winmsg= new JLabel();
	public static JButton end= new JButton();

	public GameFrame() throws IOException{
		
	//............... GameFrame .........................	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,-7, width+10, hight+30);
		setLayout(null);
		setBackground(Color.black);
		//addMouseListener(selectionPanel);
		//addKeyListener(gamePanel);
		setResizable(false);
		//this.addKeyListener(this);
		ImageIcon meow = new ImageIcon("mewo.png");
		this.setIconImage(meow.getImage());
		this.setTitle("The Last Of Us (but it really isnt)");
		win.setBackground(new Color(0x544a46));
		win.setBounds((width/2)-250, (hight/2)-200, 450, 350);
		win.setLayout(null);
		
		gameOver.setBackground(new Color(0x544a46));
		gameOver.setBounds((width/2)-250, (hight/2)-200, 450, 350);
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
		//win.add(end);
		gameOver.setVisible(false);
		win.setVisible(false);
		
		//............... menu .........................
		menuPanel.setLayout(null);
		menuPanel.setBounds(0,0,width,hight );
		menuPanel.setBackground(null);
		menu.setBounds(0,0,width,hight);
		start.addActionListener(this);
		BufferedImage img  = ImageIO.read(new File("background3.png"));
		Image tmp = img.getScaledInstance(width,hight,Image.SCALE_SMOOTH);
		ImageIcon background= new ImageIcon(tmp);
		//loadBtns();
	//	loadMenu();
		menu.setIcon(background);
		menuPanel.setVisible(true);
		loadMenu();
		this.add(menuPanel);
		//............... guide .........................
		guide.setLayout(null);
		guide.setBackground(new Color(0x6c635e));
		guide.setBounds(100,100,width-200,hight-200);
		
		next.addActionListener(this);
		startGuide();
		//guide.setVisible(false);
		
		//............... selection .........................
		selectPanel.setBackground(new Color(0x3d253b));
		selectPanel.setBounds(0,0,width,hight);
		selectPanel.setLayout(null);
		selectPanel.setBackground(null);
		Game.loadHeroes("Heroes.csv");
		startSelec();
		selectPanel.setVisible(false);
		
		//............... grid .........................
		mapGrid.setBackground(new Color(0x3d253b));
		mapGrid.setBounds(0, 0, (GameFrame.hight), (GameFrame.hight));
		mapGrid.setLayout(new GridLayout(15,15,0,0));
		//startGrid();
		mapGrid.setVisible(false);
		
		//............... side .........................
		side.setLayout(null);
		side.setBounds(width-300,0,300,GameFrame.hight);
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
		
		//adding stuff
		
		back.setBounds(0,0,width,hight);
		BufferedImage img1  = ImageIO.read(new File("background3.png"));
		Image tmp1 = img1.getScaledInstance(width,hight,Image.SCALE_SMOOTH);
		ImageIcon background1= new ImageIcon(tmp1);
		back.setIcon(background1);
		this.setContentPane(back);
		this.add(menuPanel);
		this.add(guide);
		this.add(selectPanel);
		this.add(mapGrid);
		this.add(side);
		this.add(gameOver);
		this.add(win);
		setVisible(true);
		
	}
	
	
	public static void loadMenu() throws IOException {
		//menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(0x544a46));
		start.setBounds(((width-325)/2)-2, 375, 325, 88);
		BufferedImage img  = ImageIO.read(new File("startBtn4.png"));
		Image tmp = img.getScaledInstance(325,88,Image.SCALE_SMOOTH);
		ImageIcon startBtn= new ImageIcon(tmp);
		//ImageIcon startBtn= new ImageIcon("startBtn3.png");
		start.setIcon(startBtn);
		//start.setFocusable(false);
		start.setBorderPainted(false); 
		start.setContentAreaFilled(false); 
		start.setFocusPainted(false); 
		start.setOpaque(true);
		start.setBorder(null);
		start.setBackground(null);
		
		start.setIcon(startBtn);
		//start.setVisible(true);
		menu.setBackground(new Color(0x544a46));
		menu.setBackground(null);
		menu.setOpaque(true);
		menuPanel.add(menu);
		menuPanel.add(start);
		start.setVisible(true);
		menuPanel.setVisible(true);
		
	}
	
	public static void startGuide() throws IOException {
		
		//guide.setForeground(new ImageIcon());
		//add(back);
		
		String s="  welcome to our very scuffed game!!"+"\n"
		+"  go on a (very short) adventure to cure what was supposed to be zombies but skeletones"+"\n" 
		+"  were cuter"+"\n"
		+"  anyway... you need to cure as many 'zombies' as you can turning them into heros"+"\n"
		+"  you can use"
		+"  to build your little community and survive the apocalypse"+"\n"
		+"  (i know i pselled that worng)"+"\n"
		+"  when a hero or zombie attacks their target defends with half their attack dmg"+"\n"
		+"  all zombies attack an adjacent hero when you end your turn (pretend we animated that)"+"\n"
		+"  there are 3 types of heros with a special action each"+"\n"
		;
		String s2="           medic: can heal any hero"+"\n"+"                including themself"+"\n"+"\n"
				+"           Fighter: beats up zombies for"+"\n"+"                 no action points "+"\n"+"\n"
				+"           Explorer: sees whole map for 1 turn "+"\n"+"                (yeah devs said you can do that normally)"+"\n"+"\n"
				;
		textGuide.setBounds(25,10,width-200,280);
		textGuide.setText(s);
		textGuide.setForeground(Color.white);
		textGuide.setBackground(null);
		textGuide.setEditable(false);
		//textGuide.setFocusable(false);
		textGuide.setOpaque(true);
		textGuide.setVisible(true);
		textGuide.setFont(new Font("MV Boli", Font.BOLD, 18));
		//back.add(text);
		textGuide2.setBounds(25,300,width-400,250);
		textGuide2.setText(s2);
		textGuide2.setForeground(Color.white);
		textGuide2.setBackground(null);
		textGuide2.setEditable(false);
		//textGuide.setFocusable(false);
		textGuide2.setOpaque(true);
		textGuide2.setVisible(true);
		textGuide2.setFont(new Font("MV Boli", Font.BOLD, 18));
		
		JLabel med= new JLabel();
		med.setBounds(15,5,53,53);
		med.setIcon(new ImageIcon("MedCrop.gif"));
		
		JLabel figh= new JLabel();
		figh.setBounds(15,90,53,53);
		figh.setIcon(new ImageIcon("FighCrop.gif"));
		
		JLabel exp= new JLabel();
		exp.setBounds(15,180,53,53);
		exp.setIcon(new ImageIcon("ExpCrop.gif"));
		
		textGuide2.add(med);
		textGuide2.add(figh);
		textGuide2.add(exp);
		
		next.setBounds(width-300, hight-270, 90, 50);
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
		select.setBounds(3,100,(GameFrame.width)-10,(GameFrame.hight)-175);
		select.setBackground(new Color(0x523b3e));
		select.setLayout(new GridLayout(2,4,10,40));
		select.setVisible(true);
		selectPanel.add(select);
		selectPanel.add(text);
		addHeroes();
		
		}
		
	public void addHeroes() throws IOException{
		btns.clear();
		Charlabels.clear();
		texts.clear();
		Game.heroes.clear();
		Game.availableHeroes.clear();
		Game.loadHeroes("Heroes.csv");
		for(int i=0; i<Game.availableHeroes.size(); i++){
			Hero h= Game.availableHeroes.get(i);		
			if(h instanceof Explorer)
				icon= new ImageIcon("CurrentExplorer.gif");
			if(h instanceof Fighter)
				icon= new ImageIcon("CurrentFighter.gif");		
			if(h instanceof Medic)
				icon= new ImageIcon("CurrentMedic.gif");
		//............... HeroButton .........................	
			JButton b= new JButton();
			b.setBackground(new Color(0x6c635e));
		//	b.setPreferredSize(new Dimension(35, 25));
			b.setText("Select");
			b.setForeground(Color.white);
			b.setFont(new Font("MV Boli", Font.BOLD, 20));
			//b.addMouseListener(this);
			b.setFocusable(false);
			b.addActionListener(this);
			btns.add(b);
			
		//............... HeroLabel .........................		
			JLabel l= new JLabel();
			l.setBackground(null);
			l.setHorizontalAlignment(JLabel.CENTER);
			//l.setBackground(null);
			l.setIcon(icon);
			//l.addMouseListener(this);
			l.setOpaque(true);
			Charlabels.add(l);
	
		//............... String ........................
			String s="Name: "+h.getName()+"\n"
					+"HP: "+h.getCurrentHp()+"\n"
					+"Dmg: "+h.getAttackDmg()+"\n"
					+"Actions: "+h.getActionsAvailable();
			
		//............... HeroText ........................	
			JTextPane text= new JTextPane();
			text.setText(s);
			text.setEditable(false);
			//text.setBounds(0, 0, 150, 50);
			text.setFocusable(false);
			//text.addMouseListener(this);
			text.setForeground(Color.white);
			text.setBackground(null);
			text.setFont(new Font("MV Boli", Font.BOLD, 16));
			texts.add(text);
					
		//............... HeroPanel .........................	
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setVisible(true);
			panel.setBackground(null);
			panel.add(Charlabels.get(i), BorderLayout.NORTH);
			panel.add(texts.get(i), BorderLayout.CENTER);
			panel.add(btns.get(i), BorderLayout.SOUTH);
					
		//............... HerosPanel .........................
			select.add(panel);
			select.setBackground(null);
		}
		}
		
	public static void setIconMap(int i,int j) throws IOException {
		if(i==0 && j==0) {
			//ImageIcon image=new ImageIcon("Tile7.png");
			BufferedImage img  = ImageIO.read(new File("Tile7.1.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);
			return;
			}
		if(i==14 && j==14) {
			BufferedImage img  = ImageIO.read(new File("Tile3.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);			
			return;
			}
		if(i==14 && j==0) {				
			BufferedImage img  = ImageIO.read(new File("Tile1.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);			
			return;
			}
		if(i==0 && j==14) {
			BufferedImage img  = ImageIO.read(new File("Tile9.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);
			return;
			}
		if(i==0) {				
			BufferedImage img  = ImageIO.read(new File("Tile8.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);			
			return;
			}
		if(j==0) {
			BufferedImage img  = ImageIO.read(new File("Tile6.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);							
			return;
			}
		if(j==14) {
			//ImageIcon image=new ImageIcon("Tile4.png");
			BufferedImage img  = ImageIO.read(new File("Tile4.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);
			return;
			}
		if(i==14) {
			//ImageIcon image=new ImageIcon("Tile2.png");
			BufferedImage img  = ImageIO.read(new File("Tile2.png"));
			Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
			ImageIcon tile= new ImageIcon(tmp);
			//mapBtns[i][j]=new JButton();
			mapBtns[i][j].setIcon(tile);
			return;
			}
		BufferedImage img  = ImageIO.read(new File("Tile5.png"));
		Image tmp = img.getScaledInstance(53,53,Image.SCALE_SMOOTH);
		ImageIcon tile= new ImageIcon(tmp);
		//mapBtns[i][j]=new JButton();
		mapBtns[i][j].setBackground(null);
		mapBtns[i][j].setIcon(tile);						
		}
	
	public static void setIcon2(int i,int j) throws IOException {
		if(Game.map[i][j].isVisible()) {
		if(Game.map[i][j] instanceof CharacterCell){
			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Explorer){
				ImageIcon icon=new ImageIcon("curExpCrop.gif");
				//mapBtns[i][j]=new JButton();
				
				((CharacterCell) Game.map[i][j]).setSafe(true);
				mapBtns[i][j].setBackground(new Color(0x3d253b));
				mapBtns[i][j].setIcon(icon);
				mapBtns[i][j].setVisible(true);
				 
				}		
			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Fighter){
				ImageIcon icon=new ImageIcon("curFighCrop.gif");
				//mapBtns[i][j]=new JButton();
				
				((CharacterCell) Game.map[i][j]).setSafe(true);				
				mapBtns[i][j].setBackground(new Color(0x3d253b));
				mapBtns[i][j].setIcon(icon);
				mapBtns[i][j].setVisible(true);
				 					
				}		
			if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Medic){
				ImageIcon icon=new ImageIcon("curMedCrop.gif");
				//mapBtns[i][j]=new JButton();
				
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
		for(int i=14; i>=0; i--) {
			for(int j=0; j<15; j++) {
				mapBtns[i][j]=new JButton();
				mapBtns[i][j].setFocusable(false);
				mapBtns[i][j].setBorder(null);
				setIconMap(i,j);
				
			}}
			}
				
	public static void loadmap() throws IOException {
		for(int i=0; i<Game.map.length; i++) {
			for(int j=0; j<Game.map[i].length; j++){
				setIcon2(i,j);
				if(Game.map[i][j] instanceof CharacterCell)
					array.add(((CharacterCell) Game.map[i][j]));
				mapGrid.add(mapBtns[i][j], i,j);
			}}
				}
			
	public void reloadMap() throws IOException {
		for(int i=0; i<Game.map.length; i++) {
			for(int j=0; j<Game.map[i].length; j++){
				setIconMap(i,j);
			}}
				
		for(int i=0; i<Game.map.length; i++) {
			for(int j=0; j<Game.map[i].length; j++){
				setIcon2(i,j);
			}}
	}
	
	public void addKey() {
		for(int i=0; i<mapBtns.length; i++) {
			for(int j=0; j<mapBtns[i].length; j++) {

			mapBtns[i][j].addActionListener(this);
		}}
	}
	
	public static void startGrid() throws IOException {
		//curr= GameFrame.selectionPanel.player;
		//mapGrid.setBackground(Color.white);
		Game.startGame(player);	
		heros= new ArrayList<>();
		array= new ArrayList<>();
		paintMap();
		loadmap();
		PlayerBtn= mapBtns[0][0];
		//GameFrame.add(this);		
		}
	
//	public void loadButts() {
//		if(player instanceof Explorer)
//			z= new ImageIcon("ExpAtkCrop.gif");
//		if(player instanceof Fighter)
//			z= new ImageIcon("FighAtkCrop.gif");
//		if(player instanceof Medic)
//			z= new ImageIcon("MedAtkCrop.gif");
//		
//		attack.setIcon(z);
//		butts.add(attack);
//		side.add(butts);
//	}

	public void startSide() throws IOException {

		rem.setBounds(2,0,300,(GameFrame.hight)/2);
		rem.setBackground(new Color(0x3d253b));
		rem.setLayout(new GridLayout(4,2));
		addremHeros();
		//for(int i=0; i<remHeros.size(); i++)
		//	rem.add(remHero);
		rem.setVisible(true);
		
		curr.setBounds(2,hight-197,300,100);
		curr.setLayout(null);
		curr.setBackground(new Color(0x3d253b));
		
		butts.setBounds(2,(hight/2)+3, 300,((hight/2)-200)-3);
		butts.setLayout(null);
		butts.setBackground(new Color(0x3d253b));
		
		
		sideText.setBounds(2,hight-94,300,100);
		text.setForeground(Color.white);
		text.setFont(new Font("MV Boli", Font.PLAIN, 16));
		sideText.setBackground(new Color(0x3d253b));
		text.setEditable(false);
		text.setBackground(null);
		sideText.add(text);
		
		//text.setForeground(Color.black);
		//curr.setBackground(Color.pink);
		//butts.setBackground(Color.green);
		
		up.setBounds(45,80,50,35);
		up.setIcon(new ImageIcon("uparrow3.png"));
		up.setBackground(null);
		up.setFocusable(false);
		up.setBorderPainted(false);
		
		down.setBounds(45,145,50,35);
		down.setIcon(new ImageIcon("downarrow3.png"));
		down.setBackground(null);
		down.setFocusable(false);
		down.setBorderPainted(false);
		
		left.setBounds(20,105,35,50);
		left.setIcon(new ImageIcon("leftarrow3.png"));
		left.setBackground(null);
		left.setFocusable(false);
		left.setBorderPainted(false);
		
		right.setBounds(85,105,35,50);
		right.setIcon(new ImageIcon("rightarrow3.png"));
		right.setBackground(null);
		right.setFocusable(false);
		right.setBorderPainted(false);
		
//		if(player instanceof Explorer)
//			z= new ImageIcon("ExpAtkCrop.gif");
//		if(player instanceof Fighter)
//			z= new ImageIcon("FighAtkCrop.gif");
//		if(player instanceof Medic)
//			z= new ImageIcon("MedAtkCrop.gif");
		
		attack.setBounds(10, 10, 53, 53);
		BufferedImage img1= ImageIO.read(new File("atkBtn.png"));
		Image tmp1= img1.getScaledInstance(53,53,Image.SCALE_SMOOTH);
		ImageIcon atkBtn= new ImageIcon(tmp1);
		attack.setIcon(atkBtn);
		attack.setBackground(new Color(0x393f4a));
		attack.setOpaque(true);
		attack.setVisible(true);
		
		special.setBounds(70, 10, 53, 53);
		special.setBackground(new Color(0x393f4a));
		special.setIcon(new ImageIcon("suppBtn.png"));
		
		cure.setBounds(130, 10, 53, 53);
		cure.setBackground(new Color(0x393f4a));
		cure.setIcon(new ImageIcon("vaccBtn.png"));
		
		endTurn.setBounds(180,150,90,35);
//		BufferedImage img= ImageIO.read(new File("next1.png"));
//		Image tmp= img.getScaledInstance(80,50,Image.SCALE_SMOOTH);
//		ImageIcon nextBtn= new ImageIcon(tmp);
//		endTurn.setIcon(nextBtn);
		endTurn.setText("End turn");
		endTurn.setMargin(new Insets(0,0,0,0));
		endTurn.setFont(new Font("MV Boli", Font.BOLD, 16));
		endTurn.setForeground(Color.white);
		endTurn.setBackground(new Color(0x393f4a));
		endTurn.setFocusable(false);
		//endTurn.setBorderPainted(false);
//		
//		bomb.setBounds(2,hight-(94+25),300,75);
//		bomb.setIcon(new ImageIcon("TrapCellWarning.gif"));
//		bomb.setVisible(true);
		
		butts.add(up);
		butts.add(down);
		butts.add(left);
		butts.add(right);
		butts.add(attack);
		butts.add(special);
		butts.add(cure);
		butts.add(endTurn);
		
		side.setBackground(Color.white);
		side.add(rem);
		side.add(curr);
		side.add(butts);
		side.add(sideText);
//		side.add(bomb);
	}
	
	public void loadCurr(){
		heroLabel.setIcon(null);
		heroText.setBounds(60, 10, 200, 200);
		String s= player.getName()+"\n"
				+"HP: "+ player.getCurrentHp()+"         "+"dmg: "+ player.getAttackDmg()+"\n"
				+"vaccines: "+player.getVaccineInventory().size()
				+"       "+"max actions: "+ player.getMaxActions()+"\n"
				+"supplies: "+player.getSupplyInventory().size()+"       "+"Actions: "+ player.getActionsAvailable();
				
		heroText.setText(s);
		heroText.setBackground(null);
		heroText.setFont(new Font("MV Boli", Font.BOLD, 12));
		heroText.setForeground(Color.white);
		heroText.setVisible(true);
		heroText.setEditable(false);
		heroText.setMargin(new Insets(5,5,5,5)); 
		//heroText.set
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
		//Game.heroes.remove(player);
		remHeros.clear();
		remBtns.clear();
		rem.removeAll();
		
		ArrayList<Hero> k= Game.heroes;
		for(int i=0; i<Game.heroes.size(); i++){
			if(Game.heroes.get(i).equals(player))
				continue;
			JButton heroLabel= new JButton();
			if(Game.heroes.get(i) instanceof Explorer)
				heroLabel.setIcon(new ImageIcon("curExpCrop.gif"));
			if(Game.heroes.get(i) instanceof Fighter)
				heroLabel.setIcon(new ImageIcon("curFighCrop.gif"));
			if(Game.heroes.get(i) instanceof Medic)
				heroLabel.setIcon(new ImageIcon("curMedCrop.gif"));
			heroLabel.setBackground(new Color(0x393f4a));
			heroLabel.setOpaque(true);
			heroLabel.setVisible(true);
			remBtns.add(heroLabel);
			//remBtns.get(i).addActionListener(this);
			
			String s="Name: "+Game.heroes.get(i).getName()+"\n"
					+"HP: "+Game.heroes.get(i).getCurrentHp()+"\n"
					+"Dmg: "+Game.heroes.get(i).getAttackDmg()+"\n"
					+"Actions: "+Game.heroes.get(i).getActionsAvailable();
			JTextPane remText= new JTextPane();
			//heroText= new JTextPane();
			remText.setText(s);
			remText.setFocusable(false);
			remText.setForeground(Color.white);
			remText.setBackground(null);
			remText.setOpaque(true);
			remText.setEditable(false);
			remText.setFont(new Font("MV Boli", Font.PLAIN, 12));
			remText.setVisible(true);
			
			JPanel remHero= new JPanel();
			remHero.setBackground(null);
			remHero.setVisible(true);
			remHero.setLayout(new BorderLayout());
			remHero.add(heroLabel, BorderLayout.WEST);
			remHero.add(remText, BorderLayout.CENTER);
			remHeros.add(Game.heroes.get(i));
			rem.add(remHero);
		}
		for(int i=0; i<remBtns.size(); i++) {
			remBtns.get(i).addActionListener(this);
		}
		side.add(rem);
		//Game.heroes.add(player);
		
	}
	public static void switchtoGuide() {
		menu.setVisible(false);
		//selectionPanel.setVisible(true);
		//setBackground(Color.black);
		guide.setVisible(true);
		//selectionPanel.add(guide);
	}
	
	public void keyStuff() {
		this.addKeyListener(new KeyAdapter(){
			 public void keyPressed(KeyEvent e) {
				 System.out.println("djkas");
				    int keyCode = e.getKeyCode();
				    if(keyCode==KeyEvent.VK_W) {
				    	System.out.println("djkas");
				    }
		}});
	}
	

	
	public static void win(){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e2) {
				//e2.printStackTrace();
			}
			text.setText("you winnn");
			//win.add(msg);
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
				//e2.printStackTrace();
			}
			text.setText("game over");
			mapGrid.setVisible(false);
			side.setVisible(false);
			//gameOver.add(msg);
			gameOver.add(end);
			gameOver.setVisible(true);
			
		}
	}
	public boolean inMap(Point p) {
		if(p.getX()<15 && p.getX()>=0 && p.getY()<15 && p.getY()>=0) 
			return true;
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		text.setText("");	
		if(e.getSource()== end) {
			gameOver.setVisible(false);
			win.setVisible(false);
			mapGrid.setVisible(false);
			side.setVisible(false);
			menuPanel.setVisible(true);
		}
		if(e.getSource()==start) {
			menuPanel.setVisible(false);
			guide.setVisible(true);
		}
		if(e.getSource()== next) {
			guide.setVisible(false);
			selectPanel.setVisible(true);
		}
		for(int i=0; i<Game.availableHeroes.size(); i++) {
			if(e.getSource()==btns.get(i)) {
				player=Game.availableHeroes.get(i);
				selectPanel.setVisible(false);
				//Game.startGame(player);
				try {
					startGrid();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				addKey();
				mapGrid.setVisible(true);
				loadCurr();
				addremHeros();
				side.add(curr);
				side.add(rem);
				side.setVisible(true);
			}
		}
		//ImageIcon icon=(ImageIcon) mapBtns[player.getLocation().x][player.getLocation().y].getIcon();
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
			//ImageIcon image= (ImageIcon) PlayerBtn.getIcon();
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
			//if(player.getTarget()!=null) {
			mapBtns[xTarget][yTarget].setIcon(zomAtked);
			//}
			loadCurr();
			addremHeros();	
			if(Game.checkWin()) { 
				win();
				return;
			}
			gameover();
		}
		if(e.getSource()== special) {
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
				//side.add(rem);
			} catch (InvalidTargetException | NotEnoughActionsException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				//System.out.println("blabla");
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
		for(int i=0; i<mapBtns.length; i++) {
			for(int j=0; j<mapBtns[i].length; j++) {
				if(e.getSource()==mapBtns[i][j] && Game.map[i][j] instanceof CharacterCell) {
					text.setText("target set");
					player.setTarget(((CharacterCell)Game.map[i][j]).getCharacter());
				}
//				if(e.getSource()==mapBtns[i][j] && Game.map[i][j] instanceof CharacterCell
//						&& ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero) {
//					text.setText("Hero selected");
//					int k=Game.heroes.indexOf(((CharacterCell) Game.map[i][j]).getCharacter());
//					player=Game.heroes.get(k);
//					addremHeros();
//					loadCurr();
//				}
				}}
		for(int i=0; i<remBtns.size(); i++) {
			if(e.getSource()== remBtns.get(i)) {
				text.setText("Hero selected");
				player=remHeros.get(i);
				addremHeros();
				loadCurr();
			}
		}
		}
	
	public static void main(String[]args) throws IOException{
		GameFrame frame=new GameFrame();
	}

}
