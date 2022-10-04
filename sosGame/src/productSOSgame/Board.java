package productSOSgame;

public class Board {
	private int[][] grid;
	private char turnS = 'S';
	private char turnO = 'O'; 

	public Board(int num) {
		grid = new int[num][num];
	}

	public int getCell(int row, int column) {
		return grid[row][column];
	}

	public char getTurnS() {
		return turnS;
	}
	
	public char getTurnO() {
		return turnO;
	}
}