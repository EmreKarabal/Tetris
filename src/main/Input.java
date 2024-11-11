package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener{

	public static boolean up,down,left,right,pause, quit,replay = false;
	
	@Override
	public void keyTyped(KeyEvent evt) {}

	@Override
	public void keyPressed(KeyEvent evt) {
		int keyCode = evt.getKeyCode();
		switch(keyCode) {
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
				down=true;
				break;
			case KeyEvent.VK_RIGHT:
				right=true;
				break;
			case KeyEvent.VK_LEFT:
				left=true;
				break;
			case KeyEvent.VK_ESCAPE:
				if(pause) {
					pause = false;
					GamePanel.music.playSound(4, true);
					GamePanel.music.loopMusic();
				}
				else {
					pause = true;
					GamePanel.music.stopMusic();
				}
				break;
			case KeyEvent.VK_R:
				replay = true;
				break;
			case KeyEvent.VK_Q:
				quit = true;
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent evt) {}

}
