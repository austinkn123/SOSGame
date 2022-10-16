package testSOSgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GUIOneFile;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;
import productSOSgame.TestingBoardFrame;
import productSOSgame.GUI;
import productSOSgame.GameBoardCanvas;

public class TestBoardGUI { 
	private JFrame frame = new JFrame();
	public static JPanel contentPane = new JPanel();
	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private GameBoardCanvas gameBoardCanvas;  
	private int size = 8;
	

	@Before
	public void setUp() throws Exception {
		//Can change size of board manually
		board = new Board(); 
		generalBoard =  new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSizeBoard(size); 
		generalBoard.setSizeGeneral(size);
		simpleBoard.setSizeBoard(size);
		gameBoardCanvas = new GameBoardCanvas(board, generalBoard, simpleBoard);
		gameBoardCanvas.setMode("GENERAL");
		gameBoardCanvas.setGameSize(size);
		gameBoardCanvas.setPlayerKeyRed('S');
		gameBoardCanvas.setPreferredSize(new Dimension(600, 600));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyBoard() {
		//Click on the jtextfield area to type in the input
//		new GameBoardCanvas(board, generalBoard, simpleBoard);
		new TestingBoardFrame(board,generalBoard, simpleBoard);
		
		
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoardGeneral() {
		System.out.println("GENERAL BOARD");
		generalBoard.makeMove(0, 0, size);
		generalBoard.makeMove(1, 1, size);	
		generalBoard.makeMove(1, 4, size);	
		System.out.println("S makes Move");
		System.out.println(generalBoard.getCell(0, 0, size));
		System.out.println("O makes Move");
		System.out.println(generalBoard.getCell(1, 1, size));
		System.out.println("S makes Move");
		System.out.println(generalBoard.getCell(1, 4, size));
		new TestingBoardFrame(board,generalBoard, simpleBoard);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	
	@Test
	public void testNonEmptyBoardSimple() {
		System.out.println("SIMPLE BOARD");
		simpleBoard.makeMove(0, 0, size);
		simpleBoard.makeMove(1, 1, size);	
		simpleBoard.makeMove(1, 4, size);	
		System.out.println("S makes Move");
		System.out.println(simpleBoard.getCell(0, 0, size));
		System.out.println("O makes Move");
		System.out.println(simpleBoard.getCell(1, 1, size));
		System.out.println("S makes Move");
		System.out.println(generalBoard.getCell(1, 4, size));
		new GUI(board);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}