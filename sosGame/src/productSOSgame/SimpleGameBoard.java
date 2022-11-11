package productSOSgame;

import java.util.Random;

public class SimpleGameBoard extends Board {
	
	public void makeMoveInSimpleMode(int row, int column, int boardSize, char redPlayer, char bluePlayer,
			char cpuPlayerKeyRed, char cpuPlayerKeyBlue) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER;
			
//			After making a move, the gamestate is updated
			updateGameState(turn, row, column, redPlayer, bluePlayer); 
			
			turn = (turn == 'R')? 'B' : 'R';
			
//			Making a computer move if the char key for computer matches
			if ((turn == cpuPlayerKeyRed || turn == cpuPlayerKeyBlue) && currentGameState == GameState.PLAYING) {
				makeAutoMove(boardSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue);
			}
			
		}
	}
	//Makes a automated move
	private void makeAutoMove(int size, char redPlayer, 
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
		//Generate random number within number of empty cells
		int targetMove = random.nextInt(numberOfEmptyCells);
		int index=0;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					if (targetMove == index) {
						setRow(row);
						setCol(col);
						makeMoveInSimpleMode(row, col, size, redPlayer, bluePlayer, 
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
		setRow(position/size);
		setCol(position%size);
		makeMoveInSimpleMode(position/size, position%size, size, redPlayer, bluePlayer, 
				cpuPlayerKeyRed, cpuPlayerKeyBlue);
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
