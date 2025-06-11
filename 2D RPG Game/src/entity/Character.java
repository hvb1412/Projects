package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;

public class Character extends Entity{
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,idle;

	public BufferedImage attackUp1,attackUp2, attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
	public int hp, maxHp;
	public int mp, maxMp;
	public int attack;
	public int attackDefault;
	public int defense;
	public int defenseDefault;
	public int strength;
	public int speed;
	public Rectangle attackZone;
	public int attackZoneDefaultX;
	public int attackZoneDefaultY;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public boolean alive = true;
	public boolean dying = false;
	public boolean invincible = false; // bất khả chiến bại =))
	public boolean hpBarOn = false;
	public String direction = "down";
	public boolean attacking;
	public Item currentWeapon;
	public Item currentShield;
	public ArrayList<Item> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public Character(GamePanel gp) {
		super(gp);
	}
	
}
