package testSOSgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GUI;

public class TestBoardGUI {
	private Board board;
	private int size = 8;

	@Before
	public void setUp() throws Exception {
		//Can change size of board manually
		board = new Board(); 
		board.setSize(size);
		
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
			Thread.sleep(5000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}