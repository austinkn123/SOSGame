package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.Board.Cell;

public class TestMoveO {

	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 5;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		generalBoard = new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSize(size);
		generalBoard.setSize(size);
		simpleBoard.setSize(size);
		simpleBoard.makeMove(1, 1, size);
		generalBoard.makeMove(1, 1, size);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 4.4
	@Test
	public void testOTurnMoveVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getCell(0, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 4.5
	@Test
	public void testOTurnMoveNonVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size); // O move
		simpleBoard.makeMove(1, 0, size); // S move
		assertEquals("", simpleBoard.getTurn(), 'B');
		simpleBoard.makeMove(1, 0, size); // invalid O move
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 4.6
	@Test
	public void testOTurnInvalidSizeMoveinSimpleMode() {
		simpleBoard.makeMove((size + 1), 0, size); 
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.5
	@Test
	public void testOTurnMoveVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size);
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.6
	@Test
	public void testOTurnMoveNonVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size); // O move
		generalBoard.makeMove(1, 0, size); // S move
		assertEquals("", generalBoard.getTurn(), 'B');
		generalBoard.makeMove(1, 0, size); // invalid O move
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.7
	@Test
	public void testOTurnInvalidSizeMoveinGeneralMode() {
		generalBoard.makeMove((size + 1), 0, size); 
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.8 (for next sprint)
	

}
