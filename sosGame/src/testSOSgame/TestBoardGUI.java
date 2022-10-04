package testSOSgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productSOSgame.Board;
import productSOSgame.GUI;

public class TestBoardGUI {
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(6);
		
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
			Thread.sleep(15000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}