package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import monster.Monster;

public class Projectile extends Character{
	
	public Entity user;
	public int useCost;
	public Monster monster;
	public boolean reverse;
	public int breakDefense = 0;

	public Projectile(GamePanel gp) {
		super(gp);
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user)
	{
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.hp = this.maxHp;
	}
	
	public void update()
	{
//		gp.cChecker.checkTile(this);
		if(user == gp.player)
		{
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999)
			{
				gp.player.damageMonsterByProjectile(monsterIndex, attack, breakDefense);
//				generateParticle(user.projectile, gp.monster[monsterIndex]);
				alive = false;
			}
		}
		if(user != gp.player)
		{
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true)
			{
				monster.damagePlayer(attack);
//				generateParticle(user.projectile, gp.player);
				alive = false;
			}
		}
		
		switch(direction)
		{
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		hp--;
		if(hp <= 0)
		{
			alive = false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12)
		{
			if(spriteNum == 1)
			{
				spriteNum = 2;
			}
			else if(spriteNum == 2)
			{
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public boolean haveResource(Player user)
	{
		boolean haveResource = false;
		return haveResource;
	}
	public void subtractResource(Player user)
	{
		user.mp -= 1;
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.x
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.x
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.y
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.y) {
			switch (direction) {
			case "up":
				image = (spriteNum == 1) ? up1 : up2;
				break;
			case "down":
				image = (spriteNum == 1) ? down1 : down2;
				break;
			case "left":
				image = (spriteNum == 1) ? left1 : left2;
				break;
			case "right":
				image = (spriteNum == 1) ? right1 : right2;
				break;
			}
			if(alive)
			g2.drawImage(image, screenX, screenY, null);
			if(gp.testMode) {
				g2.setColor(Color.blue);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			}

		}
	}
}
