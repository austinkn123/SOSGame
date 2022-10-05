package productSOSgame;

public class Board {
	private int[][] grid;
	private char turnS = 'S';
	private char turnO = 'O'; 

	public Board(int size) {
		grid = new int[size][size];
	}

	public int getCell(int row, int column, int size) {
//		return grid[row][column];
		if((row >= 0) && (column >= 0) && (row < size) && (column < size)) {
			return grid[row][column];
		}
		else {
			return -1;
		}
	}

	public char getTurnS() {
		return turnS;
	}
	
	public char getTurnO() {
		return turnO;
	}
}