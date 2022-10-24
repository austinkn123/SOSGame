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
import productSOSgame.GUI;

public class TestCompleteGames {

	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 3;

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
	
	//RED = S and BLUE = O
	@Test
	public void simpleGameRedSWin() {
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O'); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O'); //RED is S
		assertEquals("", simpleBoard.getGameState(), GameState.RED_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//RED = O and BLUE = S
	@Test
	public void simpleGameRedOWin() {
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'O', 'S'); //RED is O
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'O', 'S'); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'O', 'S'); //RED is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'O', 'S'); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'O', 'S'); //RED is O
		assertEquals("", simpleBoard.getGameState(), GameState.RED_WINS);
	}

}
