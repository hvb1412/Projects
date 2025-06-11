package object;

import entity.Item;
import main.GamePanel;

public class OBJ_sword_01 extends Item {

	public OBJ_sword_01(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		
		type = type_sword;
		name = "Crystal sword";
		down1 = setup("/objects/sword_01", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 1;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn Crystal sword of POWER.";
		
		price = 20;
	}

}
