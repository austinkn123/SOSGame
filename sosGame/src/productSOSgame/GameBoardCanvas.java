package productSOSgame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import productSOSgame.Board.Cell;
import productSOSgame.GeneralGameBoard.GameStateGeneral;
import productSOSgame.Board.GameState;
import productSOSgame.Board.scoredCell;;

@SuppressWarnings("serial")
public class GameBoardCanvas extends JPanel {
	public static int CELL_SIZE = 100; 
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
	public static final int SYMBOL_STROKE_WIDTH = 8; 

	protected int CANVAS_WIDTH;
	protected int CANVAS_HEIGHT;


	protected Board board;
	GeneralGameBoard generalGame = new GeneralGameBoard();
	SimpleGameBoard simpleGame = new SimpleGameBoard();
	
//	private JPanel redPlayerPanel = new JPanel();
//	private JPanel bluePlayerPanel = new JPanel();
//	
//	protected JLabel redPlayerLabel = new JLabel("  Red Player  ");
//	protected JLabel bluePlayerLabel = new JLabel("  Blue Player  ");
	protected JLabel redPlayerPoints;
	protected JLabel bluePlayerPoints;
	
	private  JPanel gameOptions = new JPanel();
	private JLabel gameStatusBar = new JLabel("Red Player, Make a Move!");;
	GridBagConstraints gbc = new GridBagConstraints();
	
	int size = 8;
	String modeString = "GENERAL";
	char playerKeyRed = 'S';
	char playerKeyBlue = 'S';
	
	
	//For test cases
	public void setMode(String modeString) {this.modeString = modeString;}
	
	public void setGameSize(int size) {this.size = size;}
	
	public void setPlayerKeyRed(char playerKeyRed) {this.playerKeyRed = playerKeyRed;}
	
	
	public GameBoardCanvas(Board board, GeneralGameBoard generalGame, SimpleGameBoard simpleGame, int pSize){
		this.board = board;
		this.generalGame = generalGame;
		this.simpleGame = simpleGame;
		
		size= board.getSize();
		size = pSize;
		modeString = board.getModeString();
		playerKeyRed = board.getRedPlayerKey();
		playerKeyBlue = board.getBluePlayerKey();
		
		
		gameOptions.setLayout(new GridBagLayout());
		GUI.contentPane.add(gameOptions, BorderLayout.SOUTH);
		
		gameStatusBar.setFont(new Font("Calibri", Font.BOLD, 25));
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gameOptions.add(gameStatusBar, gbc);
		
		//NEW GAME BUTTON
		JButton newGame = new JButton("  New Game  ");
		newGame.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newGame.setBounds(272, 14, 92, 39);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gameOptions.add(newGame, gbc); 
		
		//SCREEN BOARD SIZE
		CANVAS_WIDTH = 500;  
		CANVAS_HEIGHT = 500;
		CELL_SIZE = CANVAS_WIDTH / pSize;
		
		if(modeString == "GENERAL") {
			redPlayerPoints = new JLabel(String.valueOf(generalGame.getPointRed()));
			bluePlayerPoints = new JLabel(String.valueOf(generalGame.getPointBlue()));
			redPlayerPoints.setFont(new Font("Tahoma", Font.PLAIN, 30));
			redPlayerPoints.setForeground(new Color(255, 0, 0));
			bluePlayerPoints.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			GUI.redPlayerPanel.add(redPlayerPoints, BorderLayout.CENTER);
			GUI.bluePlayerPanel.add(bluePlayerPoints, BorderLayout.CENTER);
			redPlayerPoints.setForeground(Color.RED);
			bluePlayerPoints.setForeground(Color.BLUE);
		}
		
		
		
		
		
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					int rowSelected = e.getY() / CELL_SIZE;
					int colSelected = e.getX() / CELL_SIZE;
					makeMoveonBoard(generalGame, simpleGame, rowSelected, colSelected, pSize, playerKeyRed, playerKeyBlue, modeString);
			}
		});
 
	
	//ACTION FOR ENTER BUTTON
	newGame.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(modeString == "GENERAL") {
				generalGame.resetPointBlue();
				generalGame.resetPointRed();
				redPlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
				redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
				generalGame.setSizeBoard(pSize);
			}
			if(modeString == "SIMPLE") {
				simpleGame.setSizeBoard(pSize);
			}
			
		}
	});
	
	
	
	}
	
	public void makeMoveonBoard(GeneralGameBoard generalMode, SimpleGameBoard simpleMode, int row, int col, int pSize, 
			char redPlayer, char bluePlayer, String pModString) { 
		if(pModString == "GENERAL") {
			if(generalMode.getGameState() == GameState.PLAYING) {
				generalMode.makeMoveInGeneralMode(row, col, pSize, redPlayer, bluePlayer);
			}
		}
		if(pModString == "SIMPLE") {
			if (simpleMode.getGameState() == GameState.PLAYING) {
				simpleMode.makeMoveInSimpleMode(row, col, pSize, redPlayer, bluePlayer);
			}
		}
		repaint(); 
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);   
		setBackground(Color.WHITE);
		drawGridLines(g);
		drawBoard(g);
		playerStatus();
		checkScore();
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
	
	public void drawBoard(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
		SYMBOL_SIZE = (CANVAS_WIDTH / size)  - CELL_PADDING * 2;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				int x1 = col * CELL_SIZE + CELL_PADDING ;
				int y1 = row * CELL_SIZE + CELL_PADDING ;
				
				int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
				int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
				
				if(board.setMode(modeString, size) == 1) { //General Game
					if (generalGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
						if(playerKeyRed == 'S') {
							g2d.setColor(Color.RED);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);

							drawScoreLinesRedS(g2d, row, col, x1, y1, x2, y2);
						}
						else{
							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
//							drawScoreLinesRed(g2d, row, col, x1, y1, x2, y2);
						}
						
					} else if (generalGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player
						if(playerKeyBlue == 'S') {
							g2d.setColor(Color.BLUE);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
							drawScoreLinesBlueS(g2d, row, col, x1, y1, x2, y2);
						}
						else {
							g2d.setColor(Color.BLUE);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
//							drawScoreLinesBlueO(g2d, row, col, x1, y1, x2, y2);
						}
					}
//					drawScoreLinesRedS(g2d, row, col, x1, y1, x2, y2);
//					drawScoreLinesBlueO(g2d, row, col, x1, y1, x2, y2);
					
				}
				
				else if(board.setMode(modeString, size) == 2) { //Simple Game
					if (simpleGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
						if(playerKeyRed == 'S') {
							g2d.setColor(Color.RED);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
//							drawScoreLines(g2d, row, col, x1, y1, x2, y2);
						}
						else{
							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
//							drawScoreLines(g2d, row, col, x1, y1, x2, y2);
						}

					} else if (simpleGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player
						if(playerKeyBlue == 'S') {
							g2d.setColor(Color.BLUE);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
//							drawScoreLines(g2d, row, col, x1, y1, x2, y2);
						}
						else {
							g2d.setColor(Color.BLUE);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
//							drawScoreLines(g2d, row, col, x1, y1, x2, y2);
						}
					}
				}
//				drawScoreLinesRedS(g2d, row, col, x1, y1, x2, y2);
				
			}
		}
	}
	
	private void drawScoreLinesRedS(Graphics g,  int row, int col, int x1, int y1, int x2, int y2){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH / 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//		g2d.setColor(Color.BLACK);
		if(board.setMode(modeString, size) == 1) {
			if(playerKeyRed == 'S' && playerKeyBlue == 'O') {
				if(generalGame.rowRightSideRedS(row, col, size)) {
					g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.rowLeftSideRedS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.rightSideDiagDownRedS(row, col, size)) {
					g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
				}
				if(generalGame.rightSideDiagUpRedS(row, col, size)) {
					g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
				}
				if(generalGame.leftSideDiagDownRedS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
				}
				if(generalGame.leftSideDiagUpRedS(row, col, size)) {
					g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
				}
				if(generalGame.colDownRedS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
				}
			}
			
			
			
			
			
			//O CASES
//			if(generalGame.getScoredCell(row, col, size) == scoredCell.O_ROW) {
//				g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
//			}
//			if(generalGame.getScoredCell(row, col, size) == scoredCell.O_COL) {
//				g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - CELL_SIZE, x2 - SYMBOL_SIZE / 2, y2 + CELL_SIZE);
//			}
//			if(generalGame.getScoredCell(row, col, size) == scoredCell.O_DOWN_DIAG) {
//				g2d.drawLine(x1 - CELL_SIZE, y1 - CELL_SIZE, x2 + CELL_SIZE, y2 + CELL_SIZE);
//			}
//			if(generalGame.getScoredCell(row, col, size) == scoredCell.O_UP_DIAG) {
//				g2d.drawLine(x2 + CELL_SIZE, y1 - CELL_SIZE, x1 - CELL_SIZE, y2 + CELL_SIZE);
//			}

		}
		
		if(board.setMode(modeString, size) == 2) {
			if(simpleGame.rowRightSideRedS(row, col, size)) {
				g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
			}
			if(simpleGame.rowLeftSideRedS(row, col, size)) {
				g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
			}
			if(simpleGame.rightSideDiagDownRedS(row, col, size)) {
				g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
			}
			if(simpleGame.rightSideDiagUpRedS(row, col, size)) {
				g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
			}
			if(simpleGame.leftSideDiagDownRedS(row, col, size)) {
				g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
			}
			if(simpleGame.leftSideDiagUpRedS(row, col, size)) {
				g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
			}
			if(simpleGame.colDownRedS(row, col, size)) {
				g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
			}
			
			//O CASES
//			if(simpleGame.getScoredCell(row, col, size) == scoredCell.O_ROW) {
//				g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
//			}
//			if(simpleGame.getScoredCell(row, col, size) == scoredCell.O_COL) {
//				g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - CELL_SIZE, x2 - SYMBOL_SIZE / 2, y2 + CELL_SIZE);
//			}
//			if(simpleGame.getScoredCell(row, col, size) == scoredCell.O_DOWN_DIAG) {
//				g2d.drawLine(x1 - CELL_SIZE, y1 - CELL_SIZE, x2 + CELL_SIZE, y2 + CELL_SIZE);
//			}
//			if(simpleGame.getScoredCell(row, col, size) == scoredCell.O_UP_DIAG) {
//				g2d.drawLine(x2 + CELL_SIZE, y1 - CELL_SIZE, x1 - CELL_SIZE, y2 + CELL_SIZE);
//			}
		}
	}
	
	private void drawScoreLinesBlueS(Graphics g,  int row, int col, int x1, int y1, int x2, int y2){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		if(board.setMode(modeString, size) == 1) {
			if(playerKeyRed == 'O' && playerKeyBlue == 'S') {
				if(generalGame.rowRightSideBlueS(row, col, size)) {
					g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.rowLeftSideBlueS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.rightSideDiagDownBlueS(row, col, size)) {
					g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
				}
				if(generalGame.rightSideDiagUpBlueS(row, col, size)) {
					g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
				}
				if(generalGame.leftSideDiagDownBlueS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
				}
				if(generalGame.leftSideDiagUpBlueS(row, col, size)) {
					g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
				}
				if(generalGame.colDownBlueS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
				}
			}
		}
		if(board.setMode(modeString, size) == 2) {
			if(playerKeyRed == 'O' && playerKeyBlue == 'S') {
				if(generalGame.rowRightSideBlueS(row, col, size)) {
					g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.rowLeftSideBlueS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.rightSideDiagDownBlueS(row, col, size)) {
					g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
				}
				if(generalGame.rightSideDiagUpBlueS(row, col, size)) {
					g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
				}
				if(generalGame.leftSideDiagDownBlueS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
				}
				if(generalGame.leftSideDiagUpBlueS(row, col, size)) {
					g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
				}
				if(generalGame.colDownBlueS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
				}
			}
		}
	}
	
//	private void drawScoreLinesBlueO(Graphics g,  int row, int col, int x1, int y1, int x2, int y2){
//		Graphics2D g2d = (Graphics2D)g;
//		if(board.setMode(modeString, size) == 1) {
//			if(generalGame.rowBlueO(row, col, size)) {
//				g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
//			}
//		}
//		if(board.setMode(modeString, size) == 2) {
//			
//		}
//	}
	private void checkScore() {
		if(generalGame.getGameScore() == GameStateGeneral.RED_SCORES ) {
			redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
			gameStatusBar.setText("Red Scores! Red's Turn Again");
		}
		if(generalGame.getGameScore() == GameStateGeneral.BLUE_SCORES) {
			bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
			gameStatusBar.setText("Blue Scores! Blue's Turn Again");
		}
	}
	
	private void playerStatus(){
		if(board.setMode(modeString, size) == 1) {
			if (generalGame.getTurn() == 'R') {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Red's Turn");
			} 
			else{
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("Blue's Turn");
			}
			if (generalGame.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.BLACK);
				gameStatusBar.setText("Game is a Draw");
			}
			if (generalGame.getGameState() == GameState.RED_WINS) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Red Wins");
			}
			if (generalGame.getGameState() == GameState.BLUE_WINS) {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("Blue Wins");
			}

		}
		else if (board.setMode(modeString, size) == 2){
			if (simpleGame.getTurn() == 'R') {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Red's Turn");
			} 
			else {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("Blue's Turn");
			}
			if (simpleGame.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.BLACK);
				gameStatusBar.setText("Game is a Draw");
			}
			if (simpleGame.getGameState() == GameState.RED_WINS) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Red Wins");
			}
			if (simpleGame.getGameState() == GameState.BLUE_WINS) {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("Blue Wins");
			}
		}
		
		
	}

}
