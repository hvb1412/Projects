package object;

import entity.Item;
import main.GamePanel;

public class OBJ_sword_06 extends Item {

	public OBJ_sword_06(GamePanel gp) {
        super(gp);
		type = type_sword;
		name = "King sword";
		down1 = setup("/objects/sword_06", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 4;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn old sword that was used by KING GIHAM.";
		
		price = 22;
	}

}
