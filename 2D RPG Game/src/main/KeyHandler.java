package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, beamPressed;
	public boolean attackPressed, damagePressed, diePressed, refreshPressed, upPowerPressed, boomPressed;
	public boolean runPressed;
	public KeyHandler(GamePanel gp)
	{
		this.gp = gp;
	}
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			titleState(code);
		}
		//PLAY STATE
		else if(gp.gameState == gp.playState)
		{
			playState(code);
		}
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState)
		{
			pauseState(code);
		}
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState)
		{
			dialogueState(code);
		}
		
		//CHARACTER STATE
		else if(gp.gameState == gp.characterState)
		{
			characterState(code);
			playerInventory(code);
		}
		//OPTIONS STATE
		else if(gp.gameState == gp.optionsState)
		{
			optionsState(code);
		}
		//GAME OVER STATE
		else if(gp.gameState == gp.gameOverState)
		{
//			gp.stopMusic();
//			gp.playSE(3);
			gameOverState(code);
			
			
		}
		//TRADE STATE
		else if(gp.gameState == gp.tradeState)
		{
			tradeState(code);
		}
	}
	
	public void titleState(int code)
	{
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
			{
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0)
				{
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
			{
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2)
				{
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0)
				{
					gp.gameState = gp.playState;
					
					
				}
				if(gp.ui.commandNum == 1)
				{
					//ADD LATER
				}
				if(gp.ui.commandNum == 2)
				{
					System.exit(0); 
				}
			}		
	}
	public void playState(int code)
	{
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			upPressed = true;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			leftPressed = true;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			downPressed = true;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P)
		{
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C)
		{
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE)
		{
			gp.gameState = gp.optionsState;
		}
		if(code == KeyEvent.VK_U) {
			shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_J) {
			attackPressed = true;
		}
		if (code == KeyEvent.VK_I) {
			damagePressed = true;
		}
		if (code == KeyEvent.VK_K) {
			diePressed = true;
		}
		if (code == KeyEvent.VK_SHIFT) {
			runPressed = true;
		}
		if (code == KeyEvent.VK_R) {
			refreshPressed = true;
		}
		if (code == KeyEvent.VK_I) {
			beamPressed = true;
		}
		if (code == KeyEvent.VK_K) {
			upPowerPressed = true;
		}
		if (code == KeyEvent.VK_O) {
			boomPressed = true;
		}
	}
	public void pauseState(int code)
	{
		if(code == KeyEvent.VK_P)
		{
			gp.gameState = gp.playState;
		}
	}
	public void dialogueState(int code)
	{
		if(code == KeyEvent.VK_ENTER)
		{
			gp.gameState = gp.playState;
		}
	}
	public void characterState(int code)
	{
		if(code == KeyEvent.VK_C)
		{
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			if(gp.ui.slotRow != 0)
			{
				gp.ui.slotRow--;
			
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			if(gp.ui.slotRow != 3)
			{
				gp.ui.slotRow++;
		
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			if(gp.ui.slotCol != 0)
			{
				gp.ui.slotCol--;
	
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			if(gp.ui.slotCol != 4)
			{
				gp.ui.slotCol++;
				
			}
		}
		if(code == KeyEvent.VK_ENTER)
		{
			gp.player.selectItem();
		}
	}
	public void optionsState(int code)
	{
		if(code == KeyEvent.VK_ESCAPE)
		{
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState)
		{
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0)
			{
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			gp.ui.commandNum++;
			if(gp.ui.commandNum > maxCommandNum)
			{
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			if(gp.ui.subState == 0)
			{
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0)
				{
					gp.music.volumeScale--;
					gp.music.checkVolume();
//					gp.playSE(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0)
				{
					gp.se.volumeScale--;
//					gp.playSE(9);
				}
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			if(gp.ui.subState == 0)
			{
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5)
				{
					gp.music.volumeScale++;
					gp.music.checkVolume();
//					gp.playSE(9);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5)
				{
					gp.se.volumeScale++;
//					gp.playSE(9);
				}
			}
		}
	}
	public void gameOverState(int code)
	{
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0)
			{
				gp.ui.commandNum = 1;
			}
			
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1)
			{
				gp.ui.commandNum = 0;
			}
			
		}
		if(code == KeyEvent.VK_ENTER)
		{
			if(gp.ui.commandNum == 0)
			{
				gp.gameState = gp.playState;
				gp.retry();
//				gp.playMusic(0);
			}
			else if(gp.ui.commandNum == 1)
			{
				gp.gameState = gp.titleState;
				gp.restart();
			}
		}
	}
	public void tradeState(int code)
	{
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
		
		if(gp.ui.subState == 0)
		{
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
			{
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0)
				{
					gp.ui.commandNum = 2;
				}
//				gp.playSE(9);
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
			{
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2)
				{
					gp.ui.commandNum = 0;
				}
//				gp.playSE(9);
			}
		}
		if(gp.ui.subState == 1)
		{
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE)
			{
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2)
		{
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE)
			{
				gp.ui.subState = 0;
			}
		}
	}
	public void playerInventory(int code)
	{
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			if(gp.ui.playerSlotRow != 0)
			{
				gp.ui.playerSlotRow--;
//				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			if(gp.ui.playerSlotRow != 3)
			{
				gp.ui.playerSlotRow++;
//				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			if(gp.ui.playerSlotCol != 0)
			{
				gp.ui.playerSlotCol--;
//				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			if(gp.ui.playerSlotCol != 4)
			{
				gp.ui.playerSlotCol++;
//				gp.playSE(9);
			}
		}
	}
	public void npcInventory(int code)
	{
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			if(gp.ui.npcSlotRow != 0)
			{
				gp.ui.npcSlotRow--;
//				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			if(gp.ui.npcSlotRow != 3)
			{
				gp.ui.npcSlotRow++;
//				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			if(gp.ui.npcSlotCol != 0)
			{
				gp.ui.npcSlotCol--;
//				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			if(gp.ui.npcSlotCol != 4)
			{
				gp.ui.npcSlotCol++;
//				gp.playSE(9);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
		{
			upPressed = false;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
		{
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
		{
			downPressed = false;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
		{
			rightPressed = false;
		}
		if(code == KeyEvent.VK_U) {
			shotKeyPressed = false;
		}      
	    if (code == KeyEvent.VK_I) {
	    	damagePressed = false;
	    }
	            
	    if (code == KeyEvent.VK_K) {
	        diePressed = false;
	    }
	        
	    if (code == KeyEvent.VK_SHIFT) {
			runPressed = false;
		}
	    if (code == KeyEvent.VK_R) {
			refreshPressed = false;
		}
	    if (code == KeyEvent.VK_I) {
			beamPressed = false;
		}
	    if (code == KeyEvent.VK_K) {
			upPowerPressed = false;
		}
	    if (code == KeyEvent.VK_O) {
			boomPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
