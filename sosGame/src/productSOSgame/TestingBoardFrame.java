package productSOSgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TestingBoardFrame extends JFrame {

	private JPanel contentPane = new JPanel();
	private Board board;
	private GeneralGameBoard generalGame;
	private SimpleGameBoard simpleGame;
	int size = 3;



	/**
	 * Create the frame.
	 */
	public TestingBoardFrame(Board board, GeneralGameBoard generalGame, SimpleGameBoard simpleGame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.board = board;;
		this.generalGame = generalGame;
		this.simpleGame = simpleGame;
		setContentPane();

		board.setSizeBoard(size);
		System.out.println(size);
		
		setTitle("SOS Game");
		setContentPane(contentPane);
		pack(); 
		setVisible(true);  
	}
	
	private void setContentPane(){
		contentPane.setLayout(new BorderLayout());
		setGamePanel();
	}
	
	private void setGamePanel() {
		int CANVAS_WIDTH = 600;  
		int CANVAS_HEIGHT = 600;
		int CELL_SIZE = CANVAS_WIDTH / size;
		board.setSize(size);
		GameBoardCanvas gameBoardCanvas = new GameBoardCanvas(board, generalGame, simpleGame);
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		pack(); 
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestingBoardFrame frame = new TestingBoardFrame(new Board(), new GeneralGameBoard(), new SimpleGameBoard());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
