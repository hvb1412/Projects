package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class PlayerInfoBox {
	GamePanel gp;
	public int screenX, screenY;
	public BufferedImage boxImage; //58, 16  fix 16--> 72
	public BufferedImage avatarImage;
	public final double healthBarHeight = 30D;  //21, 0, 29, 8
	public final double healthBarWidthMax = 122D;
	public final int healthBarX = 94;
	public final int healthBarY = 5;
	public double healthBarWidth = 122D;
	public final double manaBarHeight = 13.5D;  //21, 8, 29, 3
	public final double manaBarWidthMax = 130.5D;
	public final int manaBarX = 94;
	public final int manaBarY = 36;
	public double manaBarWidth = 130.5D;
	public final double saitamaBarHeight = 5D;  //13, 14, 40, 1
	public final double saitamaBarWidthMax = 180.0D;
	public final int saitamaBarX = 54;
	public final int saitamaBarY = 59;
	public double saitamaBarWidth = 180.0D;
	
	public PlayerInfoBox(GamePanel gp) {
		this.gp = gp;
		screenX = gp.tileSize/2;
		screenY = gp.tileSize/2;
		getImage();
	}
	
	public void update() {
		healthBarWidth = gp.player.hp * healthBarWidthMax/ gp.player.maxHp;
		manaBarWidth = gp.player.mp * manaBarWidthMax/ gp.player.maxMp;
		saitamaBarWidth = gp.player.saitama * saitamaBarWidthMax/ gp.player.MAX_SAITAMA;
		
		
	}
	public void getImage() {
		try {
			boxImage = ImageIO.read(getClass().getResourceAsStream("/InfoBox/boxImage.png"));
			avatarImage = ImageIO.read(getClass().getResourceAsStream("/InfoBox/avatar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(screenX + healthBarX, screenY + healthBarY, (int)healthBarWidthMax, (int)healthBarHeight);
		g2.setColor(Color.red);
		g2.fillRect(screenX + healthBarX, screenY + healthBarY, (int)healthBarWidth, (int)healthBarHeight);
		
		g2.setColor(new Color(89, 140, 153));
		g2.fillRect(screenX + manaBarX, screenY + manaBarY, (int)manaBarWidthMax, (int)manaBarHeight);
		g2.setColor(Color.BLUE);
		g2.fillRect(screenX + manaBarX, screenY + manaBarY, (int)manaBarWidth, (int)manaBarHeight);
		g2.setColor(Color.gray);
		g2.fillRect(screenX + saitamaBarX, screenY + saitamaBarY, (int)saitamaBarWidthMax, (int)saitamaBarHeight);
		if(gp.player.tired) {
			g2.setColor(Color.RED);
		}
		else {
			g2.setColor(Color.ORANGE);
		}
		
		g2.fillRect(screenX + saitamaBarX, screenY + saitamaBarY, (int)saitamaBarWidth, (int)saitamaBarHeight);
		
		g2.drawImage(this.boxImage, this.screenX, this.screenY, gp.tileSize*3/2*58/16 ,gp.tileSize*3/2, null);
		g2.drawImage(this.avatarImage, this.screenX + 4, this.screenY + 10, 48, 48, null);
	}
}
