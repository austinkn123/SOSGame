package productSOSgame;


public class Board {
	public enum Cell {EMPTY, RED_PLAYER, BLUE_PLAYER, HAS_SCORED};
	public enum scoredCell {NOT_SCORED, S_ROW_LEFT, S_ROW_RIGHT, S_COL_UP, S_COL_DOWN, S_LEFT_DIAG_DOWN, 
		S_LEFT_DIAG_UP, S_RIGHT_DIAG_DOWN, S_RIGHT_DIAG_UP, O_ROW, O_COL, O_DOWN_DIAG, O_UP_DIAG};
	public enum GameState {PLAYING, DRAW, RED_WINS, BLUE_WINS};
	public GameState currentGameState;
	protected char turn = 'R';
	protected int size = 0;
	protected String modeString;
	protected char redPlayerKey = 'S';
	protected char bluePlayerKey = 'S';
	protected Cell[][] grid;
	protected scoredCell[][] scoredGrid;
	
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
	    
	    //TESTING
	    scoredGrid = new scoredCell[newSize][newSize];
	    
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
				
				//TESTING
				scoredGrid[row][column] = scoredCell.NOT_SCORED;
			}
		}
		turn = 'R';
		currentGameState = GameState.PLAYING;
	} 


	public Cell getCell(int row, int column, int size) {
		if((row >= 0) && (column >= 0) && (row < size) && (column < size)) {
			return grid[row][column];
		}
		else {
			return null;
		}
	}
	
	//TESTING
	public scoredCell getScoredCell(int row, int column, int size) {
		if((row >= 0) && (column >= 0) && (row < size) && (column < size)) {
			return scoredGrid[row][column];
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
	
	public boolean isFilled() {
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					return false; // an empty cell found, not filled
				}
			}
		}
		return true;
	}
	
	protected boolean hasScored(char turn, int row, int column, int size, char redPlayer, char bluePlayer) {
		Cell token = (turn=='R')? Cell.RED_PLAYER: Cell.BLUE_PLAYER;
		boolean score = false;
		
		//RED'S TURN
		if(token == Cell.RED_PLAYER) {
			//RED IS S
			if(redPlayer == 'S' && bluePlayer == 'O') {
				return findScoreSRED(row, column, size);
			}
			//RED IS O
			else if(redPlayer == 'O' && bluePlayer == 'S'){
				return findScoreORED(row, column, size);
			}
		}
		//BLUE'S TURN
		if(token == Cell.BLUE_PLAYER) {
			//RED IS S
			if(redPlayer == 'S' && bluePlayer == 'O') {
				return findScoreOBLUE(row, column, size);
				
			}
			//RED IS O
			else if(redPlayer == 'O' && bluePlayer == 'S'){
				return findScoreSBLUE(row, column, size);
			}
		}
		return score;
	}
	
	public void setGameState(GameState currentGameState) {
		this.currentGameState = currentGameState;
	}
	
	public GameState getGameState() {
		return currentGameState;
	}
	
	//RED = S
	protected boolean findScoreSRED(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			//Row Score Right
			if(grid[row][column + 1] == Cell.BLUE_PLAYER && (grid[row][column + 2] == Cell.RED_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_ROW_RIGHT;
				
				return true;
			}
			//Right side Diagonal Down Score
			if(row < size - 2) {
				if(grid[row + 1][column + 1] == Cell.BLUE_PLAYER && (grid[row + 2][column + 2] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_RIGHT_DIAG_DOWN;
					
					return true;
				}
			}
			//Right side Diagonal Up Score
			if(row > 1) {
				if(grid[row - 1][column + 1] == Cell.BLUE_PLAYER && (grid[row - 2][column + 2] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_RIGHT_DIAG_UP;
					
					return true;
				}
			}
		}
		if((column > 1)) {
			//Row Score Left
			if(grid[row][column - 1] == Cell.BLUE_PLAYER && (grid[row][column - 2] == Cell.RED_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_ROW_LEFT;
				
				return true;
			}
			//Left side Diagonal Down Score
			if(row > 1) {
				if(grid[row - 1][column - 1] == Cell.BLUE_PLAYER && (grid[row - 2][column - 2] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_LEFT_DIAG_DOWN;
					
					return true;
				}
			}
			//Left side Diagonal Up Score
			if(row < size - 2) {
				if(grid[row + 1][column - 1] == Cell.BLUE_PLAYER && (grid[row + 2][column - 2] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_LEFT_DIAG_UP;
					
					return true;
				}
			}
		}
		if((row < size - 2)) {
			//Column Score down
			if(grid[row + 1][column] == Cell.BLUE_PLAYER && (grid[row + 2][column] == Cell.RED_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_COL_DOWN;
				
				return true;
			}
		}
		if((row > 1)) {
			//Column Score up
			if(grid[row - 1][column] == Cell.BLUE_PLAYER && (grid[row - 2][column] == Cell.RED_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_COL_UP;
				
				return true;
			}
		}
			
		return score;
	}
	
	//BLUE == O
	protected boolean findScoreOBLUE(int row, int column, int size) {
		//O Player
		boolean score = false;
		
			if((column == 0) || (column == size - 1)) {
				score = false;
			}
			else{
				//O Player Row Score
				if(grid[row][column - 1] == Cell.RED_PLAYER && (grid[row][column + 1] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_ROW;
					
					return true;
				}
			}
			
			if((row == 0) || (row == size - 1) ) {
				score = false;
			}
			else {
				//O Player Column Score
				if(grid[row - 1][column] == Cell.RED_PLAYER && (grid[row + 1][column] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_COL;
					
					return true;
				}
			}
			if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
				score = false;
			}
			else {
				//O Player Down Diagonal Score
				if(grid[row - 1][column - 1] == Cell.RED_PLAYER && (grid[row + 1][column + 1] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_DOWN_DIAG;
					
					return true;
				}
				//O Player Up Diagonal Score
				if(grid[row + 1][column - 1] == Cell.RED_PLAYER && (grid[row - 1][column + 1] == Cell.RED_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_UP_DIAG;
					
					return true;
				}
			}

		return score;
	}
	
	//BLUE == S
	protected boolean findScoreSBLUE(int row, int column, int size) {
		boolean score = false;
		
		if((column < size - 2)) {
			//Row Score Right
			if(grid[row][column + 1] == Cell.RED_PLAYER && (grid[row][column + 2] == Cell.BLUE_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_ROW_RIGHT;
				
				return true;
			}
			//Right side Diagonal Down Score
			if(row < size - 2) {
				if(grid[row + 1][column + 1] == Cell.RED_PLAYER && (grid[row + 2][column + 2] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_RIGHT_DIAG_DOWN;
					
					return true;
				}
			}
			//Right side Diagonal Up Score
			if(row > 1) {
				if(grid[row - 1][column + 1] == Cell.RED_PLAYER && (grid[row - 2][column + 2] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_RIGHT_DIAG_UP;
					
					return true;
				}
			}
		}
		
		if((column > 1)) {
			//Row Score Left
			if(grid[row][column - 1] == Cell.RED_PLAYER && (grid[row][column - 2] == Cell.BLUE_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_ROW_LEFT;
				
				return true;
			}
			//Left side Diagonal Down Score
			if(row > 1) {
				if(grid[row - 1][column - 1] == Cell.RED_PLAYER && (grid[row - 2][column - 2] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_LEFT_DIAG_DOWN;
					
					return true;
				}
			}
			//Left side Diagonal Up Score
			if(row < size - 2) {
				if(grid[row + 1][column - 1] == Cell.RED_PLAYER && (grid[row + 2][column - 2] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.S_LEFT_DIAG_UP;
					
					return true;
				}
			}
		}
		//Column Score down
		if((row < size - 2)) {
			if(grid[row + 1][column] == Cell.RED_PLAYER && (grid[row + 2][column] == Cell.BLUE_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_COL_DOWN;
				
				return true;
			}
		}
		//Column Score up
		if((row > 1)) {
			if(grid[row - 1][column] == Cell.RED_PLAYER && (grid[row - 2][column] == Cell.BLUE_PLAYER)) {
				
				scoredGrid[row][column] = scoredCell.S_COL_UP;
				
				return true;
			}
		}
		
		return score;
	}
	
	
	//RED == O
	protected boolean findScoreORED(int row, int column, int size) {
		//O Player
		boolean score = false;
			
			if((column == 0) || (column == size - 1)) {
				score = false;
			}
			else{
				//O Player Row Score
				if(grid[row][column - 1] == Cell.BLUE_PLAYER && (grid[row][column + 1] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_ROW;
					
					return true;
				}
			}
			if((row == 0) || (row == size - 1) ) {
				score = false;
			}
			else {
				//O Player Column Score
				if(grid[row - 1][column] == Cell.BLUE_PLAYER && (grid[row + 1][column] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_COL;
					
					return true;
				}
			}
			if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
				score = false;
			}
			else {
				//O Player Down Diagonal Score
				if(grid[row - 1][column - 1] == Cell.BLUE_PLAYER && (grid[row + 1][column + 1] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_DOWN_DIAG;
					
					return true;
				}
				//O Player Up Diagonal Score
				if(grid[row + 1][column - 1] == Cell.BLUE_PLAYER && (grid[row - 1][column + 1] == Cell.BLUE_PLAYER)) {
					
					scoredGrid[row][column] = scoredCell.O_UP_DIAG;
					
					return true;
				}
			}

		return score;
	}
	
}