package piece;

import java.awt.Color;

public class Piece_I extends Piece {
	
	public int id = 1;

	
	
	
	public Piece_I() {
		create(Color.cyan);
	}
	
	public void setXY(int x, int y) {
		
		
		/*
		 *    1
		 *    1
		 *    1
		 *    1
		 * 
		 */
		
		blockList[0].x = x;
		blockList[0].y = y;
		blockList[1].x = x;
		blockList[1].y = y - Block.SIZE;
		blockList[2].x = x;
		blockList[2].y = y + Block.SIZE;
		blockList[3].x = x;
		blockList[3].y = y + (Block.SIZE * 2);
		
		
	}
	
	
	// Rotation methods
	public void getDirection0() {
		
		//	1
		//	1
		//	1
		//	1
		
		rotatedBlockList[0].x = blockList[0].x;
		rotatedBlockList[0].y = blockList[0].y;
		rotatedBlockList[1].x = blockList[0].x;
		rotatedBlockList[1].y = blockList[0].y - Block.SIZE;
		rotatedBlockList[2].x = blockList[0].x;
		rotatedBlockList[2].y = blockList[0].y + Block.SIZE;
		rotatedBlockList[3].x = blockList[0].x;
		rotatedBlockList[3].y = blockList[0].y + (Block.SIZE * 2);
		
		updateXY(0);
	}
	public void getDirection1() {
		//	1111	
		
		rotatedBlockList[0].x = blockList[0].x;
		rotatedBlockList[0].y = blockList[0].y;
		rotatedBlockList[1].x = blockList[0].x - Block.SIZE;
		rotatedBlockList[1].y = blockList[0].y;
		rotatedBlockList[2].x = blockList[0].x + Block.SIZE;
		rotatedBlockList[2].y = blockList[0].y;
		rotatedBlockList[3].x = blockList[0].x + (Block.SIZE * 2);
		rotatedBlockList[3].y = blockList[0].y;
		
		updateXY(1);
		
	}
	public void getDirection2() {
		getDirection0();
		
	}
	public void getDirection3() {
		getDirection1();
		
	}
	
	
	
}
