package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Character;
import entity.Entity;
import entity.Item;
import entity.NPC;
import entity.NPC_Dialogue;
import entity.NPC_SellingItem;
import entity.Player;
import entity.Projectile;
import map.Map;

import monster.Monster;
import monster.ChuDongTanCong;
import monster.MonsterCube;
import map.Teleport;
import java.util.*;
public class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	private ProcessFrontBehindEntity processor;
	final int originalTileSize = 16; // 16 x 16 
	final int scale  = 3;
	public final int tileSize = originalTileSize * scale; //48 x 48
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	public Teleport[][] teleportList = new Teleport[4][10];
	public ArrayList<Projectile> projectileList = new ArrayList<>();
	public int num_CurrentMap = 1;
	public int maxMap = 4;
	public Map currentMap;
	public Map[] maps = new Map[maxMap];
	public Item obj[][] = new Item[4][20];
	public Monster[][] monster = new Monster[4][20];
	public NPC [][] npc = new NPC[4][20];
//	public Projectile projectile[] = new Projectile[20];
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	Thread gameThread;
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public CollisionChecker cChecker;
	public ChuDongTanCong quaiVatTanCong;
	public boolean can_touch = true;
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int storeState = 7;
	public final int transitionState = 8;
	public final int tradeState = 9;
	public int tempMap;
	public int tempCol;
	public int tempRow;
	private final Map MAP01 = new Map(this, "/map/layer0.txt", "/map/layer1.txt");
	private final Map MAP02 = new Map(this, "/map/map02.txt", "/map/Map02_layer02.txt");
	private final Map MAP03 = new Map(this, "/map/Map03.txt", "/map/Map03_layer02.txt");
	int FPS = 60;
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxWorldHeight = maxWorldRow * tileSize;
	public final int maxWorldWidth = maxWorldRow * tileSize;
	public boolean testMode = false; 
	public Player player = new Player(this, keyH);
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.cChecker = new CollisionChecker(this);
		this.quaiVatTanCong = new ChuDongTanCong(this);
		maps[1] = MAP01;
		maps[2]= MAP02;
		maps[3] = MAP03;
		currentMap = maps[1];
		/*
		 * teleportList.add(new Teleport( 1, 9, 11, // từ Map 1 tại tile (4,42) 2, 23,
		 * 23 // sang Map 2 tile (30,9) )); teleportList.add(new Teleport( 2, 24, 24, //
		 * từ Map 2 tile (30,10) 1, 9, 10 // sang Map 1 tile (42,2) ));
		 * teleportList.add(new Teleport( 1, 38, 11, 3 ,21,34 )); teleportList.add(new
		 * Teleport( 3,21,33, 1,39,11)); teleportList.add(new Teleport( 2,30,17,
		 * 3,34,18)); teleportList.add(new Teleport( 3,33,18, 2,31,17));
		 */
		processor = new ProcessFrontBehindEntity (this);
		setupGame();
	}
	
	public void setupGame()
	{
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setTeleport();
		player.setItems();
		aSetter.setMonster();
//		aSetter.setInteractiveTile();
		playMusic(0);
//		stopMusic();
//		gameState = titleState;
//		
//		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
//		g2 = (Graphics2D)tempScreen.getGraphics();
//		
//		if(fullScreenOn == true)
//		{
//			setFullScreen();
//		}
		
	}
	public void retry()
	{
		player.setDefaultPositions();
		player.restoreLifeAndMana();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setObject();
		aSetter.setTeleport();
	}
	public void restart()
	{ 
		player.setDefaultValues();
		player.getPlayerImage();
		player.setItems();
//		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
//		aSetter.setInteractiveTile();
		aSetter.setTeleport();
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	//Method run is a method of interface Runable
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		int framecount = 0;
		long timer = 0;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				this.repaint();
				
				delta -=1;
				framecount++;
			}
			if(timer >= 1000000000) {
				//System.out.println("FPS: " + framecount);
				framecount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		if(gameState == playState)
		{
			
			player.update();
			for(int i = 0; i < monster[num_CurrentMap].length; i++)
			{
				if(monster[num_CurrentMap][i] != null)
				{
					if(monster[num_CurrentMap][i].alive == true && monster[num_CurrentMap][i].dying == false)
					{
						monster[num_CurrentMap][i].update();
					}
					if (monster[num_CurrentMap][i].alive == false) {
					    monster[num_CurrentMap][i].checkDrop(); 
					    monster[num_CurrentMap][i] = null;
					}

				}
			}
			for(int i = 0; i < npc[num_CurrentMap].length;i++) {
				if(npc[num_CurrentMap][i] != null) {
					npc[num_CurrentMap][i].update();
				}
			}
			for(int i = 0; i < teleportList[num_CurrentMap].length;i++) {
				if(teleportList[num_CurrentMap][i] != null) {
					teleportList[num_CurrentMap][i].update();
				}
			}
			for(int i = 0; i < projectileList.size(); i++)
			{
				if(projectileList.get(i) != null)
				{
					if(projectileList.get(i).alive == true)
					{
						projectileList.get(i).update();
					}
					if(projectileList.get(i).alive == false)
					{
						projectileList.remove(i);
					}
				}
			}
//			cube.update();
			//System.out.println(player.worldX +" "+ player.worldY);
			System.out.println("Current Map: " + num_CurrentMap);
			int playerCol = player.worldX/tileSize;
			int playerRow = player.worldY/tileSize;
			System.out.println(playerCol +" "+ playerRow);
			/*
			 * for (Teleport tp : teleportList) { if (num_CurrentMap == tp.fromMap &&
			 * playerCol == tp.fromCol && playerRow == tp.fromRow && can_touch == true) {
			 * this.player.x = this.player.defaultScreenX; this.player.y =
			 * this.player.defaultScreenY; Teleport(tp.toMap, tp.toCol, tp.toRow); can_touch
			 * = false; break; } }
			 */
		}
		if(gameState == pauseState)
		{
			//nothing
		}
		if(gameState == gameOverState) {
			restart();
		}
	}
	
	/*
	 * public void Teleport(int targetmap, int col, int row ) { playSE(6); gameState
	 * = transitionState; tempMap = targetmap; tempCol = col; tempRow = row; //
	 * num_CurrentMap = targetmap; // currentMap = maps[num_CurrentMap]; //
	 * player.worldX = col * tileSize; // player.worldY = row * tileSize; }
	 */	 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		ArrayList<Entity> entities = new ArrayList();
		entities.add(player);
		currentMap.draw(g2, 1);
		//currentMap.draw(g2, 2);
		//player.draw(g2);
//		if(player.hp > 0) {
//			cube.draw(g2);
//		}
		for(int i = 0; i < monster[num_CurrentMap].length; i++)
		{
			if(monster[num_CurrentMap][i] != null)
			{
				entities.add(monster[num_CurrentMap][i]);
			}
		}
		for(int i = 0; i < npc[num_CurrentMap].length; i++)
		{
			if(npc[num_CurrentMap][i] != null)
			{
				entities.add(npc[num_CurrentMap][i]);
			}
		}
		for(int i = 0; i < npc[num_CurrentMap].length; i++)
		{
			if(npc[num_CurrentMap][i] != null)
			{
				entities.add(npc[num_CurrentMap][i]);
			}
		}
		for(int i = 0; i < teleportList[num_CurrentMap].length; i++)
		{
			if(teleportList[num_CurrentMap][i] != null)
			{
				entities.add(teleportList[num_CurrentMap][i]);
			}
		}
		for(int i = 0; i < obj[num_CurrentMap].length; i++)
		{
			if(obj[num_CurrentMap][i] != null)
			{
				entities.add(obj[num_CurrentMap][i]);
			}
		}
		for(int i = 0; i < projectileList.size(); i++)
		{
			if(projectileList.get(i) != null)
			{
//				projectileList.get(i).update();
				entities.add(projectileList.get(i));
			}
		}
		for(Entity e: entities) {
			e.draw(g2);
		}
		
		currentMap.draw(g2, 2);
		processor.draw(g2, currentMap.getLayer2(), entities);
//		projectileList.clear();
		//UI
		
		ui.draw(g2);
		g2.dispose();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}