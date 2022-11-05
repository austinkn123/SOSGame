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
		generalBoard.setSizeBoard(size);
		simpleBoard.setSizeBoard(size);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 4.1
	@Test
	public void testRedTurnMoveVacantCellinSimpleMode() {
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X'); //RED is S
		assertEquals("", simpleBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	//acceptance criterion 4.2
	@Test
	public void testRedTurnMoveNonVacantCellinSimpleMode() { 
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X'); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X'); //BLUE is O
		assertEquals("", simpleBoard.getCell(0, 1, size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'R');
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X'); //RED is S
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	//acceptance criterion 4.3
	@Test
	public void testRedTurnInvalidSizeMoveinSimpleMode() {
		simpleBoard.makeMoveInSimpleMode(0, size + 1, size, 'S', 'O', 'X', 'X'); //RED is S
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.1
	@Test
	public void testRedTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X');
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	@Test
	public void testRedTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S', 'X', 'X');
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.2
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X');
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X');
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X');
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S', 'X', 'X');
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S', 'X', 'X');
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S', 'X', 'X');
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.3
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoard.makeMoveInGeneralMode(size + 1, 0, size, 'S', 'O', 'X', 'X');
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoard.makeMoveInGeneralMode(size + 1, 0, size, 'O', 'S', 'X', 'X');
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	// acceptance criterion 6.4
	//S player
	@Test
	public void scoredPointasPlayerS() {
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X');
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X');
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X');
		assertEquals("", generalBoard.getGameScore(), GameStateGeneral.RED_SCORES); 
		assertEquals("", generalBoard.getPointRed(), 1); 
		assertEquals("", generalBoard.getTurn(), 'R'); 
	}
	
	//O Player
	@Test
	public void scoredPointasPlayerO() {
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'O', 'S', 'X', 'X'); //O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S', 'X', 'X'); //S
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S', 'X', 'X'); //O
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'O', 'S', 'X', 'X'); //S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'O', 'S', 'X', 'X'); //O
		assertEquals("", generalBoard.getGameScore(), GameStateGeneral.RED_SCORES); 
		assertEquals("", generalBoard.getPointRed(), 1); 
		assertEquals("", generalBoard.getTurn(), 'R'); 
	}
	
}
