package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Entity;
import map.MapLayer;
import map.MapTile;

public class ProcessFrontBehindEntity {
	GamePanel gp;
	public ProcessFrontBehindEntity(GamePanel gp) {
		this.gp = gp;
	}


	public void draw(Graphics2D g2, MapLayer layer, ArrayList<Entity> entities) {
		int col = 0;
		int row = 0;
		int x, y;
		int [][] layerTileNum = layer.layerTileNum;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			int worldX = col*gp.tileSize;
			int worldY = row*gp.tileSize;
			x = worldX - gp.player.worldX + gp.player.x;
			y = worldY- gp.player.worldY + gp.player.y;
			int titleNum = layerTileNum[row][col];
			if(titleNum != 0) {
				if(x >= -2*gp.tileSize && x <= gp.screenWidth + 2*gp.tileSize &&
						y >= -2* gp.tileSize && y <= gp.screenHeight + 2*gp.tileSize) {
					for(Entity e: entities) {
						if(e.worldX >= worldX && e.worldX <= worldX + layer.tiles[titleNum].getWidth() && e.worldY >= worldY && e.worldY <= (worldY + layer.tiles[titleNum].collisionArea.y + layer.tiles[titleNum].collisionArea.height) &&
								e.worldY + e.solidAreaDefaultY + e.solidArea.height >=  (worldY + layer.tiles[titleNum].collisionArea.y + layer.tiles[titleNum].collisionArea.height)) {
							e.draw(g2);
						}
					}
				}
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		
		}
	}
}

