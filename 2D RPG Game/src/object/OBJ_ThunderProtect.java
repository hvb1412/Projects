package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Player;
import entity.Projectile;
import main.GamePanel;

public class OBJ_ThunderProtect extends Projectile{
	
	public OBJ_ThunderProtect(GamePanel gp) {
		super(gp);
		name = "Thunder Bolt";
		speed = 0;
		maxHp = 180;
		hp = maxHp;
		attack = 0;
		useCost = 8;
		breakDefense = 1;
		reverse = false;		
		alive = false;
		getImage();
		
	}
	
	public void getImage()
	{
		up1 = setup("/projectile/lightning_skill5_frame3", gp.tileSize*3, gp.tileSize*2);
		up2 = setup("/projectile/lightning_skill5_frame4", gp.tileSize*3, gp.tileSize*2);
		down1 = setup("/projectile/lightning_skill5_frame4", gp.tileSize*3, gp.tileSize*2);
		down2 = setup("/projectile/lightning_skill5_frame3", gp.tileSize*3, gp.tileSize*2);
		left1 = setup("/projectile/lightning_skill5_frame3", gp.tileSize*3, gp.tileSize*2);
		left2 = setup("/projectile/lightning_skill5_frame4", gp.tileSize*3, gp.tileSize*2);
		right1 = setup("/projectile/lightning_skill5_frame4", gp.tileSize*3, gp.tileSize*2);
		right2 = setup("/projectile/lightning_skill5_frame3", gp.tileSize*3, gp.tileSize*2);
	}
	public boolean haveResource(Player user)
	{
		boolean haveResource = false;
		if(user.mp >= useCost)
		{
			haveResource = true;
		}
		return haveResource;
	}
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user)
	{
		this.worldX = worldX;
		this.worldY = worldY - gp.tileSize/2;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.hp = this.maxHp;
	}
	public void subtractResource(Player user)
	{
		user.mp -= useCost;
	}
	public Color getParticleColor()
	{
		Color color = new Color(240,50,0);
		return color;
	}
	public int getParticleSize()
	{
		int size = 10;
		return size;
	}
	public int getParticleSpeed()
	{
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife()
	{
		int maxLife = 20;
		return maxLife;
	}
	public void update()
	{
		
//		gp.cChecker.checkTile(this);
		if(user == gp.player)
		{
			System.out.println("+++:" + alive);
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999)
			{
				gp.player.damageMonsterByProjectile(monsterIndex, attack, breakDefense);
//				generateParticle(user.projectile, gp.monster[monsterIndex]);
			}
			System.out.println("----" + alive);
		}
		if(user != gp.player)
		{
			System.out.println("+++:" + alive);
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true)
			{
				monster.damagePlayer(attack);
//				generateParticle(user.projectile, gp.player);
			}
			System.out.println("----" + alive);
		}
		
		switch(direction)
		{
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": reverse = true; break;
		case "right": reverse = false; break;
		}
		
		hp--;
		if(hp <= 0)
		{
			alive = false;
			gp.player.canMove = true;
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
//		if(flip) {
//			int range = gp.player.attackZone.width + 2*gp.player.attackZoneDefaultX - gp.player.width;
//			worldX -= range;
//		}
		if(reverse) {
			solidArea.x = -gp.tileSize/2;
			solidArea.y = 10;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			solidArea.width = gp.tileSize*2 + 15;
			solidArea.height = gp.tileSize + 15;
		}
		else {
			solidArea.x = -gp.tileSize/2;
			solidArea.y = 10;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			solidArea.width = gp.tileSize*2 + 15;
			solidArea.height = gp.tileSize + 15;
		}
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
			if(alive) {
				if(reverse) {
					g2.drawImage(image, screenX - gp.tileSize + 8, screenY - 8, null);
					if(gp.testMode) {
						g2.setColor(Color.blue);
						g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
					}
					
				}
				else {
					g2.drawImage(image, screenX - gp.tileSize + 8, screenY - 8, null);
					if(gp.testMode) {
						g2.setColor(Color.blue);
						g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
					}
				}
				
			}			
		}
	}
}
