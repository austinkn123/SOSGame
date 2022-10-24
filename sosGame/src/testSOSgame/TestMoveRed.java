package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.GeneralGameBoard.GameStateGeneral;
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
		simpleBoard.makeMove(0, 0, size); //Invalid Move
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
	public void testRedTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O');
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	@Test
	public void testRedTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S');
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.2
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O');
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O');
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O');
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S');
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S');
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S');
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.3
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoard.makeMoveInGeneralMode(size + 1, 0, size, 'S', 'O');
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoard.makeMoveInGeneralMode(size + 1, 0, size, 'O', 'S');
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	// acceptance criterion 6.4
	//S player
	@Test
	public void scoredPointasPlayerS() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O');
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O');
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O');
		assertEquals("", generalBoard.getGameScore(), GameStateGeneral.RED_SCORES); 
		assertEquals("", generalBoard.getPointRed(), 1); 
		assertEquals("", generalBoard.getTurn(), 'R'); 
	}
	
	//O Player
	@Test
	public void scoredPointasPlayerO() {
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'O', 'S'); //O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S'); //S
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S'); //O
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'O', 'S'); //S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'O', 'S'); //O
		assertEquals("", generalBoard.getGameScore(), GameStateGeneral.RED_SCORES); 
		assertEquals("", generalBoard.getPointRed(), 1); 
		assertEquals("", generalBoard.getTurn(), 'R'); 
	}
	
}
