package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	public static final int WIDTH = 560; // 360 + 200(info panel)
	public static final int HEIGHT = 601;
	final int FPS = 60;
	final long targetTime = 1000/FPS;
	Thread gameThread;
	Game game;
	
	public static SoundAnimation music = new SoundAnimation();
	public static SoundAnimation soundEffect = new SoundAnimation();
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		
		this.addKeyListener(new Input());
		this.setFocusable(true);
		
		game = new Game();
	}
	
	public void createIcon(JFrame frame) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpeg"));
		Image image = icon.getImage();
		frame.setIconImage(image);
	}
	
	
	
	
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
		
		music.playSound(4, true);
		music.loopMusic();
	}

	
	
	@Override
	public void run() {
		long startTime,elapsedTime,delayTime;
		
		while(true) {
		
			startTime = System.nanoTime();
			update();
			repaint();
			elapsedTime = System.nanoTime() - startTime;
			delayTime = targetTime - elapsedTime / 1000000;
			
			if(delayTime < 0) {
				delayTime = 0;
			}
			try {
				Thread.sleep(delayTime);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	
	
	public void update() {
		if(Input.pause == false) {
			game.update();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		game.draw(g2);
		
	}

}
