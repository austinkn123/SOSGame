package testSOSgame;

import static org.junit.Assert.*;

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
		board.setSizeBoard(size);
		generalBoard.setSizeGeneral(size);
		simpleBoard.setSizeBoard(size);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 1.1
	//Test board and turn R after
	@Test
	public void testNewEmptyBoard() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", board.getCell(row, column, size), Cell.EMPTY); 
			}
		}
		assertEquals("", board.getTurn(), 'R'); 
	}

	
	// acceptance criterion 1.2
	@Test
	public void testInvalidSize() {
		//row, col, size
		assertEquals("", board.getCell((size + 1), 0, size), null); 
		assertEquals("", generalBoard.getCell((size + 1), 0, size), null); 
		assertEquals("", simpleBoard.getCell((size + 1), 0, size), null); 
	}
	
	//acceptance criterion 2.1 - 2.3 Test if the game mode and board size is valid
	@Test
	public void testValidGameModeandSize() {
		assertEquals("", board.setMode("GENERAL", size), 1); 
		assertEquals("", board.setMode("SIMPLE", size), 2); 
		assertEquals("", board.setMode("Non Selected", size), -1); 
		assertEquals("", board.setMode("GENERAL", 0), -1); 
		assertEquals("", board.setMode("SIMPLE", 0), -1); 
	}
	
	//acceptance criterion 3.1 start with simple mode and size is valid
	@Test
	public void testNewSimpleBoardTurn() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", simpleBoard.getCell(row, column, size), Cell.EMPTY); 
			}
		}
		assertEquals("", board.getTurn(), 'R'); 
	}
	
	//acceptance criterion 3.2 start with general mode and size is valid
	@Test
	public void testNewGeneralBoardTurn() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				//"" means message
				assertEquals("", generalBoard.getCell(row, column, size), Cell.EMPTY); 
			}
		}
		assertEquals("", board.getTurn(), 'R'); 
	}
}