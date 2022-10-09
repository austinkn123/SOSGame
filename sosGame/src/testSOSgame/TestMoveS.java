package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.Board.Cell;


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
		assertEquals("", simpleBoard.getCell(0, 0, size), Cell.SPLAYER);
		assertEquals("", simpleBoard.getTurn(), 'O');
	}
	//acceptance criterion 4.2
	@Test
	public void testSTurnMoveNonVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size);
		simpleBoard.makeMove(1, 0, size);
		assertEquals("", simpleBoard.getCell(1, 0, size), Cell.OPLAYER);
		assertEquals("", simpleBoard.getTurn(), 'S');
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getTurn(), 'S');
	}
	
	//acceptance criterion 4.3
	@Test
	public void testSTurnInvalidSizeMoveinSimpleMode() {
		simpleBoard.makeMove((size + 1), 0, size);
		assertEquals("", simpleBoard.getTurn(), 'S');
	}
	
	// acceptance criterion 6.1
	@Test
	public void testSTurnMoveVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size);
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.SPLAYER);
		assertEquals("", generalBoard.getTurn(), 'O');
	}
	
	// acceptance criterion 6.2
	@Test
	public void testSTurnMoveNonVacantCellinGeneralMode() {
		generalBoard.makeMove(0, 0, size);
		generalBoard.makeMove(1, 0, size);
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.OPLAYER);
		assertEquals("", generalBoard.getTurn(), 'S');
		generalBoard.makeMove(0, 0, size);
		assertEquals("", generalBoard.getTurn(), 'S');
	}
	
	// acceptance criterion 6.3
	@Test
	public void testSTurnInvalidSizeMoveinGeneralMode() {
		generalBoard.makeMove((size + 1), 0, size);
		assertEquals("", generalBoard.getTurn(), 'S');
	}
	
	// acceptance criterion 6.4
	
	
}
