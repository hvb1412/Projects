package monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.SpriteSheet;

public class Demon extends Monster {
	BufferedImage idle[];
	BufferedImage move[];
	BufferedImage attackImage[];

	public Demon(GamePanel gp) {
		super(gp);
		idle = new SpriteSheet("/demon/SpriteSheet_IDLE.png", 1728, 160, 6, 0, 46, 194, 114).animation;
		move = new SpriteSheet("/demon/SpriteSheet_MOVE.png", 3456, 160, 12, 0, 46, 194, 114).animation;
		attackImage = new SpriteSheet("/demon/SpriteSheet_ATTACK.png", 4320, 160, 15, 40, 40, 185, 120).animation;

		flip = false;
		this.height = gp.tileSize * 3; // 70
		this.width = this.height *194/114; // 96
		solidArea.x = this.height ; // 32
		solidArea.y = this.height * 45 / 70 - 55; // 45
		solidArea.width = gp.tileSize + gp.tileSize/2;
		solidArea.height = gp.tileSize + gp.tileSize + 10;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		attackZone = new Rectangle();
		attackZone.x = this.height * 34 / 70 - gp.tileSize - 20;
		attackZone.y = 0;
		attackZoneDefaultX = attackZone.x;
		attackZoneDefaultY = attackZone.y;

		attackZone.width = this.height * 51 / 70 + 2*gp.tileSize + 20; // 22
		attackZone.height = (this.height * 69 / 70)/2;
		this.image = idle[0];

		this.spriteNum = 0;
		this.spriteCounter = 0;
		this.speed = 1;
		this.alive = true;
		name = "Demon";
		type = type_monster;
		maxHp = 100;
		hp = maxHp;
		state = "MOVE";
		attack = 50;
		defense = 30;
		exp = 30;
		coin = 30;
		direction = "up";
		actionLockCounter = 0;
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		System.out.println(move.length);
		actionLockCounter++;
		if (actionLockCounter >= 120) {
			if (state == "MOVE") {
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
				
			} else {
				direction = "idle";
			}
			actionLockCounter = 0;
		}
		if (direction == "left") {
			this.flip = false;
		}
		if (direction == "right") {
			this.flip = true;
			
		}
		gp.quaiVatTanCong.QuaiVatDuoiTheoPlayer(this);
		if (state == "ATTACK") {
			this.attack();
		}
	}

	public void updateDrawImage(int screenX, int screenY) {
		if(flip) {
			attackZone.x = this.height - 5*gp.tileSize - 20;
			attackZone.y = (this.height * 69 / 70)/2;
			attackZoneDefaultX = attackZone.x;
			attackZoneDefaultY = attackZone.y;

			attackZone.width = this.height * 51 / 70 + 2*gp.tileSize + 20; // 22
			attackZone.height = (this.height * 69 / 70)/2;
		}
		else {
			attackZone.x = this.height * 34 / 70 - gp.tileSize - 20;
			attackZone.y = (this.height * 69 / 70)/2;
			attackZoneDefaultX = attackZone.x;
			attackZoneDefaultY = attackZone.y;

			attackZone.width = this.height * 51 / 70 + 2*gp.tileSize + 20; // 22
			attackZone.height = (this.height * 69 / 70)/2;
		}
		if (state == "MOVE") {
			if (spriteNum == -1)
				spriteNum = 0;
			switch (direction) {
			case "up":
				image = move[spriteNum];
				break;
			case "down":
				image = move[spriteNum];
				break;
			case "left":
				image = move[spriteNum];
				;
				break;
			case "right":
				image = move[spriteNum];
				;
				break;
			}
		} else if (state == "ATTACK") {
			if (spriteNum == -1)
				spriteNum = 0;
			image = attackImage[spriteNum];
			System.out.println("------------" + spriteNum);
			System.out.println("============" + attackImage.length);
			if (spriteNum == attackImage.length - 1) {
				this.move();
			}
		} else if (state == "IDLE") {
			if (spriteNum == -1)
				spriteNum = 0;
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
		if (spriteNum == 12 && spriteCounter % 10 == 5) {
			attackZone.x = worldX + attackZoneDefaultX;
			attackZone.y = worldY + attackZoneDefaultY;
			Rectangle solidPlayer = gp.player.solidArea;
			solidPlayer.x = gp.player.worldX + gp.player.solidAreaDefaultX;
			int range = this.attackZone.width + 2 * this.attackZoneDefaultX - this.width;
			if (flip)
				attackZone.x -= range;
			solidPlayer.y = gp.player.worldY + gp.player.solidAreaDefaultY;
			if (attackZone.intersects(solidPlayer)) {
				gp.player.takeDamge(this.attack);
			}
			solidPlayer.x = gp.player.solidAreaDefaultX;
			solidPlayer.y = gp.player.solidAreaDefaultY;
		}
	}

	public void move() {
		if (state != "MOVE") {
			state = "MOVE";
			spriteNum = -1;
			spriteCounter = 0;
		}
	}

	public void idle() {
		if (state != "IDLE") {
			state = "IDLE";
			spriteNum = -1;
			spriteCounter = 0;
		}
	}

	public void updateSpriteNum() {
		System.out.println("++++++++" + spriteCounter);
		if (spriteCounter % 10 == 0) {
			switch (state) {
			case "MOVE":
				spriteNum = (spriteNum + 1) % move.length;
				break;
			case "ATTACK":
				spriteNum = (spriteNum + 1) % attackImage.length;
				break;
			case "IDLE":
				spriteNum = (spriteNum + 1) % idle.length;
				break;
			}
		}
	}

	public void drawImage(Graphics2D g2, int screenX, int screenY) {
		g2.setColor(Color.blue);
		int range = this.attackZone.width + 2 * this.attackZoneDefaultX - this.width;
		if (flip) {
			g2.drawImage(image, screenX + this.width + this.width/2, screenY, -this.width, this.height, null);
			if (gp.testMode)
				g2.drawRect(screenX + this.attackZoneDefaultX - range, screenY + this.attackZoneDefaultY,
						this.attackZone.width, this.attackZone.height);
		} else {
			g2.drawImage(image, screenX, screenY, this.width, this.height, null);
			if (gp.testMode)
				g2.drawRect(screenX + this.attackZoneDefaultX, screenY + this.attackZoneDefaultY, this.attackZone.width,
						this.attackZone.height);
		}
	}
}