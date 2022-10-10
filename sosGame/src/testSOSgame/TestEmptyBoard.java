package testSOSgame;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.Board.Cell;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;

public class TestEmptyBoard {
	//Create new board of 5x5 (if number is changed, testNewBoard row/col must be changed
	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 7;

	@Before
	public void setUp() throws Exception {
		//Size of board is 5
		board = new Board(); 
		generalBoard =  new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSize(size);
		generalBoard.setSize(size);
		simpleBoard.setSize(size);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 1
	//Test board with 5 and turn S after
	@Test
	public void testNewBoardTurn() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", board.getCell(row, column, size), Cell.EMPTY); 
			}
		}
		assertEquals("", board.getTurn(), 'S'); 
	}

	
	// acceptance criterion 1.2
	@Test
	public void testInvalidSize() {
		//row, col, size
		assertEquals("", board.getCell((size + 1), 0, size), null); 
	}
	
	@Test
	public void testInvalidSizeGeneral() {
		//row, col, size
		assertEquals("", generalBoard.getCell((size + 1), 0, size), null); 
	}
	
	@Test
	public void testInvalidSizeSimple() {
		//row, col, size
		assertEquals("", simpleBoard.getCell((size + 1), 0, size), null); 
	}
	
	@Test
	public void testNewSimpleBoardTurn() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", simpleBoard.getCell(row, column, size), Cell.EMPTY); 
			}
		}
		assertEquals("", board.getTurn(), 'S'); 
	}
	
	@Test
	public void testNewGeneralBoardTurn() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", generalBoard.getCell(row, column, size), Cell.EMPTY); 
			}
		}
		assertEquals("", board.getTurn(), 'S'); 
	}
	
	
	
	 
}