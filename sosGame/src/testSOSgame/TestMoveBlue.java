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


public class TestMoveBlue {

	private Board board;
	private GeneralGameBoard generalBoardPlayerS;
	private GeneralGameBoard generalBoardPlayerO;
	private SimpleGameBoard simpleBoard;
	private int size = 5;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		generalBoardPlayerS = new GeneralGameBoard();
		generalBoardPlayerO = new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSizeBoard(size);
		generalBoardPlayerS.setSizeGeneral(size);
		generalBoardPlayerO.setSizeGeneral(size);
		simpleBoard.setSizeBoard(size);
		simpleBoard.makeMove(1, 1, size);
		generalBoardPlayerO.makeMoveInGeneralMode(1, 1, size, 'S', 'O'); //RED is S
		generalBoardPlayerS.makeMoveInGeneralMode(1, 1, size, 'O', 'S'); // RED IS O
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 4.4
	@Test
	public void testBlueTurnMoveVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size);
		assertEquals("", simpleBoard.getCell(0, 0, size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoard.getTurn(), 'R');
	}
	
	// acceptance criterion 4.5
	@Test
	public void testBlueTurnMoveNonVacantCellinSimpleMode() {
		simpleBoard.makeMove(0, 0, size); // O move
		simpleBoard.makeMove(1, 0, size); // S move
		assertEquals("", simpleBoard.getTurn(), 'B');
		simpleBoard.makeMove(1, 0, size); // invalid O move
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 4.6
	@Test
	public void testBlueTurnInvalidSizeMoveinSimpleMode() {
		simpleBoard.makeMove((size + 1), 0, size); 
		assertEquals("", simpleBoard.getTurn(), 'B');
	}
	
	// acceptance criterion 6.5
	@Test
	public void testBlueTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(0, 1, size, 'O', 'S'); // BLUE IS S
		assertEquals("", generalBoardPlayerS.getCell(0, 1, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testBlueTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(0, 1, size, 'S', 'O'); // BLUE IS O
		assertEquals("", generalBoardPlayerO.getCell(0, 1, size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 6.6
	@Test
	public void testBlueTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(1, 1, size, 'O', 'S'); // BLUE IS S and is invalid move
		assertEquals("", generalBoardPlayerS.getTurn(), 'B');
	}
	
	@Test
	public void testBlueTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(1, 1, size, 'S', 'O'); // BLUE IS O and is invalid move
		assertEquals("", generalBoardPlayerO.getTurn(), 'B');
	}
	
	// acceptance criterion 6.7
	@Test
	public void testBlueTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(size + 1, 0, size, 'O', 'S'); // BLUE IS S
		assertEquals("", generalBoardPlayerS.getTurn(), 'B');
	}
	
	@Test
	public void testBlueTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(size + 1, 0, size, 'S', 'O'); // BLUE IS O
		assertEquals("", generalBoardPlayerO.getTurn(), 'B');
	}
	
	// acceptance criterion 6.8 
	//S Player
	@Test
	public void scoredPointasPlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(0, 0, size, 'O', 'S'); // BLUE IS S and moves
		generalBoardPlayerS.makeMoveInGeneralMode(0, 1, size, 'O', 'S'); // RED IS O and moves
		generalBoardPlayerS.makeMoveInGeneralMode(0, 2, size, 'O', 'S'); // BLUE IS S and moves
		assertEquals("", generalBoardPlayerS.getGameScore(), GameStateGeneral.BLUE_SCORES); 
		assertEquals("", generalBoardPlayerS.getPointBlue(), 1); 
		assertEquals("", generalBoardPlayerS.getTurn(), 'B'); 
	}
	
	//O Player
	@Test
	public void scoredPointasPlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(0, 1, size, 'S', 'O'); // BLUE IS O and moves
		generalBoardPlayerO.makeMoveInGeneralMode(1, 3, size, 'S', 'O'); // RED IS S and moves
		generalBoardPlayerO.makeMoveInGeneralMode(1, 2, size, 'S', 'O'); // BLUE IS O and moves
		assertEquals("", generalBoardPlayerO.getGameScore(), GameStateGeneral.BLUE_SCORES); 
		assertEquals("", generalBoardPlayerO.getPointBlue(), 1); 
		assertEquals("", generalBoardPlayerO.getTurn(), 'B'); 
	}
	
}
