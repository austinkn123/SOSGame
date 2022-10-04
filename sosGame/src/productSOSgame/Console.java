package productSOSgame;

import java.util.Scanner;

public class Console {
	//object board
	private Board board;
	
	//Constructor board
	public Console(Board board) {
		this.board = board;
	}
	
	public static void main(String[] args) {
		
		// acceptance criterion 1.2
		Scanner input = new Scanner(System.in);
		int size = 0;
		while(size < 3) {
			System.out.print("Enter the number that will be rows/column: ");
			size = input.nextInt();
			if(size < 3){
				System.out.println("Number must be greater than 2");
			}
		}
		
		input.close();

	    System.out.println("Board: " + size);
	    
	    new Console(new Board(size)).displayBoard(size);
	}
		
	
	public void displayBoard(int num) {
		for(int row = 0; row < num; row++) {
	    	for(int col = 0; col < num; col++) {
	    		System.out.print("|" + board.getCell(row, col, num));
	    	}
	    	System.out.print("|");
	    	System.out.println();
	    }
		
	}
	

}
