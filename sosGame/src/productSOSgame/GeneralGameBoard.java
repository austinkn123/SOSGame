package productSOSgame;

import java.io.FileWriter;
import java.io.IOException;

public class GeneralGameBoard extends Board {
	
	public GeneralGameBoard (){
		createFile();
	}
	
	@Override
	public void makeMove(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
			super.makeMove(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
			
	//		Turn is still with the player if they scored
			if(currentGameState == GameState.RED_SCORES) {
				turn = 'R';
				currentGameState = GameState.PLAYING;
				scoreStatusRed = true;
				
			}
			else if(currentGameState == GameState.BLUE_SCORES) {
				turn = 'B';
				currentGameState = GameState.PLAYING;
				scoreStatusBlue = true;
				
			}
			else {
				turn = (turn == 'R')? 'B' : 'R';
				scoreStatusRed = false;
				scoreStatusBlue = false;
			}
			
	//	    Making a computer move if the char key for computer matches
			if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING
					) {
				makeAutoMove(boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
			}
			 
		}
	}
	
	@Override
	public void recordMoves(int row, int column, int boardSize, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, char turn) {
		
		super.recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
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
		super.updateGameState(turn, row, column, redPlayer, bluePlayer);
		if(isFilled()) {
			checkGameScore();
		}
	}
	
	
}