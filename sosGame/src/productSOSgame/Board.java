package productSOSgame;

public class Board {
	private int[][] grid;
	private char turn = 'S';
	private Boolean gameEnded = false;

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

	public char getTurn() {
		return turn;
	}
	
}