package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.Board.Cell;


public class TestMoveRed {

	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 5;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		generalBoard = new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSizeBoard(size);
		generalBoard.setSizeGeneral(size);
		simpleBoard.setSizeBoard(size);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 4.1
	@Test
	public void testRedTurnMoveVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	//acceptance criterion 4.2
	@Test
	public void testRedTurnMoveNonVacantCellinSimpleMode() { 
		simpleBoard.makeMove(0, 0, size);
		simpleBoard.makeMove(1, 0, size);
		assertEquals("", simpleBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'R');
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	//acceptance criterion 4.3
	@Test
	public void testRedTurnInvalidSizeMoveinSimpleMode() {
		simpleBoard.makeMove((size + 1), 0, size);
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.1
	@Test
	public void testRedTurnMoveVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size);
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.2
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size);
		generalBoard.makeMove(1, 0, size);
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMove(0, 0, size);
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.3
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralMode() {
		generalBoard.makeMove((size + 1), 0, size);
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	// acceptance criterion 6.4 (for next sprint)
	
	
}
