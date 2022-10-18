package testSOSgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.Console;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;

public class TestBoardConsole { 
	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 8;
	
	@Before
	public void setUp() throws Exception {
		//Input any number to create a new board
		board = new Board(); 
		generalBoard =  new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSizeBoard(size);
		generalBoard.setSizeGeneral(size); 
		simpleBoard.setSizeBoard(size);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyBoard() {
		//Input any number greater than 2 for displayBoard 
		System.out.println("EMPTY BOARD OF NO MODE");
		new Console(board).displayBoard(size);
	}
	
	@Test
	public void testNonEmptyBoard() {
		System.out.println("NON EMPTY BOARD");
		board.makeMove(0, 0, size);
		board.makeMove(1, 1, size);
		new Console(board).displayBoard(size);
	}
	
	@Test
	public void testEmptyBoardGeneral() {
		//Input any number greater than 2 for displayBoard 
		System.out.println("EMPTY GENERAL MODE");
		new Console(generalBoard).displayBoardGeneral(size, generalBoard);
	}
	
	@Test
	public void testNonEmptyBoardGeneral() {
		//Input any number greater than 2 for displayBoard 
		System.out.println("NON EMPTY GENERAL MODE");
		generalBoard.makeMove(0, 0, size);
		generalBoard.makeMove(1, 2, size);
		new Console(generalBoard).displayBoardGeneral(size, generalBoard);
	}
	
	@Test
	public void testEmptyBoardSimple() {
		//Input any number greater than 2 for displayBoard 
		System.out.println("EMPTY SIMPLE MODE");
		new Console(simpleBoard).displayBoardSimple(size, simpleBoard);
	}
	
	@Test
	public void testNonEmptyBoardSimple() {
		//Input any number greater than 2 for displayBoard 
		System.out.println("NON EMPTY SIMPLE MODE");
		simpleBoard.makeMove(0, 0, size);
		simpleBoard.makeMove(1, 1, size);
		new Console(simpleBoard).displayBoardSimple(size, simpleBoard);
	}
	 
	
	
	
}