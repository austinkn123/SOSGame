package productSOSgame;

import java.util.Scanner;

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
		GeneralGameBoard generalMode = new GeneralGameBoard();
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
		modeWord = inputMode.next();
		
		inputSize.close();
		inputMode.close();

	    System.out.println("Board: " + size);
	    if (modeWord != "General" || modeWord != "Simple") {
	    	modeWord = "Not Selected";
	    }
	    System.out.println("Mode: " + modeWord);
	    
	    if(modeWord == "General") {
	    	generalMode.setSize(size);
			new Console(generalMode).displayBoard(size);
		}
		else if (modeWord == "Simple") {
			simpleMode.setSize(size);
			new Console(simpleMode).displayBoard(size);
		}
		else {
			modeWord = "Not Selected";
			newBoard.setSize(size);
			new Console(newBoard).displayBoard(size);
			
			
		}
	    
	    
	}
		
	
	public void displayBoard(int boardSize) {
//		board.setSize(boardSize);
		for(int row = 0; row < boardSize; row++) {
	    	for(int col = 0; col < boardSize; col++) {
	    		System.out.print("|" + board.getCell(row, col, boardSize));
	    	}
	    	System.out.print("|");
	    	System.out.println();
	    }
		
	}
	

}
