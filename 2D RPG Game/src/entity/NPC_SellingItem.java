package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
//import object.OBJ_Axe;
//import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_sword_01;
import object.OBJ_sword_02;
import object.OBJ_sword_03;
import object.OBJ_sword_04;
import object.OBJ_sword_05;
import object.OBJ_sword_06;

public class NPC_SellingItem extends NPC {

	public NPC_SellingItem(GamePanel gp) {
		super(gp);
		collision = true;
		CollisionOn = false;
		solidArea = new Rectangle();
		solidArea.x = 5; // = 17*gp.tileSize/40
		solidArea.y = 13; // = 22*gp.tileSize/40
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 22; // = 21*gp.tileSize/40
		solidArea.height = 22; // = 18*gp.tileSize/40

		direction = "up";
		speed = 1;

		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {

		up1 = setup("/npc_selling/merchant_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		up2 = setup("/npc_selling/merchant_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
		down1 = setup("/npc_selling/merchant_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		down2 = setup("/npc_selling/merchant_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
		left1 = setup("/npc_selling/merchant_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		left2 = setup("/npc_selling/merchant_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
		right1 = setup("/npc_selling/merchant_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		right2 = setup("/npc_selling/merchant_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);

	}

	public void setDialogue() {
		dialogues[0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
	}

	public void setItems() {
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Potion_Red(gp));
//		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Sword_Normal(gp));
//		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
		inventory.add(new OBJ_Shield_Blue(gp));
		inventory.add(new OBJ_sword_01(gp));
		inventory.add(new OBJ_sword_02(gp));
		inventory.add(new OBJ_sword_03(gp));
		inventory.add(new OBJ_sword_04(gp));
		inventory.add(new OBJ_sword_05(gp));
		inventory.add(new OBJ_sword_06(gp));
	}

	public void speak() {
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}

	public void setAction() {
		actionLockCounter++;

		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";
			} else if (i <= 50) {
				direction = "down";
			} else if (i <= 75) {
				direction = "left";
			} else {
				direction = "right";
			}
			actionLockCounter = 0;
		}
	}

	public void checkCollision() {

		CollisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
	}
	@Override
	public void update() {
		setAction();
		checkCollision();
		spriteCounter++;
		if (spriteCounter > 15) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		if (CollisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
	}
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.x &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.x &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.y &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.y)
		{
			switch(direction)
			{
			case "up":
				if(spriteNum == 1)
				{
					image = up1;	
				}
				if(spriteNum == 2)
				{
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = down1;	
				}
				if(spriteNum == 2)
				{
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = left1;	
				}
				if(spriteNum == 2)
				{
					image = left2;
				}
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = right1;	
				}
				if(spriteNum == 2)
				{
					image = right2;
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, null);
			if (gp.testMode) {
				g2.setColor(Color.RED);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}
		}

	}

	
}
