package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Teleport extends Entity {
    public int fromMap, fromCol, fromRow;
    public int toMap, toCol, toRow;
    public Teleport(GamePanel gp, int fromMap, int fromCol, int fromRow, int toMap, int toCol, int toRow) {
        super(gp);
        this.fromMap = fromMap;
        this.fromCol = fromCol;
        this.fromRow = fromRow;
        this.toMap = toMap;
        this.toCol = toCol;
        this.toRow = toRow;

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        collision = true;
        getImage();
    }
    public void getImage() {
    	image =  setup("/tiles/03Door04",gp.tileSize,gp.tileSize);
    }

    public void update() {
        // Kiểm tra va chạm với player
        Rectangle playerArea = new Rectangle(gp.player.worldX + gp.player.solidArea.x, gp.player.worldY + gp.player.solidArea.y, gp.player.solidArea.width, gp.player.solidArea.height);
        Rectangle teleportArea = new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);

        if (playerArea.intersects(teleportArea)) {
            gp.num_CurrentMap = toMap;
            gp.currentMap = gp.maps[toMap];
            //gp.gameState= gp.transitionState;
            gp.player.worldX = toCol * gp.tileSize;
            gp.player.worldY = toRow * gp.tileSize;
        }
    }

    public void draw(Graphics2D g2) {
			if(image == null) return ;
		  int screenX = worldX - gp.player.worldX + gp.player.x; 
		  int screenY = worldY -gp.player.worldY + gp.player.y; 
		  g2.drawImage(image, screenX, screenY, null);
		  if (gp.testMode) { g2.setColor(Color.RED); g2.drawRect(screenX + solidArea.x,
		  screenY + solidArea.y, solidArea.width, solidArea.height); }
		 
    }
}
/*
 * package map;
 * 
 * import java.awt.Graphics2D;
 * 
 * import entity.Entity; import main.GamePanel;
 * 
 * public class Teleport extends Entity{ public int fromMap, fromCol, fromRow;
 * public int toMap, toCol, toRow; private GamePanel gp; public Teleport(int
 * fromMap, int fromCol, int fromRow, int toMap, int toCol, int toRow) {
 * this.fromMap = fromMap; this.fromCol = fromCol; this.fromRow = fromRow;
 * this.toMap = toMap; this.toCol = toCol; this.toRow = toRow;
 * 
 * } public void draw(Graphics2D g2) { int screenX = worldX - gp.player.worldX +
 * gp.player.x; int screenY = worldY - gp.player.worldY + gp.player.y;
 * g2.drawImage(image, screenX, screenY, null); }; }
 */