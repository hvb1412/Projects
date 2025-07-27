package object;

import java.util.Random;

import entity.Item;
import entity.Player;
import main.GamePanel;

public class OBJ_Potion_Red extends Item{
	
	GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Red Potion";
		value = 5;
		down1 = setup("/objects/potion_red",3*gp.tileSize/4, 3*gp.tileSize/4);
		description = "[" + name + "]\nHeals your life by " + value + ".";
		
		price = 10;
	}
	
	public void use(Player player)
	{
		if(player.hp == player.maxHp) {
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You cannot drink more!";
			checkUse = false;
		}
		else {
			checkUse = true;
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
			if((player.hp + value) > player.maxHp) {
				player.hp = player.maxHp;
			}
			else {
				player.hp += value;
			}
		}
		
//		gp.playSE(2);
	}

}
