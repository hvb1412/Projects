package map;

import java.awt.Graphics2D;

import main.GamePanel;

public class Map {
	private GamePanel gp;
	private MapLayer layer1;
	private MapLayer layer2;
	
	public MapLayer getLayer2() {
		return layer2;
	}

	public Map(GamePanel gp,String path1, String path2) {
		this.gp = gp;
		layer1 = new MapLayer(gp, path1);
		layer2 = new MapLayer(gp, path2);
	}
	
	public void draw(Graphics2D g2, int i) {
		switch(i) {
		case 1:
			layer1.draw(gp, g2);
			break;
		case 2:
		    layer2.draw(gp, g2);
		}
	}
}
