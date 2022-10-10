package productSOSgame;


public class Board {
	public enum Cell {EMPTY, SPLAYER, OPLAYER};
	protected char turn = 'S';
	protected int size = 0;
	protected Cell[][] grid;
	

	public void setSize(int newSize) {
	    this.size = newSize;
	    //CAREFUL, MAKES NEW BOARD OF ZEROS
	    grid = new Cell[newSize][newSize];
	    initBoard();
	 }
	
	public int getSize() {
	    return size;
	 }
	
	public Board() {
		grid = new Cell[size][size];
		initBoard();
	}
	
	public void initBoard() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				grid[row][column] = Cell.EMPTY;
			}
		}
		turn = 'S';
	} 


	public Cell getCell(int row, int column, int size) {
//		return grid[row][column];
		if((row >= 0) && (column >= 0) && (row < size) && (column < size)) {
			return grid[row][column];
		}
		else {
			return null;
		}
	}

	public char getTurn() {
		return turn;
	}
	
	public void makeMove(int row, int column, int boardSize) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'S')? Cell.SPLAYER : Cell.OPLAYER; 
			turn = (turn == 'S')? 'O' : 'S';
		}
	}
	
}