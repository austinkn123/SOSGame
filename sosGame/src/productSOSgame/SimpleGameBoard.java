package productSOSgame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SimpleGameBoard extends Board {
	
	public SimpleGameBoard (){
		createFile();
	}
	
	
	@Override
	public void makeMove(int row, int column, int boardSize, char redPlayer, char bluePlayer,
			char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER;
			
			super.makeMove(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
		
	//		After making a move, the gamestate is updated
//			updateGameState(turn, row, column, redPlayer, bluePlayer); 
//			
//			if(recordKey == true) {
//				recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
//			}
			
			turn = (turn == 'R')? 'B' : 'R';
			
	//		Making a computer move if the char key for computer matches
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
			if(currentGameState == GameState.RED_WINS || currentGameState == GameState.BLUE_WINS
					|| currentGameState == GameState.DRAW) {
		      FileWriter myWriter = new FileWriter("RecordGame.txt");
		      System.out.println(modeString);
		    	  myWriter.write(modeString + " mode:\n Red is " + redOpp + " as the " + redPlayer + " symbol \n" 
		    			  + "Blue is " + blueOpp + " as the " + bluePlayer + " symbol \n" + movesRecordedString + currentGameState + "\n");
		      System.out.println("Successfully wrote to the file.");
			  myWriter.close();
			}
			
		}
		catch (IOException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//If a player has scored, or if the board is filled with nobody scoring then end the game 
	@Override
	public void updateGameState(char turn, int row, int column, char redPlayer, char bluePlayer) {
		super.updateGameState(turn, row, column, redPlayer, bluePlayer);
		if(getPointRed() > 0 || getPointBlue() > 0) {
			checkGameScore();
		}
		
	}

}
