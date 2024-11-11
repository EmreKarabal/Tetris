package piece;

import java.awt.Color;

public class Piece_O extends Piece{

	public int id = 4;
	
	
	public Piece_O() {
		create(Color.yellow);
		
	}
	
	
public void setXY(int x, int y) {
		
	/*
	 *  11
	 *  11
	 */
	
		blockList[0].x = x;
		blockList[0].y = y;
		blockList[1].x = x + Block.SIZE;
		blockList[1].y = y;
		blockList[2].x = x;
		blockList[2].y = y + Block.SIZE;
		blockList[3].x = x + Block.SIZE;
		blockList[3].y = y + Block.SIZE;
		
		
	}
	
	
	// Rotation methods
	public void getDirection0() {
	}
	public void getDirection1() {
		
	}
	public void getDirection2() {
		
	}
	public void getDirection3() {
		
	}
	
	
	
}
