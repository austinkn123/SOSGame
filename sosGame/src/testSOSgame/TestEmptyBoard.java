package testSOSgame;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;

public class TestEmptyBoard {
	//Create new board of 5x5 (if number is changed, testNewBoard row/col must be changed
	private Board board;
	private int size = 5;

	@Before
	public void setUp() throws Exception {
		//Size of board is 5
		board = new Board(5);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 1
	//Test board with 5 and turn S after
	@Test
	public void testNewBoardTurnS() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", board.getCell(row, column, size), 0); 
			}
		}
		assertEquals("", board.getTurn(), 'S'); 
	}

	
	// acceptance criterion 1.2
	@Test
	public void testInvalidSize() {
		//row, col, size
		assertEquals("", board.getCell(6, 0, size), -1); 
	}
	
}