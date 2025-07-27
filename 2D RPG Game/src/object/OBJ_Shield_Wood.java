package object;

import entity.Entity;
import entity.Item;
import main.GamePanel;

public class OBJ_Shield_Wood extends Item{
	
	GamePanel gp;
	
	public OBJ_Shield_Wood(GamePanel gp)
	{
		super(gp);
		
		type = type_shield;
		name = "Wood Shield";
		down1 = setup("/objects/shield_wood", 3*gp.tileSize/4, 3*gp.tileSize/4);
		defenseValue = 3;
		description = "[" + name + "]\nMade by wood.";
		
		price = 10;
	}
}
