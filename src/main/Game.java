package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import piece.*;

public class Game {
	
	// Game panel (20x12)
	
	final int ROW = 20;
	final int COL = 12;
	final int WIDTH = 360;
	final int HEIGHT = 600;
	final int CELL_SIZE = 30;
	
	// Piece
	Piece currentPiece;
	Piece nextPiece;
	final int START_X;
	final int START_Y;
	
	public static ArrayList<Block> staticBlocks = new ArrayList<>();
	public static boolean gameOver = false;
	private boolean lineComplete = false;
	public static boolean replayGame = false;
	public static boolean quitGame = false;
	
	int score = 0;
	public static int fallSpeed = 30; // Piece falls automatically every 30 frames = 1/2 seconds
	
	
	public Game(){
		START_X = WIDTH / 2 - CELL_SIZE;
		START_Y = CELL_SIZE;
		
		currentPiece = pickPiece();
		currentPiece.setXY(START_X, START_Y);
		
		nextPiece = pickPiece();
		nextPiece.setXY(445, 170);
		
		
	}
	
	
	// Random piece selection
	public Piece pickPiece() {
		Piece piece = null;
		Random random = new Random();
		int x = random.nextInt(7);
		switch(x) {
			case 0:
				piece = new Piece_I();break;
			case 1:
				piece = new Piece_J();break;
			case 2:
				piece = new Piece_L();break;
			case 3:
				piece = new Piece_O();break;
			case 4:
				piece = new Piece_S();break;
			case 5:
				piece = new Piece_T();break;
			case 6:
				piece = new Piece_Z();break;
		}
		return piece;
	}
	

	
	public void checkCompleteLine() {
		
	int x = 0;
	int y = 0;
	int occupiedBlockCount = 0;
	
	while(x < GamePanel.WIDTH - 200 && y < GamePanel.HEIGHT - 1) {
		
		for(int i=0;i< staticBlocks.size();i++) {
			
			if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
				// if both x and y equal to a static block add it to counter
				occupiedBlockCount++;
			}
			
		}
	
		
		
		x += Block.SIZE;
		
		if(x == GamePanel.WIDTH - 200) {
			
			
			if(occupiedBlockCount == 12) {
				
				for(int i=staticBlocks.size() - 1; i > -1;i--) {
					if(staticBlocks.get(i).y == y) {
						lineComplete = true;
						GamePanel.soundEffect.playSound(0, false);
						staticBlocks.remove(i);
						
					}
				}
				
				// if we clear a line and there are some blocks above that line 
				// we should push down those blocks
				
				for(int i=0; i < staticBlocks.size(); i++) {
					if(staticBlocks.get(i).y < y) {
						staticBlocks.get(i).y += Block.SIZE;
					}
				}
				
				
			}
			
				if(lineComplete) {
					score+=9;
					lineComplete = false;
				}
				occupiedBlockCount = 0;
				x = 0;
				y += Block.SIZE;
			
			
		}
		
	}
		
		
	
	}
	
	
	
	
	
	
	public void update() {
		
	
		
		if(currentPiece.active == false) {
			staticBlocks.add(currentPiece.blockList[0]);
			staticBlocks.add(currentPiece.blockList[1]);
			staticBlocks.add(currentPiece.blockList[2]);
			staticBlocks.add(currentPiece.blockList[3]);
			
			
			
			// check gameOver
			if(currentPiece.blockList[0].x == START_X && currentPiece.blockList[0].y == START_Y) {
				
				GamePanel.music.stopMusic();
				GamePanel.soundEffect.playSound(3, false);
				gameOver = true;
				
				if(gameOver == true || Input.quit == true) {
					quitGame = true;
				}
				
				else if(gameOver == true || Input.replay == true) {
					replayGame = true;
				}
				
			}
			
			
			
			
			currentPiece.deactivate = false;
			
			GamePanel.soundEffect.playSound(2, false);
			
			// get the next piece
			
			currentPiece = nextPiece;
			currentPiece.setXY(START_X, START_Y);
			nextPiece = pickPiece();
			nextPiece.setXY(445, 170);
			score+=1;
			checkCompleteLine();
		}
		else if(!gameOver){
			currentPiece.update();
		}
		
		
		
	}
	
	
	
	public void draw(Graphics2D g2) {
		// Draw game grid
		
		g2.setColor(Color.lightGray);
		for(int row = 0; row < COL; row++) {
			for(int col = 0; col < ROW; col++) {
				g2.drawRect((row * CELL_SIZE), (col * CELL_SIZE), CELL_SIZE, CELL_SIZE);
			}
		}
		
		// Draw info panel
		g2.drawRect(380, 100, 150, 180);
		g2.setFont(new Font("Franklin Gothic", Font.BOLD,14));
		g2.drawString("NEXT", 435, 120);
		
		//Draw the currentPiece
		
		if(currentPiece != null) {
			currentPiece.draw(g2);
		}
		
		nextPiece.draw(g2);
		//Draw static blocks
		
		for(int i=0;i<staticBlocks.size();i++) {
			staticBlocks.get(i).draw(g2);
			g2.setColor(Color.lightGray);
			g2.drawRect(staticBlocks.get(i).x, staticBlocks.get(i).y, Block.SIZE, Block.SIZE);	
		}
		
		//Draw Score
		g2.setColor(Color.white);
		g2.setFont(new Font("Franklin Gothic", Font.PLAIN, 25));
		g2.drawString("Score: " + score, 380,550);
		
		
		//Draw Game Over text
		
		if(gameOver) {
			g2.setColor(Color.white);
			g2.setFont(new Font("Franklin Gothic", Font.BOLD, 25));
			g2.drawString("GAME OVER", 380 ,  360);
			
			g2.setFont(new Font("Franklin Gothic", Font.PLAIN, 18));
			g2.drawString("Press R to replay", 380, 380);
			g2.drawString("Press Q to quit", 380, 400);
			
		}
		
		if(Input.pause == true && !gameOver) {
			g2.setColor(Color.white);
			g2.setFont(new Font("Franklin Gothic", Font.BOLD,32));
			g2.drawString("PAUSED", 380 , 60);
		}
		
	}
	
}
