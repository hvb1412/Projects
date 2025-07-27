package object;

import entity.Entity;
import entity.Item;
import entity.Player;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Item {


    public OBJ_Coin_Bronze(GamePanel gp) {
    	super(gp);

        type = type_pickUpOnly;
        name = "Bronze Coin";
        value = 1; 

        down1 = setup("/objects/coin_bronze", 3*gp.tileSize/4, 3*gp.tileSize/4);
        image = down1;
    }

    public void use(Player entity) {
        gp.ui.addMessage("Gold + " + value);
        entity.coin += value;
    }
}
