package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Item;
import main.GamePanel;
import entity.Character;

public abstract class Monster extends Character {
	public int exp = 2;
	public int coin = 0;
	public int attackDelayCounter;
	boolean attacking = false;
	int attackingCounter = 0;
	public String state;
	boolean flip;
	public Monster(GamePanel gp) {
		super(gp);
		solidArea.x = 3;
		solidArea.y = 14;
		solidArea.width = 30;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		alive = true;
		flip = false;
		attackDelayCounter = 0;
		spriteNum = 1;
	}

	@Override
	public void update() {
        gp.cChecker.checkTile(this);
		setAction();
		CollisionOn = false;
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
//		if (this.type == type_monster && contactPlayer == true) {
//			damagePlayer(attack);
//		}
		attackDelayCounter++;
		if(attackDelayCounter == 25) {
			gp.quaiVatTanCong.attackByTouch(this);
			attacking = true;
			attackDelayCounter = 0;
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

		spriteCounter++;
		updateSpriteNum();

		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	}
	
	public void updateSpriteNum() {
		if (spriteCounter > 15) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
			// we can give damage
//			gp.playSE(6);
			gp.player.takeDamge(attack);

			gp.player.invincible = true;
		}
	}

	public void takeDamage(int playerAttack) {
		if(playerAttack - this.defense > 0) {
			this.hp -= (playerAttack - this.defense);
			gp.ui.addMessage((playerAttack - this.defense) + " damage!");
		}
		gp.ui.addMessage("0  damage!");
		
		invincible = true;
		if (this.hp <= 0) {
			dying = true;
//			checkDrop();
		}
	}

	public void damageReaction() {

	}

	// Phuong thuc abstract cho hành động của quái vật
	public abstract void setAction();

	// Phuong thuc kiểm tra việc rơi đồ
	public void checkDrop() {

	};

	public void dropItem(Item droppedItem) {
		for (int i = 0; i < gp.obj[gp.num_CurrentMap].length; i++) {
			if (gp.obj[gp.num_CurrentMap][i] == null) {
				gp.obj[gp.num_CurrentMap][i] = droppedItem;
				gp.obj[gp.num_CurrentMap][i].worldX = worldX; // the dead monster's worldX
				gp.obj[gp.num_CurrentMap][i].worldY = worldY;
				break;
			}
		}
	}

	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		int i = 5;

		if (dyingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i && dyingCounter <= i * 2) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 8) {
			alive = false;
		}
	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	public void updateDrawImage(int screenX, int screenY) {
		switch (direction) {
		case "up":
			if (!attacking) {
				image = (spriteNum == 1) ? up1 : up2;
			}
			break;
		case "down":
			image = (!attacking) ? (spriteNum == 1 ? down1 : down2) : (spriteNum == 1 ? attackDown1 : attackDown2);
			break;
		case "left":
			if (!attacking) {
				image = (spriteNum == 1) ? left1 : left2;
			} 
			break;
		case "right":
			image = (!attacking) ? (spriteNum == 1 ? right1 : right2) : (spriteNum == 1 ? attackRight1 : attackRight2);
			break;
		}
	}
	public void drawImage(Graphics2D g2, int screenX, int screenY) {
		g2.drawImage(image, screenX, screenY, null);
	}
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.x
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.x
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.y
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.y) {
				updateDrawImage(screenX, screenY);
			}

			// MONSTER HP BAR
			if (type == 2 && hpBarOn == true) {
				double oneScale = (double) gp.tileSize / maxHp;
				double hpBarValue = oneScale * hp;

				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

				hpBarCounter++;

				if (hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			if (invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4F);
			}
			if (dying == true) {
				dyingAnimation(g2);
				hpBarOn = false;
			}
			g2.setColor(Color.red);
			this.drawImage(g2, screenX, screenY);
			if(gp.testMode) g2.drawRect(screenX + this.solidAreaDefaultX, screenY + this.solidAreaDefaultY, this.solidArea.width, this.solidArea.height);

			if (attacking) {
				Color c = new Color(255, 0, 0, 210);
				g2.setColor(c);
				if(gp.testMode)g2.fillRect(screenX + this.solidAreaDefaultX, screenY + this.solidAreaDefaultY, this.solidArea.width, this.solidArea.height);
				attacking = false;
			}
			changeAlpha(g2, 1F);
		}
	}


