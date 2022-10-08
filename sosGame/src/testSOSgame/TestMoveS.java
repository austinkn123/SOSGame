package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;


public class TestMoveS {

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
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 4.1
	@Test
	public void testSTurnMoveVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getCell(0, 0, size), 1);
		assertEquals("", simpleBoard.getTurn(), 'O');
	}
	//acceptance criterion 4.2
	@Test
	public void testSTurnMoveNonVacantCell() {
		simpleBoard.makeMove(0, 0, size);
		simpleBoard.makeMove(1, 0, size);
		assertEquals("", simpleBoard.getCell(1, 0, size), 2);
		assertEquals("", simpleBoard.getTurn(), 'S');
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getTurn(), 'S');
	}
	
	//acceptance criterion 4.3
	@Test
	public void testSTurnInvalidSizeMove() {
		simpleBoard.makeMove((size + 1), 0, size);
		assertEquals("", simpleBoard.getTurn(), 'S');
	}
	
	// acceptance criterion 6.1
	@Test
	public void testSTurnMoveVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size);
		assertEquals("", generalBoard.getCell(0, 0, size), 1);
		assertEquals("", generalBoard.getTurn(), 'O');
	}
}
