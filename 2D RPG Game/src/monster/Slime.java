package monster;

import entity.Entity;
import main.GamePanel;
import main.SpriteSheet;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class Slime extends Monster {
	
	GamePanel gp;
    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        // Thông tin quái vật
        name = "Green Slime";
        direction = "up";
        type = type_monster;
        speed = 1;
        maxHp = 15;
        hp = maxHp;

        attack = 5;
        defense = 0;
        exp = 2;
        
        
        getImage(); 
    }

    public void getImage() {
        up1 = setup("/monster/greenslime_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        up2 = setup("/monster/greenslime_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        down1 = setup("/monster/greenslime_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        down2 = setup("/monster/greenslime_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        left1 = setup("/monster/greenslime_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        left2 = setup("/monster/greenslime_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        right1 = setup("/monster/greenslime_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        right2 = setup("/monster/greenslime_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
    }
    
    public void setAction() {
        actionLockCounter++;
        
       
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
        attackDelayCounter++;
		if(attackDelayCounter == 25) {
			gp.quaiVatTanCong.attackByTouch(this);
			attacking = true;
			attackDelayCounter = 0;
		}
        
//
//        int i = new Random().nextInt(100) + 1;
//        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
//            projectile.set(worldX, worldY, direction, true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        }
    }

    @Override
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;

        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        } else {
            dropItem(new OBJ_Heart(gp));
        }
    }
    public void damageReaction()
    {
    	actionLockCounter = 0;
		direction = gp.player.direction;
    }
}
