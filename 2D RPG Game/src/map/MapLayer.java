package map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class MapLayer {
	public MapTile[] tiles;
	public int layerTileNum[][];
	String path;
    public MapLayer(GamePanel gp, String path){
    	this.path = path;
    	tiles = new MapTile[100];
    	layerTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
    	getImage(gp);
    	loadLayer(gp);
    }
    public void getImage(GamePanel gp) {
    	tiles[0] = new MapTile(gp);
    	tiles[1] = new MapTile(gp, "/tiles/grand.png");
    	tiles[2] = new MapTile(gp, "/tiles/lane1.png");
    	tiles[3] = new MapTile(gp, "/tiles/lane2.png");
    	tiles[4] = new MapTile(gp, "/tiles/ngatu1.png");
    	tiles[5] = new MapTile(gp, "/tiles/ngatu2.png");
    	tiles[6] = new MapTile(gp, "/tiles/ngatu3.png");
    	tiles[7] = new MapTile(gp, "/tiles/ngatu4.png");
    	tiles[8] = new MapTile(gp, "/tiles/lane11.png");
    	tiles[9] = new MapTile(gp, "/tiles/lane12.png");
    	tiles[10] = new MapTile(gp, "/tiles/tree0.png", 48, 63, true, 25, 65, 25, 20);
    	tiles[11] = new MapTile(gp, "/tiles/tree1.png", true, 0, 30, 48, 18);
    	tiles[12] = new MapTile(gp, "/tiles/tree2.png", 80, 96, true,21 ,52 ,30 ,28);
    	tiles[13] = new MapTile(gp, "/tiles/rock1.png", true, 5, 20, 40, 20);
    	tiles[14] = new MapTile(gp, "/tiles/tree3.png", 80, 96, true,20 ,48 ,32 ,32);
    	tiles[15] = new MapTile(gp, "/tiles/tile006.png");
    	tiles[31] = new MapTile(gp,"/tiles/hut.png");    	
    	tiles[16] = new MapTile(gp,"/tiles/03brick13.png");
    	tiles[17] = new MapTile(gp, "/tiles/03ground_01.png",true);
    	tiles[18] = new MapTile(gp, "/tiles/03brick27.png");
    	tiles[19] = new MapTile(gp, "/tiles/03ground.png",true);
    	tiles[20] = new MapTile(gp, "/tiles/03Door04.png");
    	tiles[21] = new MapTile(gp, "/tiles/03land14.png");
    	tiles[22] = new MapTile(gp, "/tiles/03land18.png");
    	tiles[23] = new MapTile(gp, "/tiles/03land28.png");
    	tiles[24] = new MapTile(gp, "/tiles/03land29.png");
    	tiles[25] = new MapTile(gp, "/tiles/03floor.png");
    	tiles[26] = new MapTile(gp, "/tiles/03water24.png");
    	tiles[27] = new MapTile(gp, "/tiles/03water25.png",true);
    	tiles[28] = new MapTile(gp, "/tiles/03water26.png",true);
    	tiles[29] = new MapTile(gp, "/tiles/03water33.png");
    	tiles[30] = new MapTile(gp, "/tiles/03water36.png",true);
    	tiles[32] = new MapTile(gp, "/tiles/03brick22.png");
    	tiles[33] = new MapTile(gp, "/tiles/03brick33.png");
    	tiles[34] = new MapTile(gp,"/tiles/03water_brick.png",true);
    	tiles[35] = new MapTile(gp,"/tiles/column.png", 20,27,true,0,0,48, 96);
    	tiles[36] = new MapTile(gp,"/tiles/brick_new_03.png");
    	tiles[37] = new MapTile(gp,"/tiles/flora_01.png");
    	tiles[38] = new MapTile(gp,"/tiles/flora_02.png");
    	tiles[39] = new MapTile(gp,"/tiles/flora_03.png");
    	tiles[40] = new MapTile(gp,"/tiles/grave.png",true);
    	tiles[41] = new MapTile(gp,"/tiles/mushrooms.png");
    	tiles[42] = new MapTile(gp,"/tiles/inventory.png",true);
    	tiles[43] = new MapTile(gp,"/tiles/03brick_04.jpg", true);
    	tiles[44] = new MapTile(gp,"/tiles/03statue.png",50,150,true,15,140,32,32);
    	tiles[45]= new MapTile(gp,"/tiles/02water01.png",true);
    	tiles[46]= new MapTile(gp,"/tiles/02groundne.png");
    	tiles[47]= new MapTile(gp,"/tiles/02grass01.png");
    	tiles[48]= new MapTile(gp,"/tiles/02giua.png");
    	tiles[49]= new MapTile(gp,"/tiles/02trencung.png");
    	tiles[50]= new MapTile(gp,"/tiles/tilesheet_basic-ezgif.com-crop (1).png");
    	tiles[51]= new MapTile(gp,"/tiles/02duoi (2).png");
    	tiles[52]= new MapTile(gp,"/tiles/tilesheet_basic-ezgif.com-crop (3).png");
    	tiles[53]= new MapTile(gp,"/tiles/tilesheet_basic-ezgif.com-crop (4).png");
    	tiles[54]= new MapTile(gp,"/tiles/02duoine.png");
    	tiles[55]= new MapTile(gp,"/tiles/02duoi (2).png");
    	tiles[56]= new MapTile(gp,"/tiles/tilesheet_basic-ezgif.com-crop (2).png");
    	tiles[57]= new MapTile(gp,"/tiles/02all.png");
    	tiles[58]= new MapTile(gp,"/tiles/02datcanh.png");
    	tiles[59]= new MapTile(gp,"/tiles/02datnuoc.png");
    	tiles[60]= new MapTile(gp,"/tiles/02canh1.png");
    	tiles[61]= new MapTile(gp,"/tiles/02canh2.png");
    	tiles[62]= new MapTile(gp,"/tiles/02canh3.png");
    	tiles[63]= new MapTile(gp,"/tiles/02dat.png");
    	tiles[64]= new MapTile(gp,"/tiles/02canh4.png");
    	tiles[65]= new MapTile(gp,"/tiles/02canhdat.png");
    	tiles[66]= new MapTile(gp,"/tiles/02conuoc.png");
    	tiles[67]= new MapTile(gp,"/tiles/02dat.png");
		/*
		 * tiles[68]= new MapTile(gp,"/tiles/"); tiles[69]= new MapTile(gp,"/tiles/");
		 */
    	tiles[70]= new MapTile(gp,"/tiles/tilesheet_basic-ezgif.com-crop.png");
    	tiles[71]= new MapTile(gp,"/tiles/02tree.png",80, 96, true,20 ,48 ,32 ,32);
    	tiles[72]= new MapTile(gp,"/tiles/02stone.png");
    	tiles[73]= new MapTile(gp,"/tiles/02stone_big.png");
    	tiles[74]= new MapTile(gp,"/tiles/02greenlush.png");
    	tiles[75]= new MapTile(gp,"/tiles/02greenlush_01.png");
    	tiles[76]= new MapTile(gp,"/tiles/02greenlush_02.png");
    	tiles[77]= new MapTile(gp,"/tiles/02greenlush_03.png");
		/*
		 * tiles[34] = new MapTile(gp, null); tiles[35] = new MapTile(gp, null);
		 * tiles[36] = new MapTile(gp, null); tiles[37] = new MapTile(gp, null);
		 * tiles[38] = new MapTile(gp, null); tiles[39] = new MapTile(gp, null);
		 * tiles[40] = new MapTile(gp, null); tiles[41] = new MapTile(gp, null);
		 * tiles[42] = new MapTile(gp, null); tiles[43] = new MapTile(gp, null);
		 * tiles[44] = new MapTile(gp, null); tiles[45] = new MapTile(gp, null);
		 * tiles[46] = new MapTile(gp, null); tiles[47] = new MapTile(gp, null);
		 * tiles[48] = new MapTile(gp, null); tiles[49] = new MapTile(gp, null);
		 * tiles[50] = new MapTile(gp, null);
		 */
    	
    }
    public void loadLayer(GamePanel gp) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow ) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				while (col < gp.maxWorldCol) {
					int num = Integer.parseInt(numbers[col]);
					layerTileNum[row][col] = num;
					col++;
				}
				if (col >= gp.maxWorldCol) {
					col = 0;
				}
				row++;
			}
			br.close();
		} catch (Exception e) {
			
		}
	}
    public void draw(GamePanel gp,Graphics2D g2) {
    	int col = 0;
		int row = 0;
		int x, y;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			int worldX = col*gp.tileSize;
			int worldY = row*gp.tileSize;
			x = worldX - gp.player.worldX + gp.player.x;
			y = worldY- gp.player.worldY + gp.player.y;
			int titleNum = layerTileNum[row][col];
			if(titleNum != 0) {
				if(x >= -2*gp.tileSize && x <= gp.screenWidth + 2*gp.tileSize &&
						y >= -2* gp.tileSize && y <= gp.screenHeight + 2*gp.tileSize)
				tiles[titleNum].draw(g2, x, y);
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}		
	}

}
