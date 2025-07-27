package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Item extends Entity{
	public int value;
	public int useCost;
	public int attackValue;
	public int defenseValue;
	public boolean checkUse;
	public int price;
	public BufferedImage image2, image3,image4;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Item(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}

	public void use(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
