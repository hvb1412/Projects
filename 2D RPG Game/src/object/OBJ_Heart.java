package object;

import entity.Entity;
import entity.Item;
import entity.Player;
import main.GamePanel;

public class OBJ_Heart extends Item {
	public OBJ_Heart(GamePanel gp)
	{
		super(gp);
		
		type = type_pickUpOnly;
		name = "Heart";
		value = 2;
		down1 = setup("/objects/heart_full", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image = setup("/objects/heart_full", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image2 = setup("/objects/heart_half", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image3 = setup("/objects/heart_blank", 3*gp.tileSize/4, 3*gp.tileSize/4);
		
	}
	public void use(Player entity)
	{
		gp.ui.addMessage("Life + " + value);
		if(entity.hp + value > entity.maxHp) {
			entity.hp = entity.maxHp;
		}
		else {
			entity.hp += value;
		}
	}
}
