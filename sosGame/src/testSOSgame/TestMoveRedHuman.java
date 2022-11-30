package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
//import productSOSgame.GeneralGameBoard.GameStateGeneral;
import productSOSgame.SimpleGameBoard;
import productSOSgame.Board.Cell;
import productSOSgame.Board.GameState;


public class TestMoveRedHuman {

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
		simpleBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", simpleBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	//acceptance criterion 4.2
	@Test
	public void testRedTurnMoveNonVacantCellinSimpleMode() { 
		simpleBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMove(0, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		assertEquals("", simpleBoard.getCell(0, 1, size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'R');
		simpleBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	//acceptance criterion 4.3
	@Test
	public void testRedTurnInvalidSizeMoveinSimpleMode() {
		simpleBoard.makeMove(0, size + 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.1
	@Test
	public void testRedTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false);
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	@Test
	public void testRedTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoard.makeMove(0, 0, size, 'O', 'S', 'X', 'X', false);
		assertEquals("", generalBoard.getCell(0, 0, size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.2
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false);
		generalBoard.makeMove(1, 0, size, 'S', 'O', 'X', 'X', false);
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	@Test
	public void testRedTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoard.makeMove(0, 0, size, 'O', 'S', 'X', 'X', false);
		generalBoard.makeMove(1, 0, size, 'O', 'S', 'X', 'X', false);
		assertEquals("", generalBoard.getCell(1, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'R');
		generalBoard.makeMove(0, 0, size, 'O', 'S', 'X', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 6.3
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoard.makeMove(size + 1, 0, size, 'S', 'O', 'X', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	@Test
	public void testRedTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoard.makeMove(size + 1, 0, size, 'O', 'S', 'X', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'R');
	} 
	
	// acceptance criterion 6.4
	//S player
	@Test
	public void scoredPointAsPlayerS() {
		generalBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false);
		generalBoard.makeMove(1, 0, size, 'S', 'O', 'X', 'X', false);
		generalBoard.makeMove(2, 0, size, 'S', 'O', 'X', 'X', false);
		assertEquals("", generalBoard.getGameState(), GameState.RED_SCORES); 
		assertEquals("", generalBoard.getPointRed(), 1); 
		assertEquals("", generalBoard.getTurn(), 'R'); 
	}
	
	//O Player
	@Test
	public void scoredPointAsPlayerO() {
		generalBoard.makeMove(1, 1, size, 'O', 'S', 'X', 'X', false); //O
		generalBoard.makeMove(0, 0, size, 'O', 'S', 'X', 'X', false); //S
		generalBoard.makeMove(1, 0, size, 'O', 'S', 'X', 'X', false); //O
		generalBoard.makeMove(0, 2, size, 'O', 'S', 'X', 'X', false); //S
		generalBoard.makeMove(0, 1, size, 'O', 'S', 'X', 'X', false); //O
		assertEquals("", generalBoard.getGameState(), GameState.RED_SCORES); 
		assertEquals("", generalBoard.getPointRed(), 1); 
		assertEquals("", generalBoard.getTurn(), 'R'); 
	}
	
}
