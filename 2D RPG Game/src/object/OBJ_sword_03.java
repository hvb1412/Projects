package object;

import entity.Item;
import main.GamePanel;

public class OBJ_sword_03 extends Item {

	public OBJ_sword_03(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
    	type = type_sword;
		name = "Jusdice sword";
		down1 = setup("/objects/sword_03", 3*gp.tileSize/4, 3*gp.tileSize/4);
		attackValue = 4;
//		attackArea.width = 36;
//		attackArea.height = 36;
		description = "[" + name + "]\nAn Jusdice sword ON BEHALF OF GOD.";
		
		price = 17;
	}

}
