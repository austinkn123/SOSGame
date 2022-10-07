package productSOSgame;

public class Board {
	private int[][] grid;
	private char turn = 'S';
	int size;

	public void setSize(int newSize) {
	    this.size = newSize;
	    grid = new int[newSize][newSize];
	 }
	
	public int getSize() {
	    return size;
	 }
	
	public Board() {
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
	
	public void makeMove(int row, int column, int boardSize) {
		if (row >= 0 && row < boardSize && column >= 0 && column < boardSize && grid[row][column] == 0) {
			grid[row][column] = (turn == 'S')? 1 : 2; 
			turn = (turn == 'S')? 'O' : 'S';
		}
	}
	
}