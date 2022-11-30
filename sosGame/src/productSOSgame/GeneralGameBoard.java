package productSOSgame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneralGameBoard extends Board {
	public enum GameStateGeneral {RED_SCORES, BLUE_SCORES, NO_SCORE};
	protected GameStateGeneral currentGameScore;
//	private File recordFile = new File("RecordGame.txt");
//	private String movesRecordedString = "";
	
	public GeneralGameBoard (){
		createFile();
	}
	
	@Override
	public void makeMove(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
//			After making a move, the gamestate is updated
			updateGameState(turn, row, column, redPlayer, bluePlayer); 
			System.out.println("TURN: " + turn);
			if(recordKey == true) {
				recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
			}
			
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
				makeAutoMove(boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
			}
			
			
			
			 
			 
		}
	}
	
	@Override
	public void recordMoves(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, char turn) {
		
		super.recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
		redOpp = (cpuPlayerKeyRed == 'R') ? "Computer"  : "Human";
		blueOpp = (cpuPlayerKeyBlue == 'B') ? "Computer"  : "Human";
		try {
			if(isFilled()) {
		      FileWriter myWriter = new FileWriter("RecordGame.txt");
		      System.out.println(modeString);
		    	  myWriter.write(modeString + " mode:\n" + "Red is " + redOpp + " as the " + redPlayer + " symbol \n" 
		    			  + "Blue is " + blueOpp + " as the " + bluePlayer + " symbol \n" + movesRecordedString + "\n" + currentGameState + "\n" + 
		    		  "Red Points: " + getPointRed() + "\n" + "Blue Points: " +getPointBlue());
		      System.out.println("Successfully wrote to the file.");
			  myWriter.close();
			}
			
		}
		catch (IOException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
//	Makes a automated move
	public void makeAutoMove(int size, char redPlayer, char bluePlayer, 
			char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if (!makeWinningMove()) {
			if (!blockOpponentWinningMove())
				makeRandomMove(size, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
		}
	}
	
	private void makeRandomMove(int size, char redPlayer, char bluePlayer, 
			char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
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
						makeMove(row, col, size, redPlayer, bluePlayer, 
								cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
						return;
					} else
						index++;
				}
			}
		}
	}
	
//	Make a random first move
	public void makeFirstMove(int size, char redPlayer, char bluePlayer, 
			char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		Random random = new Random();
		int position = random.nextInt(size * size);
		System.out.println("CPU RED: " + cpuPlayerKeyRed);
		System.out.println("CPU BLUE: " + cpuPlayerKeyBlue);
		setRow(position/size);
		setCol(position%size);
		makeMove(position/size, position%size, size, redPlayer, bluePlayer, 
				cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
	}
	
//	FOR TESTING PURPOSES
	public void testingAutomatedMove(int row, int column, int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING) {
			makeMove(row, column, size, redPlayer, bluePlayer, 
					cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
			
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