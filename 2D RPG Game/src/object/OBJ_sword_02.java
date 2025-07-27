package object;

import entity.Item;
import main.GamePanel;

public class OBJ_sword_02 extends Item {

	public OBJ_sword_02(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		
		type = type_sword;
		name = "Sun sword";
		down1 = setup("/objects/sword_02", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 5;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn Sun sword OF ENERGY.";
		
		price = 18;
	}
	
}
