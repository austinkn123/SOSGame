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

public class TestMoveBlueComputer {
	
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
		
		simpleBoardPlayerS.makeMove(1, 1, size, 'S', 'O', 'X', 'B', false); //RED is S
		simpleBoardPlayerO.makeMove(1, 1, size, 'O', 'S', 'X', 'B', false); //RED is O
		
		generalBoardPlayerO.makeMove(1, 1, size, 'S', 'O', 'X', 'B', false); //RED is O
		generalBoardPlayerS.makeMove(1, 1, size, 'O', 'S', 'X', 'B', false); // RED IS S
		generalBoardPlayerScored.makeMove(1, 1, size, 'O', 'S', 'X', 'B', false); // RED IS S
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criterion 8.4
	@Test
	public void testAutomatedBlueTurnMoveVacantCellinSimpleModePlayerS() {
		simpleBoardPlayerS.makeMove(0, 0, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		assertEquals("", simpleBoardPlayerS.getCell(simpleBoardPlayerS.getRow(), simpleBoardPlayerS.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveVacantCellinSimpleModePlayerO() {
		simpleBoardPlayerO.makeMove(0, 0, size, 'O', 'S', 'X', 'B', false); //BLUE is O
		assertEquals("", simpleBoardPlayerO.getCell(simpleBoardPlayerO.getRow(), simpleBoardPlayerO.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", simpleBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 8.5
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinSimpleModePlayerS() {
		simpleBoardPlayerS.makeMove(0, 0, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		simpleBoardPlayerS.makeMove(simpleBoardPlayerO.getRow(), simpleBoardPlayerO.getCol(), size, 'S', 'O', 'X', 'B', false); //RED is S
		assertEquals("", simpleBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinSimpleModePlayerO() {
		simpleBoardPlayerO.makeMove(0, 0, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		simpleBoardPlayerO.makeMove(simpleBoardPlayerO.getRow(), simpleBoardPlayerO.getCol(), size, 'O', 'S', 'X', 'B', false); //RED is S
		assertEquals("", simpleBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 8.6
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinSimpleModePlayerS() {
		simpleBoardPlayerS.makeMove(0, size + 1, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		assertEquals("", simpleBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinSimpleModePlayerO() {
		simpleBoardPlayerO.makeMove(0, size + 1, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		assertEquals("", simpleBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.5
	public void testAutomatedBlueTurnMoveVacantCellinGeneralModePlayerS() {
		generalBoardPlayerS.makeMove(0, 1, size, 'O', 'S', 'X', 'B', false); // BLUE IS S
		assertEquals("", generalBoardPlayerS.getCell(generalBoardPlayerS.getRow(), generalBoardPlayerS.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveVacantCellinGeneralModePlayerO() {
		generalBoardPlayerO.makeMove(0, 1, size, 'S', 'O', 'X', 'B', false); // BLUE IS O
		assertEquals("", generalBoardPlayerO.getCell(generalBoardPlayerO.getRow(), generalBoardPlayerO.getCol(), size), Cell.BLUE_PLAYER);
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.6
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinGeneralModePlayerS() {
		generalBoardPlayerS.makeMove(0, 0, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		generalBoardPlayerS.makeMove(generalBoardPlayerS.getRow(), generalBoardPlayerS.getCol(), size, 'S', 'O', 'X', 'B', false); //RED is S
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnMoveNonVacantCellinGeneralModePlayerO() {
		generalBoardPlayerO.makeMove(0, 0, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		generalBoardPlayerO.makeMove(generalBoardPlayerO.getRow(), generalBoardPlayerO.getCol(), size, 'O', 'S', 'X', 'B', false); //RED is S
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.7
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinGeneralModePlayerS() {
		generalBoardPlayerS.makeMove(0, size + 1, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		assertEquals("", generalBoardPlayerS.getTurn(), 'R');
	}
	
	@Test
	public void testAutomatedBlueTurnInvalidSizeMoveinGeneralModePlayerO() {
		generalBoardPlayerO.makeMove(0, size + 1, size, 'S', 'O', 'X', 'B', false); //BLUE is O
		assertEquals("", generalBoardPlayerO.getTurn(), 'R');
	}
	
	// acceptance criterion 9.8
	@Test
	public void scoredPoint() {
		generalBoardPlayerScored.makeFirstMove(size, 'S', 'O', 'R', 'B', false);
		generalBoardPlayerScored.makeMove(generalBoardPlayerScored.getRow(), generalBoardPlayerScored.getCol(), size , 'S', 'O', 'R', 'B', false);
		assertEquals("", generalBoardPlayerScored.getGameState(), GameState.BLUE_WINS); 
	} 
	
}
