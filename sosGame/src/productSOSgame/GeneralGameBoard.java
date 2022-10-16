package productSOSgame;

public class GeneralGameBoard extends Board {

//	public GeneralGameBoard(int size) {
//		super(size);
//		// TODO Auto-generated constructor stub
//	}
	public enum GameState {PLAYING, DRAW, RED_SCORES, BLUE_SCORES};
	private GameState currentGameState;
	private int pointRed = 0;
	private int pointBlue = 0;
	
	public void addPointRed() {
		pointRed += 1;
	}
	
	public int getPointRed() {
	    return pointRed;
	 }
	
	public void addPointBlue() {
		pointBlue += 1;
	}
	
	public int getPointBlue() {
	    return pointBlue;
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
		turn = 'R';
	} 
	
	
	//!!! FOR NEXT SPRINT, I WORKED AHEAD A LITTLE
	
	public void makeMoveInGeneralMode(int row, int column, int boardSize, char redPlayer) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
			updateGameState(turn, row, column, redPlayer); 
			
			if(currentGameState == GameState.RED_SCORES) {
				turn = 'R';
			}
			else if(currentGameState == GameState.BLUE_SCORES) {
				turn = 'B';
			}
			else {
				turn = (turn == 'R')? 'B' : 'R';
			}
			
		}
		
		
	}
	
	private boolean hasScored(char turn, int row, int column, int size, char redPlayer) {
		Cell token = (turn=='R')? Cell.RED_PLAYER: Cell.BLUE_PLAYER;
		boolean score = false;
		
		//RED'S TURN
		if(token == Cell.RED_PLAYER) {
			//RED IS S
			if(redPlayer == 'S') {
				return findScoreSRED(row, column, size);
			}
			//RED IS O
			else if(redPlayer == 'O'){
				return findScoreORED(row, column, size);
			}
		}
		//BLUE'S TURN
		if(token == Cell.BLUE_PLAYER) {
			//RED IS S
			if(redPlayer == 'S') {
				return findScoreOBLUE(row, column, size);
				
			}
			//RED IS O
			else if(redPlayer == 'O'){
				return findScoreSBLUE(row, column, size);
			}
		}
		
		
		
		return score;
	}
	
	//RED = S
	private boolean findScoreSRED(int row, int column, int size) {
		boolean score = false;
		//Row Score Right
		if((column < size - 2)) {
			if(grid[row][column + 1] == Cell.BLUE_PLAYER && (grid[row][column + 2] == Cell.RED_PLAYER)) {
				return true;
			}
			//Down diagonal Right Score
			if(row < size - 2) {
				if(grid[row + 1][column + 1] == Cell.BLUE_PLAYER && (grid[row + 2][column + 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
			//Up diagonal Left
			if(row > 1) {
				if(grid[row - 1][column + 1] == Cell.BLUE_PLAYER && (grid[row - 2][column + 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
		}
		//Row Score Left
		if((column > 1)) {
			if(grid[row][column - 1] == Cell.BLUE_PLAYER && (grid[row][column - 2] == Cell.RED_PLAYER)) {
				return true;
			}
			//Down diagonal Left Score
			if(row > 1) {
				if(grid[row - 1][column - 1] == Cell.BLUE_PLAYER && (grid[row - 2][column - 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
			//Up diagonal Right
			if(row < size - 2) {
				if(grid[row + 1][column - 1] == Cell.BLUE_PLAYER && (grid[row + 2][column - 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
		}
		//Column Score down
		if((row < size - 2)) {
			if(grid[row + 1][column] == Cell.BLUE_PLAYER && (grid[row + 2][column] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		//Column Score up
		if((row > 1)) {
			if(grid[row - 1][column] == Cell.BLUE_PLAYER && (grid[row - 2][column] == Cell.RED_PLAYER)) {
				return true;
			}
		}
			

		
		return score;
	}
	
	//BLUE == O
	private boolean findScoreOBLUE(int row, int column, int size) {
		//O Player
		boolean score = false;
			//O Player Row Score
			if((column == 0) || (column == size - 1)) {
				score = false;
			}
			else{
				if(grid[row][column - 1] == Cell.RED_PLAYER && (grid[row][column + 1] == Cell.RED_PLAYER)) {
					return true;
				}
			}
			//O Player Column Score
			if((row == 0) || (row == size - 1) ) {
				score = false;
			}
			else {
				if(grid[row - 1][column] == Cell.RED_PLAYER && (grid[row + 1][column] == Cell.RED_PLAYER)) {
					return true;
				}
			}
			if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
				score = false;
			}
			else {
				//O Player Down Diagonal Score
				if(grid[row - 1][column - 1] == Cell.RED_PLAYER && (grid[row + 1][column + 1] == Cell.RED_PLAYER)) {
					return true;
				}
				//O Player Up Diagonal Score
				if(grid[row + 1][column - 1] == Cell.RED_PLAYER && (grid[row - 1][column + 1] == Cell.RED_PLAYER)) {
					return true;
				}
			}

		return score;
	}
	
	//BLUE == S!!!!!!!!!!!!!!!
	private boolean findScoreSBLUE(int row, int column, int size) {
		boolean score = false;
		//Row Score Right
		if((column < size - 2)) {
			if(grid[row][column + 1] == Cell.RED_PLAYER && (grid[row][column + 2] == Cell.BLUE_PLAYER)) {
				return true;
			}
			//Down diagonal Right Score
			if(row < size - 2) {
				if(grid[row + 1][column + 1] == Cell.RED_PLAYER && (grid[row + 2][column + 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
			//Up diagonal Left
			if(row > 1) {
				if(grid[row - 1][column + 1] == Cell.RED_PLAYER && (grid[row - 2][column + 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
		}
		//Row Score Left
		if((column > 1)) {
			if(grid[row][column - 1] == Cell.RED_PLAYER && (grid[row][column - 2] == Cell.BLUE_PLAYER)) {
				return true;
			}
			//Down diagonal Left Score
			if(row > 1) {
				if(grid[row - 1][column - 1] == Cell.RED_PLAYER && (grid[row - 2][column - 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
			//Up diagonal Right
			if(row < size - 2) {
				if(grid[row + 1][column - 1] == Cell.RED_PLAYER && (grid[row + 2][column - 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
		}
		//Column Score down
		if((row < size - 2)) {
			if(grid[row + 1][column] == Cell.RED_PLAYER && (grid[row + 2][column] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		//Column Score up
		if((row > 1)) {
			if(grid[row - 1][column] == Cell.RED_PLAYER && (grid[row - 2][column] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		
		return score;
	}
	
	
	//RED == O
	private boolean findScoreORED(int row, int column, int size) {
		//O Player
		boolean score = false;
			//O Player Row Score
			if((column == 0) || (column == size - 1)) {
				score = false;
			}
			else{
				if(grid[row][column - 1] == Cell.BLUE_PLAYER && (grid[row][column + 1] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
			//O Player Column Score
			if((row == 0) || (row == size - 1) ) {
				score = false;
			}
			else {
				if(grid[row - 1][column] == Cell.BLUE_PLAYER && (grid[row + 1][column] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
			if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
				score = false;
			}
			else {
				//O Player Down Diagonal Score
				if(grid[row - 1][column - 1] == Cell.BLUE_PLAYER && (grid[row + 1][column + 1] == Cell.BLUE_PLAYER)) {
					return true;
				}
				//O Player Up Diagonal Score
				if(grid[row + 1][column - 1] == Cell.BLUE_PLAYER && (grid[row - 1][column + 1] == Cell.BLUE_PLAYER)) {
					return true;
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
	
	private void updateGameState(char turn, int row, int column, char redPlayer) {
		if (hasScored(turn, row, column, size, redPlayer)) { // check for player scoring
			currentGameState = (turn == 'R') ? GameState.RED_SCORES : GameState.BLUE_SCORES;
		// Otherwise, no change to current state (still GameState.PLAYING).
		}
		else {
			currentGameState = GameState.PLAYING;
		}
	}

	
}