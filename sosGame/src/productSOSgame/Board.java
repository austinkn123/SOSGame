package productSOSgame;


public class Board {
	public enum Cell {EMPTY, RED_PLAYER, BLUE_PLAYER};
	protected char turn = 'R';
	protected int size = 0;
	protected String modeString;
	protected char redPlayerKey = 'S';
	protected char bluePlayerKey = 'S';
	protected Cell[][] grid;
	
	public void setSize(int newSize) {
	    this.size = newSize;
	 }
	
	public int getSize() {
	    return size;
	 }
	
	public void setModeString(String mode) {
	    this.modeString = mode;
	 }
	
	public String getModeString() {
	    return modeString;
	 }
	
	public void setRedPlayerKey(char redPlayerKey) {
	    this.redPlayerKey = redPlayerKey;
	 }
	
	public char getRedPlayerKey() {
	    return redPlayerKey;
	 }
	
	public void setBluePlayerKey(char bluePlayerKey) {
	    this.bluePlayerKey = bluePlayerKey;
	 }
	
	public char getBluePlayerKey() {
	    return bluePlayerKey;
	 }
	
	public void setSizeBoard(int newSize) {
		setSize(newSize);
	    //CAREFUL, MAKES NEW BOARD OF ZEROS
	    grid = new Cell[newSize][newSize];
	    initBoard();
	 }
	
	public int setMode(String modeKey,int newSize) {
		if(newSize < 3) {
			return -1;
		}
	    if(modeKey == "GENERAL") {
	    	return 1;
	    }
	    else if(modeKey == "SIMPLE") {
	    	return 2;
	    }
	    return -1;
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
		turn = 'R';
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
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			turn = (turn == 'R')? 'B' : 'R';
		}
	}
	
}