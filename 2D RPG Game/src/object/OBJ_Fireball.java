package object;

import java.awt.Color;

import entity.Entity;
import entity.Player;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{

	
	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		name = "Fireball";
		speed = 8;
		maxHp = 80;
		hp = maxHp;
		attack = 15;
		useCost = 3;
		solidArea.x = 12;
		solidArea.y = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 12;
		solidArea.height = 12;
		alive = false;
		getImage();
		
	}
	
	public void getImage()
	{
		up1 = setup("/projectile/fireball_up_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		up2 = setup("/projectile/fireball_up_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
		down1 = setup("/projectile/fireball_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		down2 = setup("/projectile/fireball_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
		left1 = setup("/projectile/fireball_left_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		left2 = setup("/projectile/fireball_left_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
		right1 = setup("/projectile/fireball_right_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		right2 = setup("/projectile/fireball_right_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
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
}
