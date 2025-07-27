package monster;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class MonsterCube extends Monster{
		Color color;
		int Frame;
	public MonsterCube(GamePanel gp) {
		super(gp);
		this.color = Color.RED;
		this.worldX = gp.tileSize;
		this.worldY = gp.tileSize;
		Frame = 0;
		// TODO Auto-generated constructor stub
	}
	public void update() {
		gp.quaiVatTanCong.QuaiVatDuoiTheoPlayer(this);
		if(abs(gp.player.worldX - this.worldX) <= 20 && abs(gp.player.worldY - this.worldY) <= 10 && gp.player.hp > 0) {
			this.attack();
		}
		if(this.color == Color.BLUE) {
			Frame++;
			if(Frame >= 60) {
				this.color = Color.RED;
				Frame = 0;
			}
		}
	}
	public void draw(Graphics2D g2) {
		this.x = this.worldX - gp.player.worldX + gp.player.x;
		this.y = this.worldY - gp.player.worldY + gp.player.y;
		g2.setColor(color);
		g2.fillRect(this.x, this.y, gp.tileSize, gp.tileSize);
	}
	public void attack() {
		this.color = Color.BLUE;
		gp.player.hurt();
	}
	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void checkDrop() {
		// TODO Auto-generated method stub
		
	}
	private int abs(int a) {
		if(a < 0) return -a;
		return a;
	}
	
}
