package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;

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
		assertEquals("", simpleBoard.getCell(0, 0, size), 2);
		assertEquals("", simpleBoard.getTurn(), 'S');
	}
	
	// acceptance criterion 4.5
	@Test
	public void testNoughtTurnMoveNonVacantCell() {
		simpleBoard.makeMove(0, 0, size); // O move
		simpleBoard.makeMove(1, 0, size); // S move
		assertEquals("", simpleBoard.getTurn(), 'O');
		simpleBoard.makeMove(1, 0, size); // invalid O move
		assertEquals("", simpleBoard.getTurn(), 'O');
	}
	
	// acceptance criterion 4.6
	@Test
	public void testNoughtTurnInvalidRowMove() {
		simpleBoard.makeMove((size + 1), 0, size); 
		assertEquals("", simpleBoard.getTurn(), 'O');
	}
	

}
