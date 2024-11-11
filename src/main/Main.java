package main;



import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;




public class Main {
	

	public static void main(String[] args) {
		
		
		
		JFrame window = new JFrame("Tetris");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		gamePanel.createIcon(window);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
			
		
		gamePanel.startGame();
		
		
		
			
		

		
		
	}
	

}
