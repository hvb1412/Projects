package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;

public class NPC_Dialogue extends NPC{

	private boolean flip;
	public NPC_Dialogue(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		    collision = true;
		    CollisionOn = false;
		    solidArea = new Rectangle();
		    
			speed = 1;
			direction = "up";
	        getImage(); 
	        setDialogue();
	}
	public void getImage() {
		up1 = setup("/npc/npc_ghost_1", 4*gp.tileSize/3, 4*gp.tileSize/3);
		up2 = setup("/npc/npc_ghost_2", 4*gp.tileSize/3, 4*gp.tileSize/3);
		down1 = setup("/npc/npc_ghost_3", 4*gp.tileSize/3, 4*gp.tileSize/3);
		down2 = setup("/npc/npc_ghost_4", 4*gp.tileSize/3, 4*gp.tileSize/3);
		left1 = setup("/npc/npc_ghost_1", 4*gp.tileSize/3, 4*gp.tileSize/3);
		left2 = setup("/npc/npc_ghost_2", 4*gp.tileSize/3, 4*gp.tileSize/3);
		right1 = setup("/npc/npc_ghost_3", 4*gp.tileSize/3, 4*gp.tileSize/3);
		right2 = setup("/npc/npc_ghost_4", 4*gp.tileSize/3, 4*gp.tileSize/3);
	}
	public void setDialogue() {

        dialogues[0] = "Recently,\nThis word has been conquered by terrible monster!\nThere are two doors leading to different word!\nSave me!";

    }
	public void speak() {
        if (dialogues[dialoguesIndex] == null) {
        	dialoguesIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialoguesIndex];
        dialoguesIndex = (dialoguesIndex + 1)%dialogues.length;
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
				flip = true;
			} else {
				direction = "right";
				flip = false;
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
		if(flip) {
			solidArea.x = -40; // = 17*gp.tileSize/40
			solidArea.y = 25; // = 22*gp.tileSize/40
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			solidArea.width = 21; // = 21*gp.tileSize/40
			solidArea.height = 21; //= 18*gp.tileSize/40
		}
		else {
			solidArea.x = 21; // = 17*gp.tileSize/40
			solidArea.y = 25; // = 22*gp.tileSize/40
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			solidArea.width = 21; // = 21*gp.tileSize/40
			solidArea.height = 21; //= 18*gp.tileSize/40
		}
		checkCollision();
		//setAction();
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
	}
/*		if (CollisionOn == false) {
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
	}*/
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
			if(flip) {
				g2.drawImage(image, screenX + 4*gp.tileSize/3, screenY, -4*gp.tileSize/3, 4*gp.tileSize/3, null);
//				if(gp.testMode) g2.drawRect(screenX + this.attackZoneDefaultX - range, screenY + this.attackZoneDefaultY, this.attackZone.width, this.attackZone.height);
			}
			else {
				g2.drawImage(image, screenX, screenY, 4*gp.tileSize/3, 4*gp.tileSize/3, null);
//				if(gp.testMode) g2.drawRect(screenX + this.attackZoneDefaultX, screenY + this.attackZoneDefaultY, this.attackZone.width, this.attackZone.height);
			}
//			g2.drawImage(image, screenX, screenY, null);
			if (gp.testMode) {
				g2.setColor(Color.RED);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}
		}

	}
}
