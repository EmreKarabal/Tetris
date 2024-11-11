package main;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;

import java.net.URL;

public class SoundAnimation {
	
	Clip musicClip;
	URL urlList[] = new URL[5];

	
	public SoundAnimation() {
		
		urlList[0] = getClass().getResource("/deleteLine.wav");
		urlList[1] = getClass().getResource("/rotation.wav");
		urlList[2] = getClass().getResource("/touchFloor.wav");
		urlList[3] = getClass().getResource("/gameOver.wav");
		urlList[4] = getClass().getResource("/theme.wav");
		
	}
	
	
	public void playSound(int i, boolean isMusic) {
		
		try {
			
			AudioInputStream stream = AudioSystem.getAudioInputStream(urlList[i]);
			Clip clip = AudioSystem.getClip();
			
			if(isMusic == true) {
				musicClip = clip;
			}

			clip.open(stream);
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if(event.getType() == Type.STOP){
						clip.close();
					}
				}
			});

			stream.close();
			clip.start();
			
			
		} catch(Exception e) {
			
			}
		
	}

	public void loopMusic(){
		musicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic(){
		musicClip.stop();
		musicClip.close();
	}



}
