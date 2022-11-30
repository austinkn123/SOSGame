package testSOSgame;

import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.GUI;

public class TestBoardGUI { 
	public static JPanel contentPane = new JPanel();
	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 8;
	

	@Before
	public void setUp() throws Exception {
		//Can change size of board manually
		board = new Board(); 
		generalBoard =  new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSizeBoard(size); 
		board.setRedPlayerKey('S');
		board.setBluePlayerKey('O');
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		new GUI(board);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyGeneralBoard() {
		generalBoard.setSizeBoard(size);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		
		
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptySimpleBoard() {
		simpleBoard.setSizeBoard(size);
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoardGeneral() {
		board.setModeString("GENERAL");
		generalBoard.setSizeBoard(size);
		System.out.println("GENERAL BOARD");
		generalBoard.makeMove(0, 0, size, 'S', 'O', 'X', 'X', false); //RED is S
		generalBoard.makeMove(1, 1, size, 'S', 'O', 'X', 'X', false); //BLUE is O
		generalBoard.makeMove(1, 4, size, 'S', 'O', 'X', 'X', false); //RED is S
		System.out.println("S makes Move");
		System.out.println(generalBoard.getCell(0, 0, size));
		System.out.println("O makes Move");
		System.out.println(generalBoard.getCell(1, 1, size));
		System.out.println("S makes Move");
		System.out.println(generalBoard.getCell(1, 4, size));
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	
	@Test
	public void testNonEmptyBoardSimple() {
		board.setModeString("SIMPLE");
		simpleBoard.setSizeBoard(size);
		board.setRedPlayerKey('O');
		board.setBluePlayerKey('S');
		simpleBoard.makeMove(0, 0, size, 'O', 'S', 'X', 'X', false); //RED is O
		simpleBoard.makeMove(1, 1, size, 'O', 'S', 'X', 'X', false); //BLUE is S
		simpleBoard.makeMove(1, 4, size, 'O', 'S', 'X', 'X', false); //RED is O
		System.out.println("S makes Move");
		System.out.println(simpleBoard.getCell(0, 0, size));
		System.out.println("O makes Move");
		System.out.println(simpleBoard.getCell(1, 1, size));
		System.out.println("S makes Move");
		System.out.println(simpleBoard.getCell(1, 4, size));
		GUI gui = new GUI(board);
		gui.setGamePanel(board, generalBoard, simpleBoard, size);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}