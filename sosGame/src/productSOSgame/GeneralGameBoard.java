package productSOSgame;

public class GeneralGameBoard extends Board {

//	public GeneralGameBoard(int size) {
//		super(size);
//		// TODO Auto-generated constructor stub
//	}
	public enum GameState {PLAYING, DRAW, SPLAYER_SCORES, OPLAYER_SCORES};
	private GameState currentGameState;
	private int pointS = 0;
	private int pointO = 0;
	
	public void addPointS() {
		pointS += 1;
	}
	
	public int getPointS() {
	    return pointS;
	 }
	
	public void addPointO() {
		pointO += 1;
	}
	
	public int getPointO() {
	    return pointO;
	 }
	
	public void setSizeGeneral(int newSize) {
	    this.size = newSize;
	    //CAREFUL, MAKES NEW BOARD OF ZEROS
	    grid = new Cell[newSize][newSize];
	    initBoardGeneral();
	 }
	
	public void initBoardGeneral() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				grid[row][column] = Cell.EMPTY;
			}
		}
		currentGameState = GameState.PLAYING;
		turn = 'S';
	} 
	
	public void makeMoveInGeneralMode(int row, int column, int boardSize) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'S')? Cell.SPLAYER : Cell.OPLAYER; 
			updateGameState(turn, row, column); 
			turn = (turn == 'S')? 'O' : 'S';
		}
	}
	
	private boolean hasScored(char turn, int row, int column, int size) {
		Cell token = (turn=='S')? Cell.SPLAYER: Cell.OPLAYER;
		boolean score = false;
		
		//S Player
		if(token == Cell.SPLAYER) {
			//Row Score Right
			if((column < size - 2)) {
				if(grid[row][column + 1] == Cell.OPLAYER && (grid[row][column + 2] == Cell.SPLAYER)) {
					return true;
				}
			}
			//Row Score Left
			if((column > 1)) {
				if(grid[row][column - 1] == Cell.OPLAYER && (grid[row][column - 2] == Cell.SPLAYER)) {
					return true;
				}
			}
			//Column Score down
			if((row < size - 2)) {
				if(grid[row + 1][column] == Cell.OPLAYER && (grid[row + 2][column] == Cell.SPLAYER)) {
					return true;
				}
			}
			//Column Score up
			if((row > 1)) {
				if(grid[row - 1][column] == Cell.OPLAYER && (grid[row - 2][column] == Cell.SPLAYER)) {
					return true;
				}
			}
			//Down diagonal Left Score
			if((column > 1) && (row > 1)) {
				if(grid[row - 1][column - 1] == Cell.OPLAYER && (grid[row - 2][column - 2] == Cell.SPLAYER)) {
					return true;
				}
				
			}
			//Down diagonal Right Score
			if((column < size - 2) && (row < size - 2)) {
				
				if(grid[row + 1][column + 1] == Cell.OPLAYER && (grid[row + 2][column + 2] == Cell.SPLAYER)) {
					return true;
				}
				
			}
			//Up diagonal Left
			if((column < size - 2) && (row > 1)) {
				if(grid[row - 1][column + 1] == Cell.OPLAYER && (grid[row - 2][column + 2] == Cell.SPLAYER)) {
					return true;
				}
			}
			//Up diagonal Right
			if((column > 1) && (row < size - 2)) {
				if(grid[row + 1][column - 1] == Cell.OPLAYER && (grid[row + 2][column - 2] == Cell.SPLAYER)) {
					return true;
				}
			}
			
			

		}
		
		//O Player
		if(token == Cell.OPLAYER) {
			//O Player Row Score
			if((column == 0) || (column == size - 1)) {
				score = false;
			}
			else{
				if(grid[row][column - 1] == Cell.SPLAYER && (grid[row][column + 1] == Cell.SPLAYER)) {
					return true;
				}
			}
			//O Player Column Score
			if((row == 0) || (row == size - 1) ) {
				score = false;
			}
			else {
				if(grid[row - 1][column] == Cell.SPLAYER && (grid[row + 1][column] == Cell.SPLAYER)) {
					return true;
				}
			}
			if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
				score = false;
			}
			else {
				//O Player Down Diagonal Score
				if(grid[row - 1][column - 1] == Cell.SPLAYER && (grid[row + 1][column + 1] == Cell.SPLAYER)) {
					return true;
				}
				//O Player Up Diagonal Score
				if(grid[row + 1][column - 1] == Cell.SPLAYER && (grid[row - 1][column + 1] == Cell.SPLAYER)) {
					return true;
				}
			}
		}
		
		return score;
	}
	
	public GameState getGameState() {
		return currentGameState;
	}
	
	private void updateGameState(char turn, int row, int column) {
		if (hasScored(turn, row, column, size)) { // check for player scoring
			currentGameState = (turn == 'S') ? GameState.SPLAYER_SCORES : GameState.OPLAYER_SCORES;
		// Otherwise, no change to current state (still GameState.PLAYING).
		}
		else {
			currentGameState = GameState.PLAYING;
		}
	}

	
}