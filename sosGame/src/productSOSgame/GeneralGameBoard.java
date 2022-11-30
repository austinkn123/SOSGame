package productSOSgame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneralGameBoard extends Board {
//	public enum GameStateGeneral {RED_SCORES, BLUE_SCORES, NO_SCORE};
//	protected GameStateGeneral currentGameScore;
	
//	private File recordFile = new File("RecordGame.txt");
//	private String movesRecordedString = "";
	
	public GeneralGameBoard (){
		createFile();
	}
	
//	@Override
//	public void makeMove(int row, int column, int boardSize, char redPlayer, 
//			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
//		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
//			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
//			
//			
////			After making a move, the gamestate is updated
//			updateGameState(turn, row, column, redPlayer, bluePlayer); 
//			if(recordKey == true) {
//				recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
//			}
////			Turn is still with the player if they scored
//			if(currentGameState == GameState.RED_SCORES) {
//				turn = 'R';
//			}
//			else if(currentGameState == GameState.BLUE_SCORES) {
//				turn = 'B';
//			}
//			else {
//				turn = (turn == 'R')? 'B' : 'R';
//			}
//			
////			Making a computer move if the char key for computer matches
//			if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING) {
//				makeAutoMove(boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
//			}
//			 
//		}
//	}
	
	@Override
	public void makeMove(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
			super.makeMove(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
			
	//		Turn is still with the player if they scored
			if(currentGameState == GameState.RED_SCORES) {
				turn = 'R';
			}
			else if(currentGameState == GameState.BLUE_SCORES) {
				turn = 'B';
			}
			else {
				turn = (turn == 'R')? 'B' : 'R';
			}
			
	//	    Making a computer move if the char key for computer matches
			if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING
					|| currentGameState == GameState.RED_SCORES || currentGameState == GameState.BLUE_SCORES) {
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
	
//	FOR TESTING PURPOSES
	public void testingAutomatedMove(int row, int column, int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING) {
			makeMove(row, column, size, redPlayer, bluePlayer, 
					cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
			
		}
	}
	
//	Checking the game state to see if a player has scored
	@Override
	public void updateGameState(char turn, int row, int column, char redPlayer, char bluePlayer) {
		if (hasScored(turn, row, column, size, redPlayer, bluePlayer)) { // check for player scoring
			currentGameState = (turn == 'R') ? GameState.RED_SCORES : GameState.BLUE_SCORES;
		} // Otherwise, no change to current state (still GameState.PLAYING).
		else {
			currentGameState = GameState.PLAYING;
		}
		if(isFilled()) {
			checkGameScore();
		}
		
		
	}
	
//	Checks score when game ends to decide winner
//	private void checkGameScore() {
//		System.out.println(getPointRed());
//		if(getPointRed() > getPointBlue()) {
//			currentGameState = GameState.RED_WINS;
//			System.out.println("RED WINS");
//			System.out.println("RED--" + getPointRed());
//			System.out.println("BLUE--" + getPointBlue());
//		}
//		else if (getPointRed() < getPointBlue()){
//			currentGameState = GameState.BLUE_WINS;
//			System.out.println("BLUE WINS");
//			System.out.println("RED--" + getPointRed());
//			System.out.println("BLUE--" + getPointBlue());
//		}
//		else if (getPointRed() == getPointBlue()){
//			currentGameState = GameState.DRAW;
//			System.out.println("DRAW");
//			System.out.println("RED--" + getPointRed());
//			System.out.println("BLUE--" + getPointBlue());
//		}
//	}
	
}