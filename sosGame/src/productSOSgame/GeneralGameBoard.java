package productSOSgame;

import java.util.Random;

public class GeneralGameBoard extends Board {
	public enum GameStateGeneral {RED_SCORES, BLUE_SCORES, NO_SCORE};
	protected GameStateGeneral currentGameScore;
	
	
	public void makeMoveInGeneralMode(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
//			After making a move, the gamestate is updated
			updateGameState(turn, row, column, redPlayer, bluePlayer); 
			
//			Turn is still with the player if they scored
			if(currentGameScore == GameStateGeneral.RED_SCORES) {
				turn = 'R';
			}
			else if(currentGameScore == GameStateGeneral.BLUE_SCORES) {
				turn = 'B';
			}
			else {
				turn = (turn == 'R')? 'B' : 'R';
			}
			
//			Making a computer move if the char key for computer matches
			if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING) {
				System.out.println("TURN: " + turn);
				makeAutoMove(boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue);
			}
			
		}
	}
	
//	Makes a automated move
	public void makeAutoMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) {
		if (!makeWinningMove()) {
			if (!blockOpponentWinningMove())
				makeRandomMove(size, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue);
		}
	}
	
	private void makeRandomMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) {
		int numberOfEmptyCells = getNumberOfEmptyCells();
		Random random = new Random();
//		Generate random number within number of empty cells
		int targetMove = random.nextInt(numberOfEmptyCells);
		int index=0;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					if (targetMove == index) {
						setRow(row);
						setCol(col);
						makeMoveInGeneralMode(row, col, size, redPlayer, bluePlayer, 
								cpuPlayerKeyRed, cpuPlayerKeyBlue);
						return;
					} else
						index++;
				}
			}
		}
	}
	
//	Make a random first move
	public void makeFirstMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) {
		Random random = new Random();
		int position = random.nextInt(size * size);
		System.out.println("CPU RED: " + cpuPlayerKeyRed);
		System.out.println("CPU BLUE: " + cpuPlayerKeyBlue);
		setRow(position/size);
		setCol(position%size);
		makeMoveInGeneralMode(position/size, position%size, size, redPlayer, bluePlayer, 
				cpuPlayerKeyRed, cpuPlayerKeyBlue);
	}
	
//	FOR TESTING PURPOSES
	public void testingAutomatedMove(int row, int column, int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) {
		if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING) {
			makeMoveInGeneralMode(row, column, size, redPlayer, bluePlayer, 
					cpuPlayerKeyRed, cpuPlayerKeyBlue);
			
		}
	}
	
	public void setGameScore(GameStateGeneral currentGameScore) {
		this.currentGameScore = currentGameScore;
	}
	
	public GameStateGeneral getGameScore() {
		return currentGameScore;
	}
	
//	Checking the game state to see if a player has scored
	private void updateGameState(char turn, int row, int column, char redPlayer, char bluePlayer) {
		if (hasScored(turn, row, column, size, redPlayer, bluePlayer)) { // check for player scoring
			currentGameScore = (turn == 'R') ? GameStateGeneral.RED_SCORES : GameStateGeneral.BLUE_SCORES;
		} // Otherwise, no change to current state (still GameState.PLAYING).
		else {
			currentGameState = GameState.PLAYING;
			currentGameScore = GameStateGeneral.NO_SCORE;
		}
		
		checkGameScore();
		
	}
	
//	Checks score when game ends to decide winner
	private void checkGameScore() {
		if(isFilled()) {
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
	}
	
}