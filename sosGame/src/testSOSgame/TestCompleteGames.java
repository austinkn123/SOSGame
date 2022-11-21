package testSOSgame;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	private String st;
	private String wholeFileString = "";

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
	
	//AC 5.1 A win by red the red player with the S symbol
	//RED = S and BLUE = O
	@Test
	public void simpleGameRedSWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", simpleBoard.getGameState(), GameState.RED_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 5.2 A win by red player with the O symbol
	//RED = O and BLUE = S
	@Test
	public void simpleGameRedOWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		assertEquals("", simpleBoard.getGameState(), GameState.RED_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 5.3 A win by blue player with the S symbol
	//RED = O and BLUE = S
	@Test
	public void simpleGameBlueSWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		simpleBoard.makeMoveInSimpleMode(1, 2, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		assertEquals("", simpleBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 5.4 A win by blue player with the O symbol
	//RED = S and BLUE = O
	@Test
	public void simpleGameBlueOWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		assertEquals("", simpleBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 5.5 A draw game
	//RED = S and BLUE = O
	@Test
	public void simpleGameDrawGame() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(2, 0, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(2, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(1, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", simpleBoard.getGameState(), GameState.DRAW);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 7.1 A win by red the red player with the S symbol
	//RED = S and BLUE = O
	@Test
	public void generalGameRedSWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.RED_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 7.2 A win by red player with the O symbol
	//RED = O and BLUE = S
	@Test
	public void generalGameRedOWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		assertEquals("", generalBoard.getGameState(), GameState.RED_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 7.3 A win by blue player with the S symbol
	//RED = O and BLUE = S
	@Test
	public void generalGameBlueSWin() {
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'O', 'S', 'X', 'X', false); //RED is O
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'O', 'S', 'X', 'X', false); //RED is O
		assertEquals("", generalBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 7.4 A win by blue player with the O symbol
	//RED = S and BLUE = O
	@Test
	public void generalGameBlueOWin() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.BLUE_WINS);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//AC 7.5 A draw game
	//RED = S and BLUE = O
	@Test
	public void generalGameDrawGame() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'X', false); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.DRAW);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecordSimpleGameRedWins(){
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		simpleBoard.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O', 'X', 'X', true); //RED is S
		assertEquals("", simpleBoard.getGameState(), GameState.RED_WINS);
		try (BufferedReader br = new BufferedReader(new FileReader(simpleBoard.recordFile))) {
//			reads the file and each line is set to st till the end of the file
			while ((st = br.readLine()) != null) {
				wholeFileString += st + "\n";
			}
			assertEquals("", wholeFileString, "SIMPLE mode:\n Red is Human as the S symbol \n"
					+ "Blue is Human as the O symbol \n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 1\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 2\n"
					+ "RED_WINS\n");
			System.out.println(wholeFileString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecordSimpleGameBlueWins(){
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		simpleBoard.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O', 'X', 'X', true); //RED is S
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		assertEquals("", simpleBoard.getGameState(), GameState.BLUE_WINS);
		try (BufferedReader br = new BufferedReader(new FileReader(simpleBoard.recordFile))) {
//			reads the file and each line is set to st till the end of the file
			while ((st = br.readLine()) != null) {
				wholeFileString += st + "\n";
			}
			assertEquals("", wholeFileString, "SIMPLE mode:\n Red is Human as the S symbol \n"
					+ "Blue is Human as the O symbol \n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 1 Column - 0\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 2\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 1\n"
					+ "BLUE_WINS\n");
			System.out.println(wholeFileString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecordSimpleGameDraw(){
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		simpleBoard.setModeString("SIMPLE");
		simpleBoard.makeMoveInSimpleMode(1, 1, size, 'S', 'O', 'X', 'X', true); //RED is S/
		simpleBoard.makeMoveInSimpleMode(0, 0, size, 'S', 'O', 'X', 'X', true); //BLUE is O/
		simpleBoard.makeMoveInSimpleMode(1, 0, size, 'S', 'O', 'X', 'X', true); //RED is S/
		simpleBoard.makeMoveInSimpleMode(2, 0, size, 'S', 'O', 'X', 'X', true); //BLUE is O/
		simpleBoard.makeMoveInSimpleMode(2, 1, size, 'S', 'O', 'X', 'X', true); //RED is S/
		simpleBoard.makeMoveInSimpleMode(2, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O/
		simpleBoard.makeMoveInSimpleMode(1, 2, size, 'S', 'O', 'X', 'X', true); //RED is S/
		simpleBoard.makeMoveInSimpleMode(0, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		simpleBoard.makeMoveInSimpleMode(0, 1, size, 'S', 'O', 'X', 'X', true); //RED is S
		assertEquals("", simpleBoard.getGameState(), GameState.DRAW);
		try (BufferedReader br = new BufferedReader(new FileReader(simpleBoard.recordFile))) {
//			reads the file and each line is set to st till the end of the file
			while ((st = br.readLine()) != null) {
				wholeFileString += st + "\n";
			}
			assertEquals("", wholeFileString, "SIMPLE mode:\n Red is Human as the S symbol \n"
					+ "Blue is Human as the O symbol \n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 0\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 0\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 2 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 2\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 1\n"
					+ "DRAW\n");
			System.out.println(wholeFileString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecordGeneralGameRedWins() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O', 'X', 'X', true); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.RED_WINS);
		try (BufferedReader br = new BufferedReader(new FileReader(generalBoard.recordFile))) {
//			reads the file and each line is set to st till the end of the file
			while ((st = br.readLine()) != null) {
				wholeFileString += st + "\n";
			}
			assertEquals("", wholeFileString, "GENERAL mode:\n"
					+ "Red is Human as the S symbol \n"
					+ "Blue is Human as the O symbol \n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 1\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 1\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 2 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 1 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 2\n\n"
					+ "RED_WINS\n"
					+ "Red Points: 1\n"
					+ "Blue Points: 0\n");
			System.out.println(wholeFileString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecordGeneralGameBlueWins() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O', 'X', 'X', true); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.BLUE_WINS);
		try (BufferedReader br = new BufferedReader(new FileReader(generalBoard.recordFile))) {
//			reads the file and each line is set to st till the end of the file
			while ((st = br.readLine()) != null) {
				wholeFileString += st + "\n";
			}
			assertEquals("", wholeFileString, "GENERAL mode:\n"
					+ "Red is Human as the S symbol \n"
					+ "Blue is Human as the O symbol \n"
					+ "Red's Turn: \n"
					+ " Move: Row - 2 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 0\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 1\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 2 Column - 2\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 1 Column - 2\n\n"
					+ "BLUE_WINS\n"
					+ "Red Points: 0\n"
					+ "Blue Points: 1\n");
			System.out.println(wholeFileString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecordGeneralGameDraw() {
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
		board.setModeString("GENERAL");
		generalBoard.setModeString("GENERAL");
		generalBoard.makeMoveInGeneralMode(1, 1, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 0, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 0, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 0, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(2, 1, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(2, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(1, 2, size, 'S', 'O', 'X', 'X', true); //RED is S
		generalBoard.makeMoveInGeneralMode(0, 2, size, 'S', 'O', 'X', 'X', true); //BLUE is O
		generalBoard.makeMoveInGeneralMode(0, 1, size, 'S', 'O', 'X', 'X', true); //RED is S
		assertEquals("", generalBoard.getGameState(), GameState.DRAW);
		try (BufferedReader br = new BufferedReader(new FileReader(generalBoard.recordFile))) {
//			reads the file and each line is set to st till the end of the file
			while ((st = br.readLine()) != null) {
				wholeFileString += st + "\n";
			}
			assertEquals("", wholeFileString, "GENERAL mode:\n"
					+ "Red is Human as the S symbol \n"
					+ "Blue is Human as the O symbol \n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 0\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 0\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 0\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 2 Column - 1\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 2 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 1 Column - 2\n"
					+ "Blue's Turn: \n"
					+ " Move: Row - 0 Column - 2\n"
					+ "Red's Turn: \n"
					+ " Move: Row - 0 Column - 1\n\n"
					+ "DRAW\n"
					+ "Red Points: 0\n"
					+ "Blue Points: 0\n");
			System.out.println(wholeFileString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
