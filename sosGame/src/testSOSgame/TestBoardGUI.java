package testSOSgame;

import java.awt.Robot;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GUI;
import productSOSgame.GeneralGameBoard;
import productSOSgame.SimpleGameBoard;

public class TestBoardGUI { 
	private Board board;
	private GeneralGameBoard generalBoard;
	private SimpleGameBoard simpleBoard;
	private int size = 8;
	private JPanel emptyPanel = new JPanel();
	

	@Before
	public void setUp() throws Exception {
		//Can change size of board manually
		board = new Board(); 
		generalBoard =  new GeneralGameBoard();
		simpleBoard = new SimpleGameBoard();
		board.setSize(size);
		generalBoard.setSize(size);
		simpleBoard.setSize(size);
		
//		GUI.frame.setVisible(true);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyBoard() {
		//Click on the jtextfield area to type in the input
		new GUI(board);
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoardGeneral() {
		new GUI(board);
		
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
		 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoardSimple() {
		new GUI(board);
		
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
		 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}