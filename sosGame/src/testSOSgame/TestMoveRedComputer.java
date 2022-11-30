package testSOSgame;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.Board.Cell;
import productSOSgame.Board.GameState;

public class TestMoveRedComputer {

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
	
	// acceptance criterion 8.1
	@Test
	public void testAutomatedRedTurnMoveVacantCellinSimpleModePlayerS() {
		simpleBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		assertEquals("", simpleBoard.getCell(simpleBoard.getRow(), simpleBoard.getCol(), size), Cell.RED_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	@Test
	public void testAutomatedRedTurnMoveVacantCellinSimpleModePlayerO() {
		simpleBoard.makeFirstMove(size, 'O', 'S', 'R', 'X', false);
		assertEquals("", simpleBoard.getCell(simpleBoard.getRow(), simpleBoard.getCol(), size), Cell.RED_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 8.2
	@Test
	public void testAutomatedRedTurnMoveNonVacantCellinSimpleModePlayerS() {
		simpleBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		simpleBoard.makeMove(simpleBoard.getRow(), simpleBoard.getCol(), size, 'S', 'O', 'R', 'X', false);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	@Test
	public void testAutomatedRedTurnMoveNonVacantCellinSimpleModePlayerO() {
		simpleBoard.makeFirstMove(size, 'O', 'S', 'R', 'X', false);
		simpleBoard.makeMove(simpleBoard.getRow(), simpleBoard.getCol(), size, 'S', 'O', 'R', 'X', false);
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 8.3
	@Test
	public void testAutomatedRedTurnInvalidSizeMoveinSimpleModePlayerS() {
		simpleBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		simpleBoard.makeMove(simpleBoard.getRow(), simpleBoard.getCol(), size + 1, 'S', 'O', 'R', 'X', false);
		assertEquals("", simpleBoard.getTurn(), 'B');
	} 
	
	@Test
	public void testAutomatedRedTurnInvalidSizeMoveinSimpleModePlayerO() {
		simpleBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		simpleBoard.makeMove(simpleBoard.getRow(), simpleBoard.getCol(), size + 1, 'S', 'O', 'R', 'X', false);
		assertEquals("", simpleBoard.getTurn(), 'B');
	} 
	
	// acceptance criterion 9.1
	@Test
	public void testAutomatedRedTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		assertEquals("", generalBoard.getCell(generalBoard.getRow(), generalBoard.getCol(), size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	@Test
	public void testAutomatedRedTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoard.makeFirstMove(size, 'O', 'S', 'R', 'X', false);
		assertEquals("", generalBoard.getCell(generalBoard.getRow(), generalBoard.getCol(), size), Cell.RED_PLAYER);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 9.2
	@Test
	public void testAutomatedRedTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		generalBoard.makeMove(generalBoard.getRow(), generalBoard.getCol(), size, 'S', 'O', 'R', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	@Test
	public void testAutomatedRedTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoard.makeFirstMove(size, 'O', 'S', 'R', 'X', false);
		generalBoard.makeMove(1, 1, size,  'O', 'S', 'R', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'B');
		generalBoard.testingAutomatedMove(1, 1, size,  'O', 'S', 'R', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 9.3
	@Test
	public void testAutomatedRedTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoard.makeFirstMove(size, 'S', 'O', 'R', 'X', false);
		generalBoard.makeMove(generalBoard.getRow(), generalBoard.getCol(), size + 1, 'S', 'O', 'R', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'B');
	} 
	
	@Test
	public void testAutomatedRedTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoard.makeFirstMove(size, 'O', 'S', 'R', 'X', false);
		generalBoard.makeMove(generalBoard.getRow(), generalBoard.getCol(), size + 1, 'S', 'O', 'R', 'X', false);
		assertEquals("", generalBoard.getTurn(), 'B');
	} 
	
	// acceptance criterion 9.4
	@Test
	public void scoredPointAsAutomatedPlayerS() {
		generalBoard.makeFirstMove(size, 'S', 'O', 'R', 'B', false);
		generalBoard.makeMove(generalBoard.getRow(), generalBoard.getCol(), size , 'S', 'O', 'R', 'B', false);
		assertEquals("", generalBoard.getGameState(), GameState.RED_WINS); 
	} 
	
}
