package object;

import entity.Item;
import main.GamePanel;

public class OBJ_sword_04 extends Item {

	public OBJ_sword_04(GamePanel gp) {
		super(gp);
		type = type_sword;
		name = "Titan sword";
		down1 = setup("/objects/sword_04", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 3;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn precious sword.";
		
		price = 15;
	}

}
