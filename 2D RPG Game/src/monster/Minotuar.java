
package monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.SpriteSheet;

public class Minotuar extends Monster{
	BufferedImage idle[];
	BufferedImage move[];
	BufferedImage attackImage[];
	private int delayAttackCounter;
	private boolean attackReady;
	private boolean prepareAttack;
	private int preparingTimeAttack;
	public Minotuar(GamePanel gp) {
		super(gp);
		idle = new SpriteSheet("/monster/minotuar/MinotuarIDLE_480x96.png", 480, 96, 5, 0, 0, 96, 70).animation;
		move = new SpriteSheet("/monster/minotuar/MinotuarMOVE_768x96.png", 768, 96, 8, 0, 0, 96, 70).animation;
		attackImage = new SpriteSheet("/monster/minotuar/MinotuarATTACK_864x96.png", 864, 96, 9, 0, 0, 96, 70).animation;
		flip = false;
		this.attackReady = true;
		prepareAttack = false;
		this.preparingTimeAttack = 0;
		this.delayAttackCounter = 0;
		this.height = gp.tileSize*70/50; //70
		this.width = this.height*96/70; //96
		solidArea.x = this.height*38/70 - 5; //32
		solidArea.y = this.height*45/70 - 20; //45
		solidArea.width = this.height*30/70; //22
		solidArea.height = this.height*30/70 + 7; //19
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		attackZone = new Rectangle();
		attackZone.x = this.height*34/70;
		attackZone.y = 0;
		attackZoneDefaultX = attackZone.x;
		attackZoneDefaultY = attackZone.y;
		
		attackZone.width = this.height*51/70; //22
		attackZone.height = this.height*69/70;
		this.image = idle[0];
		
		this.spriteNum = 0;
		this.spriteCounter = 0;
		this.speed = 1;
		this.alive = true;
		name = "Minotuar";
        type = type_monster;
        maxHp = 50;
        hp = maxHp;
        state = "MOVE";
        attack = 30;
        defense = 20;
        exp = 10;
        coin = 10; 
        direction = "up";
        actionLockCounter = 0;
	}
	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		//System.out.println("++++++++++++++++"+ this.delayAttackCounter);
		if(this.attackReady == false) {
			delayAttackCounter++;
			if(delayAttackCounter >= 70) {
				delayAttackCounter = 0;
				this.attackReady = true;
			}
		}
		if(this.prepareAttack) {
			this.preparingTimeAttack++;
			if(preparingTimeAttack >= 30) {
				this.prepareAttack = false;
				this.preparingTimeAttack = 0;
				this.spriteNum++;
			}
		}
		actionLockCounter++;
		System.out.println(state);
		 if (actionLockCounter >= 120) {
			 if(state == "MOVE") {
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
		            
			 }
			 else {
				 direction = "idle";
			 }  
			 actionLockCounter = 0;
		 }
		 if(direction == "left") {
			 this.flip = true;
		 }
		 if(direction == "right") {
			 this.flip = false;
		 }
		 gp.quaiVatTanCong.QuaiVatDuoiTheoPlayer(this);
		 if(state == "ATTACK" && attackReady == true) {
			 this.attack();			 
		 }
	}
	public void updateDrawImage(int screenX, int screenY) {
		if(state == "MOVE") {
		if(spriteNum == -1) spriteNum = 0;
		switch (direction) {
		case "up":
			image = move[spriteNum]; 
			break;
		case "down":
			image = move[spriteNum]; 
			break;
		case "left":
			image = move[spriteNum]; 
			break;
		case "right":
			image = move[spriteNum]; 
			break;
		}
		}
		else if(state == "ATTACK" && this.attackReady) {
			if(spriteNum == -1) spriteNum = 0;
			image = attackImage[spriteNum];
			if(spriteNum == attackImage.length - 1) {
				 this.move();
			 }
		}
		else if(state == "IDLE") {
			if(spriteNum == -1) spriteNum = 0;
			image = idle[spriteNum];
		}
	}
	
	public void attack() {
		if (state != "ATTACK") {
			state = "ATTACK";
			spriteNum = -1;
			spriteCounter = 0;
		}
		direction = "idle";
		 if(spriteNum == 3 && spriteCounter % 8 == 4) {
			 attackZone.x = worldX + attackZoneDefaultX;
			 attackZone.y = worldY + attackZoneDefaultY;
			 Rectangle solidPlayer = gp.player.solidArea;
			 solidPlayer.x = gp.player.worldX + gp.player.solidAreaDefaultX;
			 int range = this.attackZone.width + 2*this.attackZoneDefaultX - this.width;
			 if(flip) attackZone.x -= range;
			 solidPlayer.y = gp.player.worldY + gp.player.solidAreaDefaultY;
			 if(attackZone.intersects(solidPlayer)) {
				 gp.player.takeDamge(this.attack);
			 }
			 solidPlayer.x = gp.player.solidAreaDefaultX;
			 solidPlayer.y = gp.player.solidAreaDefaultY;
		 }
		 
	}
	
	public void move() {
		if(state != "MOVE") {
			state = "MOVE";
			spriteNum = -1;
			spriteCounter = 0;
		}
	}
	public void idle() {
		if(state != "IDLE") {
			state = "IDLE";
			spriteNum = -1;
			spriteCounter = 0;
		}
	}
	
	public void updateSpriteNum() {
		if (spriteCounter % 8 == 0) {
			switch(state) {
			case "MOVE":
				spriteNum = (spriteNum+1)%move.length;
				break;
			case "ATTACK":
				if(spriteNum == 0) {
					prepareAttack = true;
				}
				if(prepareAttack == false) spriteNum = (spriteNum+1)%attackImage.length;
				if(spriteNum == attackImage.length - 1) {
					this.attackReady = false; 
				 }
				if(this.attackReady == false) this.move();
				break;
			case "IDLE":
				spriteNum = (spriteNum+1)%idle.length;
				break;
			}
		}
	}
	
	public void drawImage(Graphics2D g2, int screenX, int screenY) {
		g2.setColor(Color.blue);
		int range = this.attackZone.width + 2*this.attackZoneDefaultX - this.width;
		if(flip) {
			g2.drawImage(image, screenX + this.width, screenY, -this.width, this.height, null);
			if(gp.testMode) g2.drawRect(screenX + this.attackZoneDefaultX - range, screenY + this.attackZoneDefaultY, this.attackZone.width, this.attackZone.height);
		}
		else {
			g2.drawImage(image, screenX, screenY, this.width, this.height, null);
			if(gp.testMode) g2.drawRect(screenX + this.attackZoneDefaultX, screenY + this.attackZoneDefaultY, this.attackZone.width, this.attackZone.height);
		}
	}
}