package entity;

import main.GamePanel;

public abstract class NPC extends Character{
	public int dialoguesIndex;
	public String dialogues[] = new String[20];
	public void speak() {
		if(dialogues[dialoguesIndex] == null)
		{
			dialoguesIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialoguesIndex];
		dialoguesIndex++;
		
		switch(gp.player.direction)
		{
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public NPC(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		dialoguesIndex = 0;
	}
	
}
