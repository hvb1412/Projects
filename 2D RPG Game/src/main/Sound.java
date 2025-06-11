package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound implements Playable{
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	
	public Sound(){
		soundURL[0] =getClass().getResource("/sounds/Theme.wav");
		soundURL[1] =getClass().getResource("/sounds/CollectItem.wav");
		soundURL[2] =getClass().getResource("/sounds/gameover.wav");
		//soundURL[3] =getClass().getResource("/sounds/MonsterSword.mp3");
		soundURL[4] =getClass().getResource("/sounds/player_attack.wav");
		soundURL[5] =getClass().getResource("/sounds/receivedamage.wav");
		soundURL[6] =getClass().getResource("/sounds/teleport.wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	public void checkVolume()
	{
		switch(volumeScale)
		{
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
}
