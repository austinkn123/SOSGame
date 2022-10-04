package testSOSgame;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;

public class TestEmptyBoard {
	//Create new board of 5x5 (if number is changed, testNewBoard row/col must be changed
	private Board board;

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
		for (int row = 0; row<5; row++) {
			for (int column = 0; column<5; column++) {
				//"" means message
				assertEquals("", board.getCell(row, column, 5), 0); 
			}
		}
		assertEquals("", board.getTurnS(), 'S'); 
	}
	
	//Test board with 5 and turn O after
	@Test
	public void testNewBoardTurnO() {
		for (int row = 0; row<5; row++) {
			for (int column = 0; column<5; column++) {
				//"" means message
				assertEquals("", board.getCell(row, column, 5), 0); 
			}
		}
		assertEquals("", board.getTurnO(), 'O'); 
	}
	
	// acceptance criterion 1.2
	@Test
	public void testInvalidSize() {
		//row, col, size
		assertEquals("", board.getCell(6, 0, 5), -1); 
	}
	
}