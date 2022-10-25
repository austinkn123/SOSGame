package testSOSgame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
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
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("SIMPLE");
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
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'O', 'S'); //RED is O
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'O', 'S'); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'O', 'S'); //RED is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'O', 'S'); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'O', 'S'); //RED is O
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
	public void simpleGameBlueSWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'O', 'S'); //RED is O
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'O', 'S'); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'O', 'S'); //RED is O
		simpleBoard.makeMoveInSimpleMode(1, 2, size, 'O', 'S'); //BLUE is S
		assertEquals("", simpleBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//RED = S and BLUE = O
	@Test
	public void simpleGameBlueOWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'S', 'O'); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O'); //BLUE is O
		assertEquals("", simpleBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//RED = S and BLUE = O
	@Test
	public void simpleGameDrawGame() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O'); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(2, 0, size, 'S', 'O'); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(2, 2, size, 'S', 'O'); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(1, 2, size, 'S', 'O'); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O'); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O'); //RED is S
		assertEquals("", simpleBoard.getGameState(), GameState.DRAW);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//RED = S and BLUE = O
	@Test
	public void generalGameRedSWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O'); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.RED_WINS);
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
	public void generalGameRedOWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'O', 'S'); //RED is O
		assertEquals("", generalBoard.getGameState(), GameState.RED_WINS);
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
	public void generalGameBlueSWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'O', 'S'); //RED is O
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'O', 'S'); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'O', 'S'); //RED is O
		assertEquals("", generalBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//RED = S and BLUE = O
	@Test
	public void generalGameBlueOWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O'); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//RED = S and BLUE = O
	@Test
	public void generalGameDrawGame() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O'); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O'); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O'); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.DRAW);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}