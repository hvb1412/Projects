package main;

import entity.NPC_Dialogue;
import entity.NPC_SellingItem;
import map.Teleport;
import monster.Bat;
import monster.Demon;
import monster.Minotuar;
import monster.Skeleton;
import monster.Slime;
import monster.NERC;
import monster.Snake;
import monster.Gorgon;
import monster.Medusa;
import object.OBJ_Coin_Bronze;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}

	public void setObject()
	{
		int i = 0;
		gp.obj[1][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[1][i].worldX = gp.tileSize*24;
		gp.obj[1][i].worldY = gp.tileSize*30;
		i++;
		gp.obj[1][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[1][i].worldX = gp.tileSize*24;
		gp.obj[1][i].worldY = gp.tileSize*16;
		i++;
		gp.obj[1][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[1][i].worldX = gp.tileSize*21;
		gp.obj[1][i].worldY = gp.tileSize*17;
		i++;
		gp.obj[1][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[1][i].worldX = gp.tileSize*21;
		gp.obj[1][i].worldY = gp.tileSize*19;
		i++;
	}

	public void setNPC()
	{
		int i = 0;
		gp.npc[1][i] = new NPC_Dialogue(gp);
		gp.npc[1][i].worldX = gp.tileSize* 21;
		gp.npc[1][i].worldY = gp.tileSize * 23;
		i++;
		gp.npc[1][i] = new NPC_SellingItem(gp);
		gp.npc[1][i].worldX = gp.tileSize* 34;
		gp.npc[1][i].worldY = gp.tileSize * 24;
		i++;
		
	}
	public void setMonster()
	{
		//Map 01
		int i = 0;
		
		//SLIME
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*20;
		gp.monster[1][i].worldY = gp.tileSize*21;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*20;
		gp.monster[1][i].worldY = gp.tileSize*20;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*19;
		gp.monster[1][i].worldY = gp.tileSize*20;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*19;
		gp.monster[1][i].worldY = gp.tileSize*19;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*20;
		gp.monster[1][i].worldY = gp.tileSize*19;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*21;
		gp.monster[1][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*19;
		gp.monster[1][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*21;
		gp.monster[1][i].worldY = gp.tileSize*27;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*21;
		gp.monster[1][i].worldY = gp.tileSize*28;
		i++;
		
		//Medusa
		gp.monster[1][i] = new Medusa(gp);
		gp.monster[1][i].worldX = gp.tileSize*13;
		gp.monster[1][i].worldY = gp.tileSize*18;
		i++;
		gp.monster[1][i] = new Medusa(gp);
		gp.monster[1][i].worldX = gp.tileSize*14;
		gp.monster[1][i].worldY = gp.tileSize*18;
		i++;
		gp.monster[1][i] = new Medusa(gp);
		gp.monster[1][i].worldX = gp.tileSize*15;
		gp.monster[1][i].worldY = gp.tileSize*18;
		i++;
		gp.monster[1][i] = new Medusa(gp);
		gp.monster[1][i].worldX = gp.tileSize*29;
		gp.monster[1][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[1][i] = new Medusa(gp);
		gp.monster[1][i].worldX = gp.tileSize*30;
		gp.monster[1][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[1][i] = new Medusa(gp);
		gp.monster[1][i].worldX = gp.tileSize*31;
		gp.monster[1][i].worldY = gp.tileSize*28;
		i++;
		
		
		//Minotuar
		gp.monster[1][i] = new Minotuar(gp);
		gp.monster[1][i].worldX = gp.tileSize*29;
		gp.monster[1][i].worldY = gp.tileSize*20;
		i++;
		gp.monster[1][i] = new Minotuar(gp);
		gp.monster[1][i].worldX = gp.tileSize*29;
		gp.monster[1][i].worldY = gp.tileSize*21;
		i++;
		gp.monster[1][i] = new Minotuar(gp);
		gp.monster[1][i].worldX = gp.tileSize*29;
		gp.monster[1][i].worldY = gp.tileSize*20;
		i++;
		
		//Map 02
		i = 0;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*19;
		gp.monster[2][i].worldY = gp.tileSize*44;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*19;
		gp.monster[2][i].worldY = gp.tileSize*45;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*20;
		gp.monster[2][i].worldY = gp.tileSize*44;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*20;
		gp.monster[2][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*19;
		gp.monster[2][i].worldY = gp.tileSize*46;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*16;
		gp.monster[2][i].worldY = gp.tileSize*36;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*16;
		gp.monster[2][i].worldY = gp.tileSize*35;
		i++;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*17;
		gp.monster[2][i].worldY = gp.tileSize*35;
		i++;
		//Snake
		gp.monster[2][i] = new Snake(gp);
		gp.monster[2][i].worldX = gp.tileSize*30;
		gp.monster[2][i].worldY = gp.tileSize*13;
		i++;
		gp.monster[2][i] = new Snake(gp);
		gp.monster[2][i].worldX = gp.tileSize*32;
		gp.monster[2][i].worldY = gp.tileSize*12;
		i++;
		gp.monster[2][i] = new Medusa(gp);
		gp.monster[2][i].worldX = gp.tileSize*31;
		gp.monster[2][i].worldY = gp.tileSize*12;
		i++;
		gp.monster[2][i] = new Medusa(gp);
		gp.monster[2][i].worldX = gp.tileSize*31;
		gp.monster[2][i].worldY = gp.tileSize*13;
		i++;
		gp.monster[2][i] = new Medusa(gp);
		gp.monster[2][i].worldX = gp.tileSize*32;
		gp.monster[2][i].worldY = gp.tileSize*14;
		i++;
		gp.monster[2][i] = new Gorgon(gp);
		gp.monster[2][i].worldX = gp.tileSize*31;
		gp.monster[2][i].worldY = gp.tileSize*11;
		i++;
		gp.monster[2][i] = new Gorgon(gp);
		gp.monster[2][i].worldX = gp.tileSize*31;
		gp.monster[2][i].worldY = gp.tileSize*10;
		i++;
		gp.monster[2][i] = new Gorgon(gp);
		gp.monster[2][i].worldX = gp.tileSize*31;
		gp.monster[2][i].worldY = gp.tileSize*15;
		i++;
		//Minotuar
		gp.monster[2][i] = new Minotuar(gp);
		gp.monster[2][i].worldX = gp.tileSize*11;
		gp.monster[2][i].worldY = gp.tileSize*31;
		i++;
		gp.monster[2][i] = new Minotuar(gp);
		gp.monster[2][i].worldX = gp.tileSize*11;
		gp.monster[2][i].worldY = gp.tileSize*30;
		i++;
		gp.monster[2][i] = new Minotuar(gp);
		gp.monster[2][i].worldX = gp.tileSize*12;
		gp.monster[2][i].worldY = gp.tileSize*30;
		i++;
		gp.monster[2][i] = new Minotuar(gp);
		gp.monster[2][i].worldX = gp.tileSize*12;
		gp.monster[2][i].worldY = gp.tileSize*31;
		i++;
		
		//MAP 03
		i = 0;
		//DEMON
		gp.monster[3][i] = new Demon(gp);
		gp.monster[3][i].worldX = gp.tileSize*1;
		gp.monster[3][i].worldY = gp.tileSize*13;
		i++;
		gp.monster[3][i] = new Demon(gp);
		gp.monster[3][i].worldX = gp.tileSize*1;
		gp.monster[3][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[3][i] = new Demon(gp);
		gp.monster[3][i].worldX = gp.tileSize*32;
		gp.monster[3][i].worldY = gp.tileSize*13;
		i++;
		gp.monster[3][i] = new Demon(gp);
		gp.monster[3][i].worldX = gp.tileSize*47;
		gp.monster[3][i].worldY = gp.tileSize*23;
		i++;
		//SKELETON
		gp.monster[3][i] = new Skeleton(gp);
		gp.monster[3][i].worldX = gp.tileSize*27;
		gp.monster[3][i].worldY = gp.tileSize*2;
		i++;
		gp.monster[3][i] = new Skeleton(gp);
		gp.monster[3][i].worldX = gp.tileSize*25;
		gp.monster[3][i].worldY = gp.tileSize*2;
		i++;
		gp.monster[3][i] = new Skeleton(gp);
		gp.monster[3][i].worldX = gp.tileSize*31;
		gp.monster[3][i].worldY = gp.tileSize*2;
		i++;
		gp.monster[3][i] = new Skeleton(gp);
		gp.monster[3][i].worldX = gp.tileSize*31;
		gp.monster[3][i].worldY = gp.tileSize*1;
		i++;
		//MERC
		gp.monster[3][i] = new NERC(gp);
		gp.monster[3][i].worldX = gp.tileSize*14;
		gp.monster[3][i].worldY = gp.tileSize*15;
		i++;
		gp.monster[3][i] = new NERC(gp);
		gp.monster[3][i].worldX = gp.tileSize*14;
		gp.monster[3][i].worldY = gp.tileSize*16;
		i++;
		gp.monster[3][i] = new NERC(gp);
		gp.monster[3][i].worldX = gp.tileSize*12;
		gp.monster[3][i].worldY = gp.tileSize*17;
		i++;
		gp.monster[3][i] = new NERC(gp);
		gp.monster[3][i].worldX = gp.tileSize*11;
		gp.monster[3][i].worldY = gp.tileSize*16;
		i++;
		//BAT
		gp.monster[3][i] = new Bat(gp);
		gp.monster[3][i].worldX = gp.tileSize*24;
		gp.monster[3][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[3][i] = new Bat(gp);
		gp.monster[3][i].worldX = gp.tileSize*25;
		gp.monster[3][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[3][i] = new Bat(gp);
		gp.monster[3][i].worldX = gp.tileSize*27;
		gp.monster[3][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[3][i] = new Bat(gp);
		gp.monster[3][i].worldX = gp.tileSize*23;
		gp.monster[3][i].worldY = gp.tileSize*26;
		i++;
		//Minotuar
		gp.monster[3][i] = new Minotuar(gp);
		gp.monster[3][i].worldX = gp.tileSize*24;
		gp.monster[3][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[3][i] = new Minotuar(gp);
		gp.monster[3][i].worldX = gp.tileSize*25;
		gp.monster[3][i].worldY = gp.tileSize*28;
		i++;
		gp.monster[3][i] = new Minotuar(gp);
		gp.monster[3][i].worldX = gp.tileSize*24;
		gp.monster[3][i].worldY = gp.tileSize*28;
		i++;
		
	}
	public void setTeleport() {
		gp.teleportList[1][0] = new Teleport(gp,1,9,11,2,23,23);
		gp.teleportList[1][0].worldX = gp.tileSize * 9;
		gp.teleportList[1][0].worldY = gp.tileSize *11;
		gp.teleportList[1][1] = new Teleport(gp,1,38,11,3,21,34);
		gp.teleportList[1][1].worldX = gp.tileSize * 38;
		gp.teleportList[1][1].worldY = gp.tileSize* 11;
		
		gp.teleportList[2][0] = new Teleport(gp,2,24,24,1,9,10);
		gp.teleportList[2][0].worldX = gp.tileSize*24;
		gp.teleportList[2][0].worldY = gp.tileSize*24;
		gp.teleportList[2][1] = new Teleport(gp,2,30,17,3,34,18);
		gp.teleportList[2][1].worldX = gp.tileSize*30;
		gp.teleportList[2][1].worldY = gp.tileSize*17;
		
		
		gp.teleportList[3][0] = new Teleport(gp,3,21,33,1,39,11);
		gp.teleportList[3][0].worldX = gp.tileSize*21;
		gp.teleportList[3][0].worldY = gp.tileSize*33;
		gp.teleportList[3][1] = new Teleport(gp,3,33,18,2,31,17);
		gp.teleportList[3][1].worldX = gp.tileSize*33;
		gp.teleportList[3][1].worldY = gp.tileSize*18;
	}
	public void setInteractiveTile()
	{

		


	}
}
