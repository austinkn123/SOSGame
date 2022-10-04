package testSOSgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.Console;

public class TestBoardConsole {
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		//Input any number to create a new board
		board = new Board(5); 
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyBoard() {
		//Input any number greater than 2 for displayBoard 
		new Console(board).displayBoard(5);
	}
	
}