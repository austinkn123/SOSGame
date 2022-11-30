package productSOSgame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import productSOSgame.Board.Cell;
import productSOSgame.Board.GameState;
//import productSOSgame.GeneralGameBoard.GameStateGeneral;

public class Board {
	public enum Cell {EMPTY, RED_PLAYER, BLUE_PLAYER, HAS_SCORED};
	public enum scoredCell {NOT_SCORED, RED_S_SCORED, RED_O_SCORED, BLUE_S_SCORED, BLUE_O_SCORED};
	public enum GameState {PLAYING, DRAW, RED_WINS, BLUE_WINS, RED_SCORES, BLUE_SCORES};
	public GameState currentGameState;
	protected char turn = 'R';
	protected int size = 0;
	protected String modeString;
	protected char redPlayerKey = 'X';
	protected char bluePlayerKey = 'X';
	private char cpuRedPlayer = 'X';
	private char cpuBluePlayer = 'X';
	protected Cell[][] grid; 
	protected scoredCell[][] scoredGrid;
	private int pointRed = 0;
	private int pointBlue = 0;
	public int row;
	public int col;
	protected String movesRecordedString = "";
	public File recordFile = new File("RecordGame.txt");
	protected String redOpp;
	protected String blueOpp;
	protected String turnString; 
	
	public void addPointRed() {pointRed += 1;}
	public void resetPointRed() {pointRed = 0;}
	public int getPointRed() {return pointRed;}

	public void addPointBlue() {pointBlue += 1;}
	public void resetPointBlue() {pointBlue = 0;}
	public int getPointBlue() {return pointBlue;}
	
	public void setSize(int newSize) {this.size = newSize;}
	public int getSize() {return size;}
	
	public void setModeString(String mode) {this.modeString = mode;}
	public String getModeString() {return modeString;}
	
	public void setRedPlayerKey(char redPlayerKey) {this.redPlayerKey = redPlayerKey;}
	public char getRedPlayerKey() {return redPlayerKey;}
	
	public void setBluePlayerKey(char bluePlayerKey) {this.bluePlayerKey = bluePlayerKey;}
	public char getBluePlayerKey() {return bluePlayerKey;}
	
	public void setCpuRedPlayer(char cpuRedPlayer) {this.cpuRedPlayer = cpuRedPlayer;}
	public char getCpuRedPlayer() {return cpuRedPlayer;}
	
	public void setCpuBluePlayer(char cpuBluePlayer) {this.cpuBluePlayer = cpuBluePlayer;}
	public char getCpuBluePlayer() {return cpuBluePlayer;}
	
//	For Automated Testing
	public void setRow(int row) {this.row = row;}
	public int getRow() {return row;}
	public void setCol(int col) {this.col = col;}
	public int getCol() {return col;}
	
	public char getTurn() {
		return turn;
	}
	
	public Board() {
		grid = new Cell[size][size];
		initBoard();
	}
	
	public Cell[][] getGrid(){
		return grid;
	}
	
//	Sets the game mode key and checks valid size
	public int setMode(String modeKey,int newSize) {
		if(newSize < 3) {
			return -1;
		}
	    if(modeKey == "GENERAL") {
	    	setModeString(modeKey);
	    	return 1;
	    }
	    else if(modeKey == "SIMPLE") {
	    	setModeString(modeKey);
	    	return 2;
	    }
	    return -1;
	 }
	
//	Can function as a reset function
	public void setSizeBoard(int newSize) { 
		setSize(newSize);
	    //CAREFUL, MAKES NEW BOARD OF ZEROS
	    grid = new Cell[newSize][newSize];
	    
	    //TESTING
	    scoredGrid = new scoredCell[newSize][newSize];
	    
	    initBoard();
	 }
	
//	Sets board and scored board as empty and sets game state
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
	
	//If a player has scored, or if the board is filled with nobody scoring then end the game 
	public void updateGameState(char turn, int row, int column, char redPlayer, char bluePlayer) {
		if (hasScored(turn, row, column, size, redPlayer, bluePlayer)) { // check for player scoring
			currentGameState = (turn == 'R') ? GameState.RED_SCORES : GameState.BLUE_SCORES;
		} // Otherwise, no change to current state (still GameState.PLAYING).
		else {
			currentGameState = GameState.PLAYING;
		}
		if (isFilled() && (currentGameState == GameState.PLAYING)) {
			currentGameState = GameState.DRAW; 
		}
	}
	
	public void checkGameScore() {
		System.out.println(getPointRed());
		if(getPointRed() > getPointBlue()) {
			currentGameState = GameState.RED_WINS;
			System.out.println("RED WINS");
			System.out.println("RED--" + getPointRed());
			System.out.println("BLUE--" + getPointBlue());
		}
		else if (getPointRed() < getPointBlue()){
			currentGameState = GameState.BLUE_WINS;
			System.out.println("BLUE WINS");
			System.out.println("RED--" + getPointRed());
			System.out.println("BLUE--" + getPointBlue());
		}
		else if (getPointRed() == getPointBlue()){
			currentGameState = GameState.DRAW;
			System.out.println("DRAW");
			System.out.println("RED--" + getPointRed());
			System.out.println("BLUE--" + getPointBlue());
		}
	}
	
	//For console testing
	public void makeMove(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			turn = (turn == 'R')? 'B' : 'R';
		}
		updateGameState(turn, row, column, redPlayer, bluePlayer); 
		if(recordKey == true) {
			recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
		}
	}

	protected boolean makeWinningMove() {
		return false;
	}

	protected boolean blockOpponentWinningMove() {
		return false;
	}
	
	//Makes a automated move
	protected void makeAutoMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if (!makeWinningMove()) {
			if (!blockOpponentWinningMove())
				makeRandomMove(size, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
		}

	}
	
	public void makeFirstMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		Random random = new Random();
		int position = random.nextInt(size * size);
		setRow(position/size);
		setCol(position%size);
		makeMove(position/size, position%size, size, redPlayer, bluePlayer, 
				cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
	}
	
	private void makeRandomMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		int numberOfEmptyCells = getNumberOfEmptyCells();
		Random random = new Random();
		//Generate random number within number of empty cells
		int targetMove = random.nextInt(numberOfEmptyCells);
		int index=0;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					if (targetMove == index) {
						setRow(row);
						setCol(col);
						makeMove(row, col, size, redPlayer, bluePlayer, 
									cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
						return;
					} else
						index++;
				}
			}
		}
		
	}
	
	//Finds the total of empty cells on grid
	protected int getNumberOfEmptyCells() {
		int numberOfEmptyCells = 0;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					numberOfEmptyCells++;
				}
			}
		}
		return numberOfEmptyCells;
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
	
	protected void createFile() {
		try {
			if (recordFile.createNewFile()) {
				System.out.println("File created: " + recordFile.getName());
			} 
			else {
				System.out.println("File already exists.");
			}
	    } 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void recordMoves(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, char turn) {
		
		
		turnString = (turn == 'R') ? "Red" : "Blue";

		if (cpuPlayerKeyBlue == 'B') {
			blueOpp = "Computer";
		}
		
		movesRecordedString += turnString + "'s Turn: \n Move: Row - " +  row 
				+ " Column - " + column + "\n";

	}
	
	//RED = S
	public boolean rowRightSideRedS(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			if(grid[row][column + 1] == Cell.BLUE_PLAYER && (grid[row][column + 2] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean rowLeftSideRedS(int row, int column, int size) {
		boolean score = false;
		if((column > 1)) {
			if(grid[row][column - 1] == Cell.BLUE_PLAYER && (grid[row][column - 2] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean colDownRedS(int row, int column, int size) {
		boolean score = false;
		if((row < size - 2)) {
			if(grid[row + 1][column] == Cell.BLUE_PLAYER && (grid[row + 2][column] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean colUpRedS(int row, int column, int size) {
		boolean score = false;
		if((row > 1)) {
			if(grid[row - 1][column] == Cell.BLUE_PLAYER && (grid[row - 2][column] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean rightSideDiagDownRedS(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			if(row < size - 2) {
				if(grid[row + 1][column + 1] == Cell.BLUE_PLAYER && (grid[row + 2][column + 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean rightSideDiagUpRedS(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			if(row > 1) {
				if(grid[row - 1][column + 1] == Cell.BLUE_PLAYER && (grid[row - 2][column + 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean leftSideDiagDownRedS(int row, int column, int size) {
		boolean score = false;
		if((column > 1)) {
			if(row > 1) {
				if(grid[row - 1][column - 1] == Cell.BLUE_PLAYER && (grid[row - 2][column - 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean leftSideDiagUpRedS(int row, int column, int size) {
		boolean score = false;
		if((column > 1)) {
			if(row < size - 2) {
				if(grid[row + 1][column - 1] == Cell.BLUE_PLAYER && (grid[row + 2][column - 2] == Cell.RED_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	protected boolean findScoreSRED(int row, int column, int size) {
		boolean score = false;
		//Row Score Right
		if(rowRightSideRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
		
		//Row Score Left
		if(rowLeftSideRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
		
		//Right side Diagonal Down Score
		if(rightSideDiagDownRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
		
		//Right side Diagonal Up Score
		if(rightSideDiagUpRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
		
		//Left side Diagonal Down Score
		if(leftSideDiagDownRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
		
		//Left side Diagonal Up Score
		if(leftSideDiagUpRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
		
		//Column Score down
		if(colDownRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
				
		if(colUpRedS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_S_SCORED;
			addPointRed();
			score = true;
		}
			
		return score;
	}
	
	//BLUE == O
	public boolean rowBlueO(int row, int column, int size) {
		boolean score = false;
		if((column == 0) || (column == size - 1)) {
			score = false;
		}
		else{
			
			if(grid[row][column - 1] == Cell.RED_PLAYER && (grid[row][column + 1] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean colBlueO(int row, int column, int size) {
		boolean score = false;
		if((row == 0) || (row == size - 1) ) {
			score = false;
		}
		else {
			
			if(grid[row - 1][column] == Cell.RED_PLAYER && (grid[row + 1][column] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean diagDownBlueO(int row, int column, int size) {
		boolean score = false;
		if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
			score = false;
		}
		else {
			if(grid[row - 1][column - 1] == Cell.RED_PLAYER && (grid[row + 1][column + 1] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean diagUpBlueO(int row, int column, int size) {
		boolean score = false;
		if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
			score = false;
		}
		else {
			if(grid[row + 1][column - 1] == Cell.RED_PLAYER && (grid[row - 1][column + 1] == Cell.RED_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	protected boolean findScoreOBLUE(int row, int column, int size) {
		//O Player
		boolean score = false;
		
		//O Player Row Score
		if(rowBlueO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_O_SCORED;
			addPointBlue();
			score = true;
		}
		
		//O Player Column Score
		if(colBlueO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_O_SCORED;
			addPointBlue();
			score = true;
		}
		
		//O Player Down Diagonal Score
		if(diagDownBlueO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_O_SCORED;
			addPointBlue();
			score = true;
		}
		
		//O Player Up Diagonal Score
		if(diagUpBlueO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_O_SCORED;
			addPointBlue();
			score = true;
		}

		return score;
	}
	
	
	//BLUE == S
	public boolean rowRightSideBlueS(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			if(grid[row][column + 1] == Cell.RED_PLAYER && (grid[row][column + 2] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean rightSideDiagDownBlueS(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			if(row < size - 2) {
				if(grid[row + 1][column + 1] == Cell.RED_PLAYER && (grid[row + 2][column + 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean rightSideDiagUpBlueS(int row, int column, int size) {
		boolean score = false;
		if((column < size - 2)) {
			if(row > 1) {
				if(grid[row - 1][column + 1] == Cell.RED_PLAYER && (grid[row - 2][column + 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean rowLeftSideBlueS(int row, int column, int size) {
		boolean score = false;
		if((column > 1)) {
			if(grid[row][column - 1] == Cell.RED_PLAYER && (grid[row][column - 2] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean leftSideDiagDownBlueS(int row, int column, int size) {
		boolean score = false;
		if((column > 1)) {
			if(row > 1) {
				if(grid[row - 1][column - 1] == Cell.RED_PLAYER && (grid[row - 2][column - 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean leftSideDiagUpBlueS(int row, int column, int size) {
		boolean score = false;
		if((column > 1)) {
			if(row < size - 2) {
				if(grid[row + 1][column - 1] == Cell.RED_PLAYER && (grid[row + 2][column - 2] == Cell.BLUE_PLAYER)) {
					return true;
				}
			}
		}
		return score;
	}
	
	public boolean colDownBlueS(int row, int column, int size) {
		boolean score = false;
		if((row < size - 2)) {
			if(grid[row + 1][column] == Cell.RED_PLAYER && (grid[row + 2][column] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean colUpBlueS(int row, int column, int size) {
		boolean score = false;
		if((row > 1)) {
			if(grid[row - 1][column] == Cell.RED_PLAYER && (grid[row - 2][column] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	protected boolean findScoreSBLUE(int row, int column, int size) {
		boolean score = false;
		
		//Row Score Right
		if(rowRightSideBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Right side Diagonal Down Score
		if(rightSideDiagDownBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Right side Diagonal Up Score
		if(rightSideDiagUpBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Row Score Left
		if(rowLeftSideBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Left side Diagonal Down Score
		if(leftSideDiagDownBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Left side Diagonal Up Score
		if(leftSideDiagUpBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Column Score down
		if(colDownBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		//Column Score up
		if(colUpBlueS(row, column, size)) {
			scoredGrid[row][column] = scoredCell.BLUE_S_SCORED;
			addPointBlue();
			score = true;
		}
		
		return score;
	}
	
	
	//RED == O
	public boolean rowRedO(int row, int column, int size) {
		boolean score = false;
		if((column == 0) || (column == size - 1)) {
			score = false;
		}
		else{
			//O Player Row Score
			if(grid[row][column - 1] == Cell.BLUE_PLAYER && (grid[row][column + 1] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean colRedO(int row, int column, int size) {
		boolean score = false;
		if((row == 0) || (row == size - 1) ) {
			score = false;
		}
		else {
			if(grid[row - 1][column] == Cell.BLUE_PLAYER && (grid[row + 1][column] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean diagDownRedO(int row, int column, int size) {
		boolean score = false;
		if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
			score = false;
		}
		else {
			//O Player Down Diagonal Score
			if(grid[row - 1][column - 1] == Cell.BLUE_PLAYER && (grid[row + 1][column + 1] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	public boolean diagUpRedO(int row, int column, int size) {
		boolean score = false;
		if((row == 0) || (row == size - 1) || (column == 0) || (column == size - 1)) {
			score = false;
		}
		else {
			//O Player Down Diagonal Score
			if(grid[row + 1][column - 1] == Cell.BLUE_PLAYER && (grid[row - 1][column + 1] == Cell.BLUE_PLAYER)) {
				return true;
			}
		}
		return score;
	}
	
	
	protected boolean findScoreORED(int row, int column, int size) {
		//O Player
		boolean score = false;
		
		//O Player Row Score
		if(rowRedO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_O_SCORED;
			addPointRed();
			score = true;
		}
		
		//O Player Column Score
		if(colRedO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_O_SCORED;
			addPointRed();
			score = true;
		}
		
		//O Player Down Diagonal Score
		if(diagDownRedO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_O_SCORED;
			addPointRed();
			score = true;
		}
		
		//O Player Up Diagonal Score
		if(diagUpRedO(row, column, size)) {
			scoredGrid[row][column] = scoredCell.RED_O_SCORED;
			addPointRed();
			score = true;
		}		

		return score;
	}
	
}