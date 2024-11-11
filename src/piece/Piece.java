package piece;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Game;
import main.GamePanel;
import main.Input;

public class Piece {
	
	public int id;
	
	public Block[] blockList = new Block[4];
	
	int fallingHandler = 0;
	public int direction = 0;
	boolean leftCollision,rightCollision,downCollision;
	public boolean active = true;
	
	public Block[] rotatedBlockList = new Block[4];
	
	public boolean deactivate;
	int deactivateCounter = 0;
	
	public void create(Color color) {
		// Creating a list of blocks to match with each piece
		
		blockList[0] = new Block(color);
		blockList[1] = new Block(color);
		blockList[2] = new Block(color);
		blockList[3] = new Block(color);
		rotatedBlockList[0] = new Block(color);
		rotatedBlockList[1] = new Block(color);
		rotatedBlockList[2] = new Block(color);
		rotatedBlockList[3] = new Block(color);
	}

	public void setXY(int x, int y) {}
	public void updateXY(int direction) {
		
		checkRotationCollision();
		
		if(leftCollision == false && rightCollision == false && downCollision == false) {
			this.direction = direction;
			
			blockList[0].x = rotatedBlockList[0].x;
			blockList[0].y = rotatedBlockList[0].y;
			blockList[1].x = rotatedBlockList[1].x;
			blockList[1].y = rotatedBlockList[1].y;
			blockList[2].x = rotatedBlockList[2].x;
			blockList[2].y = rotatedBlockList[2].y;
			blockList[3].x = rotatedBlockList[3].x;
			blockList[3].y = rotatedBlockList[3].y;
			
		}
		
	}
	
	// Rotation method declarations
	public void getDirection0() {}
	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	
	
	
	public void checkCollision() {
		leftCollision = false;
		rightCollision = false;
		downCollision = false;
		
		checkStaticCollision();
		
		for(int i=0; i < blockList.length; i++) {
			if(blockList[i].x == 0) { // left wall is 0
				leftCollision = true;
			}
		}
		
		for(int i=0; i < blockList.length; i++) {
			if(blockList[i].x + Block.SIZE == GamePanel.WIDTH - 200) {
				rightCollision = true;
			}
		}
		
		for(int i=0; i < blockList.length; i++) {
			if(blockList[i].y + Block.SIZE == GamePanel.HEIGHT - 1) {
				downCollision = true;
			}
		}
	}
	
	public void checkRotationCollision() {
		leftCollision = false;
		rightCollision = false;
		downCollision = false;
		
		checkStaticCollision();
		
		for(int i=0; i < blockList.length; i++) {
			if(rotatedBlockList[i].x < 0) { // left wall is 0
				leftCollision = true;
			}
		}
		
		for(int i=0; i < blockList.length; i++) {
			if(rotatedBlockList[i].x + Block.SIZE > GamePanel.WIDTH - 200) {
				rightCollision = true;
			}
		}
		
		for(int i=0; i < blockList.length; i++) {
			if(rotatedBlockList[i].y + Block.SIZE > GamePanel.HEIGHT - 1) {
				downCollision = true;
			}
		}
	}
	
	public void checkStaticCollision() {
		
		for(int i=0; i < Game.staticBlocks.size(); i++) {
			
			int occupiedX = Game.staticBlocks.get(i).x;
			int occupiedY = Game.staticBlocks.get(i).y;
			
			for(int j=0; j < blockList.length; j++) {
				if(blockList[j].x == occupiedX && blockList[j].y + Block.SIZE == occupiedY) {
					downCollision = true;
				}
			}
			
			for(int j=0; j < blockList.length; j++) {
				if(blockList[j].x - Block.SIZE == occupiedX && blockList[j].y == occupiedY) {
					leftCollision = true;
				}
			}
			
			for(int j=0; j < blockList.length; j++) {
				if(blockList[j].x + Block.SIZE == occupiedX && blockList[j].y == occupiedY) {
					rightCollision = true;
				}
			}
			
		}
		
	}
	
	private void permitSlide() {
		deactivateCounter++;
		
		if(deactivateCounter == 45) {
			
			deactivateCounter = 0;
			checkCollision();
			
			if(downCollision) {
				active = false;
				
			}
		}
		
	}
	
	public void update() {
		
		if(deactivate) {
			permitSlide();
		}
		
		if(downCollision) {
			deactivate = true;
		}
		else {
			fallingHandler++;
			if(fallingHandler == Game.fallSpeed) {
				// the piece should go down 1 row
				
				blockList[0].y += Block.SIZE;
				blockList[1].y += Block.SIZE;
				blockList[2].y += Block.SIZE;
				blockList[3].y += Block.SIZE;
				fallingHandler = 0;
			}
			
		}
		
		// Movements of pieces 
		
		if(Input.up) {
			switch(direction) {
				case 0:
					getDirection1();break;
				case 1:
					getDirection2();break;
				case 2:
					getDirection3();break;
				case 3:
					getDirection0();break;
			}
			GamePanel.soundEffect.playSound(1, false);
			Input.up = false;
		}


		checkCollision();
		
		
		if(Input.left) {
			if(leftCollision == false) {
				blockList[0].x -= Block.SIZE;
				blockList[1].x -= Block.SIZE;
				blockList[2].x -= Block.SIZE;
				blockList[3].x -= Block.SIZE;
				Input.left = false;
			}
		}
		if(Input.right) {
			if(rightCollision == false) {
				blockList[0].x += Block.SIZE;
				blockList[1].x += Block.SIZE;
				blockList[2].x += Block.SIZE;
				blockList[3].x += Block.SIZE;
				Input.right = false;
			}
		}
		if(Input.down) {
			if(downCollision == false) {
				blockList[0].y += Block.SIZE;
				blockList[1].y += Block.SIZE;
				blockList[2].y += Block.SIZE;
				blockList[3].y += Block.SIZE;
				fallingHandler = 0;
				Input.down = false;
			}
		}
			
		
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(blockList[0].color);
		g2.fillRect(blockList[0].x, blockList[0].y, Block.SIZE, Block.SIZE);
		g2.fillRect(blockList[1].x, blockList[1].y, Block.SIZE, Block.SIZE);
		g2.fillRect(blockList[2].x, blockList[2].y, Block.SIZE, Block.SIZE);
		g2.fillRect(blockList[3].x, blockList[3].y, Block.SIZE, Block.SIZE);
		
		g2.setColor(Color.BLACK);
		g2.drawRect(blockList[0].x, blockList[0].y, Block.SIZE, Block.SIZE);
		g2.drawRect(blockList[1].x, blockList[1].y, Block.SIZE, Block.SIZE);
		g2.drawRect(blockList[2].x, blockList[2].y, Block.SIZE, Block.SIZE);
		g2.drawRect(blockList[3].x, blockList[3].y, Block.SIZE, Block.SIZE);
		
		
	}
	
	
}	
	
