package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.Board.Cell;
import productSOSgame.Board.GameState;

public class TestMoveBlueAutomated {
	
	private Board board;
	private GeneralGameBoard generalBoardPlayerS;
	private GeneralGameBoard generalBoardPlayerO;
	private GeneralGameBoard generalBoardPlayerScored;
	private SimpleGameBoard simpleBoardPlayerS;
	private SimpleGameBoard simpleBoardPlayerO;
	private int size = 5;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		generalBoardPlayerS = new GeneralGameBoard();
		generalBoardPlayerO = new GeneralGameBoard();
		generalBoardPlayerScored = new GeneralGameBoard();
		
		simpleBoardPlayerS = new SimpleGameBoard();
		simpleBoardPlayerO = new SimpleGameBoard();
		
		board.setSizeBoard(size);
		generalBoardPlayerS.setSizeBoard(size);
		generalBoardPlayerO.setSizeBoard(size);
		generalBoardPlayerScored.setSizeBoard(size);
		
		simpleBoardPlayerO.setSizeBoard(size);
		simpleBoardPlayerS.setSizeBoard(size);
		
		simpleBoardPlayerS.makeMoveInSimpleMode(1, 1, size, 'S', 'O', 'X', 'B'); //RED is S
		simpleBoardPlayerO.makeMoveInSimpleMode(1, 1, size, 'O', 'S', 'X', 'B'); //RED is O
		
		generalBoardPlayerO.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'B'); //RED is O
		generalBoardPlayerS.makeMoveInGeneralMode(1, 1, size, 'O', 'S', 'X', 'B'); // RED IS S
		generalBoardPlayerScored.makeMoveInGeneralMode(1, 1, size, 'O', 'S', 'X', 'B'); // RED IS S
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 8.4
	@Test
	public void testAutomatedBlueTurnMoveVacantCellinSimpleModePlayerS() {
		simpleBoardPlayerS.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'B'); //BLUE is O
		assertEquals("", simpleBoardPlayerS.getCell(simpleBoardPlayerS.getRow(), simpleBoardPlayerS.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveVacantCellinSimpleModePlayerO() {
		simpleBoardPlayerO.makeMoveInSimpleMode(0, 0, size, 'O', 'S', 'X', 'B'); //BLUE is O
		assertEquals("", simpleBoardPlayerO.getCell(simpleBoardPlayerO.getRow(), simpleBoardPlayerO.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 8.5
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinSimpleModePlayerS() {
		simpleBoardPlayerS.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'B'); //BLUE is O
		simpleBoardPlayerS.makeMoveInSimpleMode(simpleBoardPlayerO.getRow(), simpleBoardPlayerO.getCol(), size, 'S', 'O', 'X', 'B'); //RED is S
		assertEquals("", simpleBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinSimpleModePlayerO() {
		simpleBoardPlayerO.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'B'); //BLUE is O
		simpleBoardPlayerO.makeMoveInSimpleMode(simpleBoardPlayerO.getRow(), simpleBoardPlayerO.getCol(), size, 'O', 'S', 'X', 'B'); //RED is S
		assertEquals("", simpleBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 8.6
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinSimpleModePlayerS() {
		simpleBoardPlayerS.makeMoveInSimpleMode(0, size + 1, size, 'S', 'O', 'X', 'B'); //BLUE is O
		assertEquals("", simpleBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinSimpleModePlayerO() {
		simpleBoardPlayerO.makeMoveInSimpleMode(0, size + 1, size, 'S', 'O', 'X', 'B'); //BLUE is O
		assertEquals("", simpleBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.5
	public void testAutomatedBlueTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(0, 1, size, 'O', 'S', 'X', 'B'); // BLUE IS S
		assertEquals("", generalBoardPlayerS.getCell(generalBoardPlayerS.getRow(), generalBoardPlayerS.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'B'); // BLUE IS O
		assertEquals("", generalBoardPlayerO.getCell(generalBoardPlayerO.getRow(), generalBoardPlayerO.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.6
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'B'); //BLUE is O
		generalBoardPlayerS.makeMoveInGeneralMode(generalBoardPlayerS.getRow(), generalBoardPlayerS.getCol(), size, 'S', 'O', 'X', 'B'); //RED is S
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'B'); //BLUE is O
		generalBoardPlayerO.makeMoveInGeneralMode(generalBoardPlayerO.getRow(), generalBoardPlayerO.getCol(), size, 'O', 'S', 'X', 'B'); //RED is S
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.7
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoardPlayerS.makeMoveInGeneralMode(0, size + 1, size, 'S', 'O', 'X', 'B'); //BLUE is O
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoardPlayerO.makeMoveInGeneralMode(0, size + 1, size, 'S', 'O', 'X', 'B'); //BLUE is O
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.8
	@Test
	public void scoredPoint() {
		generalBoardPlayerScored.makeFirstMove(size, 'S', 'O', 'R', 'B');
		generalBoardPlayerScored.makeMoveInGeneralMode(generalBoardPlayerScored.getRow(), generalBoardPlayerScored.getCol(), size , 'S', 'O', 'R', 'B');
		assertEquals("", generalBoardPlayerScored.getGameState(), GameState.BLUE_WINS); 
	} 
	
}
