package productSOSgame;


public class SimpleGameBoard extends Board {
	
	public void makeMoveInSimpleMode(int row, int column, int boardSize, char redPlayer, char bluePlayer) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
			updateGameState(turn, row, column, redPlayer, bluePlayer); 
			
			turn = (turn == 'R')? 'B' : 'R';
			
		}
	}
	
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
