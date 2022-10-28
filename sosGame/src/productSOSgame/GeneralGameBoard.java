package productSOSgame;

public class GeneralGameBoard extends Board {
	public enum GameStateGeneral {RED_SCORES, BLUE_SCORES, NO_SCORE};
	protected GameStateGeneral currentGameScore;
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
	
	public void makeMoveInGeneralMode(int row, int column, int boardSize, char redPlayer, char bluePlayer) {
		if ((row >= 0) && (row < boardSize) && (column >= 0) && (column < boardSize) && (grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R')? Cell.RED_PLAYER : Cell.BLUE_PLAYER; 
			
			updateGameState(turn, row, column, redPlayer, bluePlayer); 
			
			if(currentGameScore == GameStateGeneral.RED_SCORES) {
				turn = 'R';
			}
			else if(currentGameScore == GameStateGeneral.BLUE_SCORES) {
				turn = 'B';
			}
			else {
				turn = (turn == 'R')? 'B' : 'R';
			}
			
		}
	}
	
	public void setGameScore(GameStateGeneral currentGameScore) {
		this.currentGameScore = currentGameScore;
	}
	
	public GameStateGeneral getGameScore() {
		return currentGameScore;
	}
	
	private void updateGameState(char turn, int row, int column, char redPlayer, char bluePlayer) {
		if (hasScored(turn, row, column, size, redPlayer, bluePlayer)) { // check for player scoring
			currentGameScore = (turn == 'R') ? GameStateGeneral.RED_SCORES : GameStateGeneral.BLUE_SCORES;
			if(currentGameScore == GameStateGeneral.RED_SCORES) {
				addPointRed();
			}
			else if(currentGameScore == GameStateGeneral.BLUE_SCORES) {
				addPointBlue();
			}
		} // Otherwise, no change to current state (still GameState.PLAYING).
		else {
			currentGameState = GameState.PLAYING;
			currentGameScore = GameStateGeneral.NO_SCORE;
		}
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
				System.out.println("DRAWWW");
				System.out.println("RED--" + getPointRed());
				System.out.println("BLUE--" + getPointBlue());
			}
		}
	}
	
	
	
}