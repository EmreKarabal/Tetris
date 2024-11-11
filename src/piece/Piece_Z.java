package piece;

import java.awt.Color;

public class Piece_Z extends Piece {
	
	public int id = 7;

	/*
	 * 		11
	 * 		 11
	 * 
	 */
	
	public Piece_Z() {
		create(Color.green);
	}
	
	
	public void setXY(int x, int y) {
		
		blockList[0].x = x;
		blockList[0].y = y;
		blockList[1].x = x - Block.SIZE;
		blockList[1].y = y;
		blockList[2].x = x;
		blockList[2].y = y + Block.SIZE;
		blockList[3].x = x + Block.SIZE;
		blockList[3].y = y + Block.SIZE;
		
		
	}
	
	
	// Rotation methods
	public void getDirection0() {
		
		//	11
		//	 11
		
		rotatedBlockList[0].x = blockList[0].x;
		rotatedBlockList[0].y = blockList[0].y;
		rotatedBlockList[1].x = blockList[0].x - Block.SIZE;
		rotatedBlockList[1].y = blockList[0].y;
		rotatedBlockList[2].x = blockList[0].x;
		rotatedBlockList[2].y = blockList[0].y + Block.SIZE;
		rotatedBlockList[3].x = blockList[0].x + Block.SIZE;
		rotatedBlockList[3].y = blockList[0].y + Block.SIZE;
		
		updateXY(0);
	}
	public void getDirection1() {
		
			//	 1
			//	11
			//	1
		
		rotatedBlockList[0].x = blockList[0].x;
		rotatedBlockList[0].y = blockList[0].y;
		rotatedBlockList[1].x = blockList[0].x;
		rotatedBlockList[1].y = blockList[0].y - Block.SIZE;
		rotatedBlockList[2].x = blockList[0].x - Block.SIZE; 
		rotatedBlockList[2].y = blockList[0].y;
		rotatedBlockList[3].x = blockList[0].x - Block.SIZE;
		rotatedBlockList[3].y = blockList[0].y + Block.SIZE;
		
		updateXY(1);
		
	}
	public void getDirection2() {
		getDirection0();
	}
	public void getDirection3() {
		getDirection1();
	}
	
	
}
