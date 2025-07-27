package object;

import entity.Entity;
import entity.Item;
import main.GamePanel;

public class OBJ_Sword_Normal extends Item{

	GamePanel gp;
	public OBJ_Sword_Normal(GamePanel gp)
	{
		super(gp);
		
		type = type_sword;
		name = "Normal sword";
		down1 = setup("/objects/sword_normal", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 1;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn old sword.";
		
		price = 10;
	}
}
