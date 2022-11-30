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
			
//			After making a move, the gamestate is updated
			updateGameState(turn, row, column, redPlayer, bluePlayer); 
			
			if(recordKey == true) {
				recordMoves(row, column, boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, turn);
			}
			
			turn = (turn == 'R')? 'B' : 'R';
			
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
	
	//Makes a automated move
	private void makeAutoMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		if (!makeWinningMove()) {
			if (!blockOpponentWinningMove())
				makeRandomMove(size, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
		}

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
//	Make a random first move
	public void makeFirstMove(int size, char redPlayer, 
			char bluePlayer, char cpuPlayerKeyRed, char cpuPlayerKeyBlue, Boolean recordKey) {
		Random random = new Random();
		int position = random.nextInt(size * size);
		setRow(position/size);
		setCol(position%size);
		makeMove(position/size, position%size, size, redPlayer, bluePlayer, 
				cpuPlayerKeyRed, cpuPlayerKeyBlue, recordKey);
	}
	
	//If a player has scored, or if the board is filled with nobody scoring then end the game 
	private void updateGameState(char turn, int row, int column, char redPlayer, char bluePlayer) {
		if (hasScored(turn, row, column, size, redPlayer, bluePlayer)) { // check for player scoring
			currentGameState = (turn == 'R') ? GameState.RED_WINS : GameState.BLUE_WINS;
		} // Otherwise, no change to current state (still GameState.PLAYING).
		else {
			currentGameState = GameState.PLAYING;
		}
		if (isFilled() && (currentGameState == GameState.PLAYING)) {
			currentGameState = GameState.DRAW; 
		}
	}

}
