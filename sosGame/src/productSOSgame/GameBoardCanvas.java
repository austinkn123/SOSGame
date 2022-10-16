package productSOSgame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import productSOSgame.GUIOneFile;
import productSOSgame.Board.Cell;
import productSOSgame.GeneralGameBoard.GameState;

public class GameBoardCanvas extends JPanel {
	public static int CELL_SIZE = 100; 
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
//	public static final int CELL_PADDING = CELL_SIZE / 6;
//	public static  int SYMBOL_SIZE = (CANVAS_WIDTH / size)  - CELL_PADDING * 2; 
	public static final int SYMBOL_STROKE_WIDTH = 8; 

	protected int CANVAS_WIDTH;
	protected int CANVAS_HEIGHT;
	


	protected Board board;
	GeneralGameBoard generalGame = new GeneralGameBoard();
	SimpleGameBoard simpleGame = new SimpleGameBoard();
	
	private JPanel redPlayerPanel = new JPanel();
	private JPanel bluePlayerPanel = new JPanel();
	
	protected JLabel redPlayerLabel = new JLabel("  Red Player  ");
	protected JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
	protected JLabel redPlayerPoints;
	protected JLabel bluePlayerPoints;
	
	
	//PROBLEM WITH SIZE
	int size = 8;
	String modeString = "GENERAL";
	char playerKeyRed = 'S';
	
	public void setMode(String modeString) {
		this.modeString = modeString;
	}
	
	public void setGameSize(int size) {
		this.size = size;
	}
	
	public void setPlayerKeyRed(char playerKeyRed) {
		this.playerKeyRed = playerKeyRed;
	}
	
	
	public GameBoardCanvas(Board board, GeneralGameBoard generalGame, SimpleGameBoard simpleGame){
		this.board = board;
		this.generalGame = generalGame;
		this.simpleGame = simpleGame;
		
		size= board.getSize();
		modeString = board.getModeString();
		playerKeyRed = board.getRedPlayerKey();
		
		System.out.println(size);
		System.out.println(modeString);
		
		GUI.contentPane.add(redPlayerPanel, BorderLayout.WEST);
		GUI.contentPane.add(bluePlayerPanel, BorderLayout.EAST);
		bluePlayerPanel.add(bluePlayerLabel);
		redPlayerPanel.add(redPlayerLabel);
		
		CANVAS_WIDTH = 600;  
		CANVAS_HEIGHT = 600;
		CELL_SIZE = CANVAS_WIDTH / size;
		
		if(modeString == "GENERAL") {
			redPlayerPoints = new JLabel(String.valueOf(generalGame.getPointRed()));
			bluePlayerPoints = new JLabel(String.valueOf(generalGame.getPointBlue()));
			redPlayerPoints.setFont(new Font("Tahoma", Font.PLAIN, 30));
			redPlayerPoints.setForeground(new Color(255, 0, 0));
			bluePlayerPoints.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			redPlayerPanel.add(redPlayerPoints, BorderLayout.CENTER);
			bluePlayerPanel.add(bluePlayerPoints, BorderLayout.CENTER);
		}
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {  
					int rowSelected = e.getY() / CELL_SIZE;
					int colSelected = e.getX() / CELL_SIZE;
					if(modeString == "GENERAL") {
						generalGame.makeMoveInGeneralMode(rowSelected, colSelected, size, playerKeyRed);
					}
					else if (modeString == "SIMPLE") {
						simpleGame.makeMove(rowSelected, colSelected, size);
					}
				repaint(); 
			}
		});

	}
	

	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);   
		setBackground(Color.WHITE);
		drawGridLines(g);
		drawBoard(g);
		playerStatus();
	}
	
	private void drawGridLines(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		for (int row = 1; row < size; row++) {
			g.fillRoundRect(0, CELL_SIZE  * row , CANVAS_WIDTH, GRID_WIDTH / 3, GRID_WIDTH, GRID_WIDTH);
		}
		for (int col = 1; col < size; col++) {
			g.fillRoundRect(CELL_SIZE * col , 0, GRID_WIDTH / 3, CANVAS_HEIGHT, GRID_WIDTH, GRID_WIDTH);
		}

	}
	
	private void drawBoard(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
//		Image i = new ImageIcon("redS").getImage(); 
		g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
		SYMBOL_SIZE = (CANVAS_WIDTH / size)  - CELL_PADDING * 2;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				int x1 = col * CELL_SIZE + CELL_PADDING ;
				int y1 = row * CELL_SIZE + CELL_PADDING ;
				if(board.setMode(modeString, size) == 1) {
					if (generalGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
						if(playerKeyRed == 'S') {
							g2d.setColor(Color.RED);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
						}
						else{
							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
						}
						
						
					} else if (generalGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player
						if(playerKeyRed == 'O') {
							g2d.setColor(Color.BLUE);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
						}
						else {
							g2d.setColor(Color.BLUE);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
						}

					}
				}
				else if(board.setMode(modeString, size) == 2) {
					if (simpleGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
						if(playerKeyRed == 'S') {
							g2d.setColor(Color.RED);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
						}
						else{
							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
						}

					} else if (simpleGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player

						if(playerKeyRed == 'O') {
							g2d.setColor(Color.BLUE);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
						}
						else {
							g2d.setColor(Color.BLUE);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
						}
						

					}
				}
				
			}
		}
	}
	
	private void playerStatus(){
//		contentPane
			if(board.setMode(modeString, size) == 1) {
				if (generalGame.getTurn() == 'R') {
					redPlayerLabel.setForeground(new Color(255, 0, 0));
					bluePlayerLabel.setForeground(new Color(0, 0, 0));
					redPlayerPoints.setForeground(new Color(255, 0, 0));
					bluePlayerPoints.setForeground(new Color(0, 0, 0));
				} 
				else{
					redPlayerLabel.setForeground(new Color(0, 0, 0));
					bluePlayerLabel.setForeground(new Color(0, 0, 255));
					redPlayerPoints.setForeground(new Color(0, 0, 0));
					bluePlayerPoints.setForeground(new Color(0, 0, 255));
				}
				if(generalGame.getGameState() == GameState.RED_SCORES ) {
					generalGame.addPointRed();
					System.out.println(generalGame.getPointRed());
					redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
					System.out.println(generalGame.getTurn());
					generalGame.setGameState(GameState.PLAYING);
				}
				
				if(generalGame.getGameState() == GameState.BLUE_SCORES) {
					generalGame.addPointBlue();
					System.out.println(generalGame.getPointBlue());
					bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
					generalGame.setGameState(GameState.PLAYING);
				}
			}
			else if (board.setMode(modeString, size) == 2){
				if (simpleGame.getTurn() == 'R') {
					redPlayerLabel.setForeground(new Color(255, 0, 0));
					bluePlayerLabel.setForeground(new Color(0, 0, 0));

				} else {
					redPlayerLabel.setForeground(new Color(0, 0, 0));
					bluePlayerLabel.setForeground(new Color(0, 0, 255));

				}
			}
	}

}
