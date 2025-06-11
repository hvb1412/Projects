package monster;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Player;
import main.GamePanel;
import main.SpriteSheet;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class Skeleton extends Monster {
    private ChuDongTanCong ai;

    private int actionLockCounter = 0;
    
    public Skeleton(GamePanel gp) {
        super(gp);
        this.gp = gp;

        // Thông tin quái vật
        name = "Skeleton";
        speed = 2;
        maxHp = 25;
        hp = maxHp;
        attack = 15;
        defense = 2;
        exp = 5;
        alive = true;
        attackZone = new Rectangle();
		attackZone.x = this.height*34/70;
		attackZone.y = 0;
		attackZoneDefaultX = attackZone.x;
		attackZoneDefaultY = attackZone.y;
        getImage();
        getAttackImage();
        this.ai = gp.quaiVatTanCong;
    }

    public void getImage()
    {
        up1 = setup("/monster/skeleton_up_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        up2 = setup("/monster/skeleton_up_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        down1 = setup("/monster/skeleton_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        down2 = setup("/monster/skeleton_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        left1 = setup("/monster/skeleton_left_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        left2 = setup("/monster/skeleton_left_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        right1 = setup("/monster/skeleton_right_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        right2 = setup("/monster/skeleton_right_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
    }
    public void getAttackImage()
    {
        attackUp1 = setup("/monster/skeleton_attack_up_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackUp2 = setup("/monster/skeleton_attack_up_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackDown1 = setup("/monster/skeleton_attack_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackDown2 = setup("/monster/skeleton_attack_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackLeft1 = setup("/monster/skeleton_attack_left_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackLeft2 = setup("/monster/skeleton_attack_left_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackRight1 = setup("/monster/skeleton_attack_right_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackRight2 = setup("/monster/skeleton_attack_right_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
    }
    @Override
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
//        ai.QuaiVatDuoiTheoPlayer(this);
        ai.attackByTouch(this);
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;

        if (i < 50) {
        	dropItem(new OBJ_ManaCrystal(gp));
            
        } else if (i < 75) {
            dropItem(new OBJ_Heart(gp));
        } else {
        	dropItem(new OBJ_Coin_Bronze(gp));
        }
    }
    public void updateDrawAttack(int screenX, int screenY) {
    	switch (direction) {
		case "up":
			if (!attacking) {
				image = (spriteNum == 1) ? up1 : up2;
			} else {
				screenY -= gp.tileSize;
				image = (spriteNum == 1) ? attackUp1 : attackUp2;
			}
			break;
		case "down":
			image = (!attacking) ? (spriteNum == 1 ? down1 : down2) : (spriteNum == 1 ? attackDown1 : attackDown2);
			break;
		case "left":
			if (!attacking) {
				image = (spriteNum == 1) ? left1 : left2;
			} else {
				screenX -= gp.tileSize;
				image = (spriteNum == 1) ? attackLeft1 : attackLeft2;
			}
			break;
		case "right":
			image = (!attacking) ? (spriteNum == 1 ? right1 : right2) : (spriteNum == 1 ? attackRight1 : attackRight2);
			break;
		}
    }
}
