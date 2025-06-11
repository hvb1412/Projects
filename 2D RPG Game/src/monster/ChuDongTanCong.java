package monster;

import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import entity.Character;
import main.GamePanel;

public class ChuDongTanCong {
	GamePanel gp;
	
	public ChuDongTanCong(GamePanel gp) {
		this.gp = gp;
	}
	
	public void attackByTouch(Monster monster) {
		int playerLeftX = gp.player.worldX + gp.player.solidAreaDefaultX;
		int playerTopY = gp.player.worldY + gp.player.solidAreaDefaultY;
		gp.player.solidArea.x = playerLeftX;
		gp.player.solidArea.y = playerTopY;
		monster.solidArea.x = monster.worldX + monster.solidAreaDefaultX;
		monster.solidArea.y = monster.worldY + monster.solidAreaDefaultY;
		if((monster.solidArea.intersects(gp.player.solidArea)) && (gp.player.invincible == false)) {
			gp.player.invincible = true;
			gp.player.takeDamge(10);
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		monster.solidArea.x = monster.solidAreaDefaultX;
		monster.solidArea.y = monster.solidAreaDefaultY;
	}
	public void QuaiVatDuoiTheoPlayer(Monster monster) {
		boolean tanCong = false;
		int warZoneWidth = 6*gp.tileSize;
		int warZoneHeight = 6*gp.tileSize;
		int safeZoneWidth = 10*gp.tileSize;
		int safeZoneHeight = 10*gp.tileSize;
		Player player = gp.player;
		if(player.worldX + player.solidAreaDefaultX <= monster.worldX + monster.solidAreaDefaultX + warZoneWidth/2 
				&& player.worldX + player.solidAreaDefaultX >= monster.worldX + monster.solidAreaDefaultX - warZoneWidth/2
				&& player.worldY + player.solidAreaDefaultX <= monster.worldY + monster.solidAreaDefaultY+ warZoneHeight/2
				&& player.worldY + player.solidAreaDefaultX >= monster.worldY + monster.solidAreaDefaultY - warZoneHeight/2
				) {
			tanCong = true;
		}
		if(player.worldX + player.solidAreaDefaultX < monster.worldX + monster.solidAreaDefaultX - safeZoneWidth/2
				|| player.worldX + player.solidAreaDefaultX > monster.worldX + monster.solidAreaDefaultX + safeZoneWidth/2
				|| player.worldY + player.solidAreaDefaultX > monster.worldY + monster.solidAreaDefaultY + safeZoneHeight/2
				|| player.worldY + player.solidAreaDefaultX < monster.worldY + monster.solidAreaDefaultY - safeZoneHeight/2) {
			tanCong = false;
		}
		
		if(tanCong) {
			if(monster.state != "MOVE" && monster.state != "ATTACK") {
				 monster.state = "MOVE";
				 monster.spriteNum = -1;
				 monster.spriteCounter = 0;
			 }

			if(monster.worldX + monster.solidAreaDefaultX + monster.solidArea.width < player.worldX + player.solidAreaDefaultX - 15) {
				monster.direction = "right";
			}
			else if(monster.worldX + monster.solidAreaDefaultX > player.worldX + player.solidAreaDefaultX + player.solidArea.width + 15) {
				monster.direction = "left";
			}
			else if(monster.worldY + monster.solidAreaDefaultY + monster.solidArea.height < player.worldY + player.solidAreaDefaultY - 10) {
				monster.direction = "down";
			}
			else if(monster.worldY + monster.solidAreaDefaultY > player.worldY + player.solidAreaDefaultY + player.solidArea.height + 10) {
				monster.direction = "up";
			}
		}
		
		monster.attackZone.x = monster.worldX + monster.attackZoneDefaultX;
		 monster.attackZone.y = monster.worldY + monster.attackZoneDefaultY;
		 Rectangle solidPlayer = gp.player.solidArea;
		 solidPlayer.x = gp.player.worldX + gp.player.solidAreaDefaultX;
		 int range = monster.attackZone.width + 2*monster.attackZoneDefaultX - monster.width;
		 if(monster.flip) monster.attackZone.x -= range;
		 solidPlayer.y = gp.player.worldY + gp.player.solidAreaDefaultY;
		 Rectangle intersection = monster.attackZone.intersection(solidPlayer);
		 if(monster.attackZone.intersects(solidPlayer) && intersection.width >= 5) {
			 if(monster.state != "ATTACK") {
				 monster.state = "ATTACK";
				 monster.spriteNum = -1;
				 monster.spriteCounter = 0;
			 }
		 }
		 solidPlayer.x = gp.player.solidAreaDefaultX;
		 solidPlayer.y = gp.player.solidAreaDefaultY;
	}
}
