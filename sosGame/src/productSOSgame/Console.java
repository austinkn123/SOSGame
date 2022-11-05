package productSOSgame;

import java.util.Scanner;

import productSOSgame.Board.Cell;

public class Console {
	//object board
	private Board board;
	private SimpleGameBoard simpleMode;
	private GeneralGameBoard generalMode;

	//Constructor board
	public Console(Board board) {
		this.board = board;
//		this.simpleMode = simpleMode;
//		this.generalMode = generalMode;
	}
	
	public static void main(String[] args) {
		
		int size = 0;
		String modeWord = "Not Selected";
		
		Board newBoard = new Board();
//		GeneralGameBoard generalMode = new GeneralGameBoard();
		SimpleGameBoard simpleMode = new SimpleGameBoard();
		
		Scanner inputSize = new Scanner(System.in);
		while(size < 3) {
			System.out.print("Enter the number that will be rows/column: ");
			size = inputSize.nextInt();
			if(size < 3){
				System.out.println("Number must be greater than 2");
			}
		}
		
		System.out.print("Enter the game mode (General or Simple): ");
		Scanner inputMode  = new Scanner(System.in);
		modeWord = inputMode.nextLine();
		
		
		
		inputSize.close();
		inputMode.close();
		


	    System.out.println("Mode: " + modeWord);
	    
	    if(modeWord == "General") {
//	    	generalMode.setSize(size);
//			new Console(generalMode).displayBoardGeneral(size, generalMode);
		}
		else if (modeWord == "Simple") {
			System.out.println("SSSSSSSSSSSSSSSS");
			simpleMode.setSize(size);
			new Console(simpleMode).displayBoardSimple(size, simpleMode);
		}
		else {
			modeWord = "Not Selected";
			newBoard.setSize(size);
			new Console(newBoard).displayBoard(size);
		}
	    
	}
		
	
	private char symbol(Cell cell) {
		if (cell==Cell.RED_PLAYER)
			return 'R';
		else
			if (cell==Cell.BLUE_PLAYER)
				return 'B';
			else return ' ';
	}
	
	public void displayBoard(int boardSize) {
		
		for(int row = 0; row < boardSize; row++) {
	    	for(int col = 0; col < boardSize; col++) {
	    		System.out.print("|" + symbol(board.getCell(row, col, boardSize)));
	    	}
	    	System.out.print("|");
	    	System.out.println();
	    }
		 
	}
	
	public void displayBoardGeneral(int boardSize, GeneralGameBoard generalMode) {
		this.generalMode = generalMode;
		for(int row = 0; row < boardSize; row++) {
	    	for(int col = 0; col < boardSize; col++) {
	    		System.out.print("|" + symbol(generalMode.getCell(row, col, boardSize)));
	    	}
	    	System.out.print("|");
	    	System.out.println();
	    }
		
	}
	
	public void displayBoardSimple(int boardSize, SimpleGameBoard simpleMode) {
//		board.setSize(boardSize);
		this.simpleMode = simpleMode;
		for(int row = 0; row < boardSize; row++) {
	    	for(int col = 0; col < boardSize; col++) {
	    		System.out.print("|" + symbol(simpleMode.getCell(row, col, boardSize)));
	    	}
	    	System.out.print("|");
	    	System.out.println();
	    }
	}
}
