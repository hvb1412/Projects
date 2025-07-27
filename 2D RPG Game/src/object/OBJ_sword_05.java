package object;

import entity.Item;
import main.GamePanel;

public class OBJ_sword_05  extends Item{

	public OBJ_sword_05(GamePanel gp) {
		super(gp);
     	type = type_sword;
		name = "Victory sword";
		down1 = setup("/objects/sword_05", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 6;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn VICTORY sword.";
		
		price = 30;
	}

}
