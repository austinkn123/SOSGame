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
import javax.swing.JRadioButton;

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


	private Board board;
	private GeneralGameBoard generalGame = new GeneralGameBoard();
	private SimpleGameBoard simpleGame = new SimpleGameBoard();
	
	protected JLabel redPlayerPoints;
	protected JLabel bluePlayerPoints;
	
	private JPanel gameOptions = new JPanel();
	private JLabel gameStatusBar = new JLabel("Red Player, Make a Move!");;
	GridBagConstraints gbc = new GridBagConstraints();
	
	private int size = 8;
	private String modeString = "GENERAL";
	private char playerKeyRed = 'X';
	private char playerKeyBlue = 'X';
	private char cpuPlayerKeyRed = 'X';
	private char cpuPlayerKeyBlue = 'X';
	private boolean recordState = false;
	
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
		System.out.println(board.getModeString());
		playerKeyRed = board.getRedPlayerKey();
		playerKeyBlue = board.getBluePlayerKey();
		cpuPlayerKeyRed = board.getCpuRedPlayer();
		cpuPlayerKeyBlue = board.getCpuBluePlayer();
		
		
//		gameOptions.setLayout(new GridBagLayout());
		gameOptions.setLayout(new BorderLayout());
		GUI.contentPane.add(gameOptions, BorderLayout.SOUTH);
		
		gameStatusBar.setFont(new Font("Calibri", Font.BOLD, 25));
		gameOptions.add(gameStatusBar, BorderLayout.CENTER);
		
		//NEW GAME BUTTON
		JButton newGame = new JButton("  New Game  ");
		newGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gameOptions.add(newGame, BorderLayout.WEST);
		
		JRadioButton recordButton = new JRadioButton("Record");
		recordButton.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
		gameOptions.add(recordButton, BorderLayout.EAST);
		
		//SCREEN BOARD SIZE
		CANVAS_WIDTH = 500;  
		CANVAS_HEIGHT = 500;
		CELL_SIZE = CANVAS_WIDTH / pSize;
		
		//SETS THE POINTS LABELS
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
	
		//ACTION FOR ENTER BUTTON
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(modeString == "GENERAL") {
					recordState = false;
					generalGame.resetPointBlue();
					generalGame.resetPointRed();
					bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
					redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
					generalGame.setSizeBoard(pSize);
				}
				if(modeString == "SIMPLE") {
					recordState = false;
					simpleGame.setSizeBoard(pSize);
				}
				
				recordButton.setSelected(false);
				recordButton.setEnabled(true);
				
				System.out.println(cpuPlayerKeyRed);
//				if (cpuPlayerKeyRed == board.getTurn()) {
//					makeFirstAutoMove(generalGame, simpleGame, pSize, 
//							playerKeyRed, playerKeyBlue, modeString, cpuPlayerKeyRed, cpuPlayerKeyBlue);
//				}
				
			}
		});
		
		//ACTION FOR RECORD BUTTON
		recordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recordState = true;
				recordButton.setEnabled(false);
				
			}
		});
		
		makeFirstAutoMove(generalGame, simpleGame, pSize, 
				playerKeyRed, playerKeyBlue, modeString, cpuPlayerKeyRed, cpuPlayerKeyBlue);
		
		//MAKES A MOVE BASED ON CLICKS
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					int rowSelected = e.getY() / CELL_SIZE;
					int colSelected = e.getX() / CELL_SIZE;
					makeMoveonBoard(generalGame, simpleGame, rowSelected, colSelected, pSize, 
							playerKeyRed, playerKeyBlue, modeString, cpuPlayerKeyRed, cpuPlayerKeyBlue);
			}
		});
	
	}
	
	//Making the first move if the red player is a computer
	public void makeFirstAutoMove(GeneralGameBoard generalMode, SimpleGameBoard simpleMode, int pSize, 
			char redPlayer, char bluePlayer, String pModString, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) { 
		if (cpuPlayerKeyRed != 'X') {
			System.out.println(cpuPlayerKeyRed);
			if(modeString == "GENERAL") {
				generalGame.makeFirstMove(pSize, playerKeyRed, playerKeyBlue, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordState);
			}
			if(modeString == "SIMPLE") {
				simpleGame.makeFirstMove(pSize, playerKeyRed, playerKeyBlue, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordState);
			}
		}
	}
	
	//Making a move on the board
	public void makeMoveonBoard(GeneralGameBoard generalMode, SimpleGameBoard simpleMode, int row, int col, int pSize, 
			char redPlayer, char bluePlayer, String pModString, char cpuPlayerKeyRed, char cpuPlayerKeyBlue) { 
		if(pModString == "GENERAL") {
			if(generalMode.getGameState() == GameState.PLAYING) {
				generalMode.makeMoveInGeneralMode(row, col, pSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordState);
				redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
				bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
				
			}
		}
		if(pModString == "SIMPLE") {
			if (simpleMode.getGameState() == GameState.PLAYING) {
				simpleMode.makeMoveInSimpleMode(row, col, pSize, redPlayer, bluePlayer, cpuPlayerKeyRed, cpuPlayerKeyBlue, recordState);
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
						if(playerKeyRed == 'S') { //S SYMBOL
							g2d.setColor(Color.RED);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
							drawScoreLinesRed(g2d, row, col, x1, y1, x2, y2);
						}
						else{ //O SYMBOL
							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							drawScoreLinesRed(g2d, row, col, x1, y1, x2, y2);
						}
					} else if (generalGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player
						if(playerKeyBlue == 'S') { //S SYMBOL
							g2d.setColor(Color.BLUE);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
							drawScoreLinesBlue(g2d, row, col, x1, y1, x2, y2);
						}
						else { //O SYMBOL
							g2d.setColor(Color.BLUE);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							drawScoreLinesBlue(g2d, row, col, x1, y1, x2, y2);
						}
					}
					
				}
				
				else if(board.setMode(modeString, size) == 2) { //Simple Game
					if (simpleGame.getCell(row,col, size) == Cell.RED_PLAYER) { //Red Player
						if(playerKeyRed == 'S') { //S SYMBOL
							g2d.setColor(Color.RED);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
							drawScoreLinesRed(g2d, row, col, x1, y1, x2, y2);
						}
						else{ //O SYMBOL
							g2d.setColor(Color.RED);
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							drawScoreLinesRed(g2d, row, col, x1, y1, x2, y2);
						}

					} else if (simpleGame.getCell(row,col, size) == Cell.BLUE_PLAYER) { //Blue Player
						if(playerKeyBlue == 'S') { //S SYMBOL
							g2d.setColor(Color.BLUE);
							g2d.setFont(new Font("TimesRoman", Font.PLAIN, CANVAS_WIDTH / size)); 
							g2d.drawString("S", x1, y1+ (CELL_SIZE -  CELL_PADDING * 2) + 5);
							drawScoreLinesBlue(g2d, row, col, x1, y1, x2, y2);
						}
						else {
							g2d.setColor(Color.BLUE); //O SYMBOL
							g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
							drawScoreLinesBlue(g2d, row, col, x1, y1, x2, y2);
						}
					}
				}
				
			}
		}
	}
	
	//DRAWING LINES FOR RED PLAYER
	private void drawScoreLinesRed(Graphics g,  int row, int col, int x1, int y1, int x2, int y2){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
//		GENERAL GAME
		if(board.setMode(modeString, size) == 1) {
//			S CASES
			if(generalGame.getScoredCell(row, col, size) == scoredCell.RED_S_SCORED) {
				if(generalGame.rowRightSideRedS(row, col, size) ) {
					g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
//					return;
				}
				if(generalGame.rowLeftSideRedS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
//					return;
				}
				if(generalGame.rightSideDiagDownRedS(row, col, size)) {
					g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
//					return;
				}
				if(generalGame.rightSideDiagUpRedS(row, col, size)) {
					g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
//					return;
				}
				if(generalGame.leftSideDiagDownRedS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
//					return;
				}
				if(generalGame.leftSideDiagUpRedS(row, col, size)) {
					g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
//					return;
				}
				if(generalGame.colDownRedS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
//					return;
				}
				if(generalGame.colUpRedS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - (CELL_SIZE * 2) , x2 - SYMBOL_SIZE / 2, y2 );
//					return;
				}
			}
//			O CASES
			if(generalGame.getScoredCell(row, col, size) == scoredCell.RED_O_SCORED) {
				if(generalGame.rowRedO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
//					return;
				}
				if(generalGame.colRedO(row, col, size) ) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - CELL_SIZE, x2 - SYMBOL_SIZE / 2, y2 + CELL_SIZE);
//					return;
				}
				if(generalGame.diagDownRedO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE, y1 - CELL_SIZE, x2 + CELL_SIZE, y2 + CELL_SIZE);
//					return;
				}
				if(generalGame.diagUpRedO(row, col, size) ) {
					g2d.drawLine(x2 + CELL_SIZE, y1 - CELL_SIZE, x1 - CELL_SIZE, y2 + CELL_SIZE);
//					return;
				}
			}
		}
		//SIMPLE GAME
		if(board.setMode(modeString, size) == 2) {
//			S CASES
			if(simpleGame.getScoredCell(row, col, size) == scoredCell.RED_S_SCORED) {
				if(simpleGame.rowRightSideRedS(row, col, size)) {
					g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
//					return;
				}
				if(simpleGame.rowLeftSideRedS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
//					return;
				}
				if(simpleGame.rightSideDiagDownRedS(row, col, size)) {
					g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
//					return;
				}
				if(simpleGame.rightSideDiagUpRedS(row, col, size)) {
					g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
//					return;
				}
				if(simpleGame.leftSideDiagDownRedS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
//					return;
				}
				if(simpleGame.leftSideDiagUpRedS(row, col, size)) {
					g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
//					return;
				}
				if(simpleGame.colDownRedS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
//					return;
				}
				if(simpleGame.colUpRedS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - (CELL_SIZE * 2) , x2 - SYMBOL_SIZE / 2, y2 );
//					return;
				}
			}
//			O CASES
			if(simpleGame.getScoredCell(row, col, size) == scoredCell.RED_O_SCORED) {
				if(simpleGame.rowRedO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
//					return;
				}
				if(simpleGame.colRedO(row, col, size) ) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - CELL_SIZE, x2 - SYMBOL_SIZE / 2, y2 + CELL_SIZE);
//					return;
				}
				if(simpleGame.diagDownRedO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE, y1 - CELL_SIZE, x2 + CELL_SIZE, y2 + CELL_SIZE);
//					return;
				}
				if(simpleGame.diagUpRedO(row, col, size) ) {
					g2d.drawLine(x2 + CELL_SIZE, y1 - CELL_SIZE, x1 - CELL_SIZE, y2 + CELL_SIZE);
//					return;
				}
			}
		}
	}
	
	//DRAWING LINES FOR BLUE PLAYER
	private void drawScoreLinesBlue(Graphics g,  int row, int col, int x1, int y1, int x2, int y2){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
//		GENERAL GAME
		if(board.setMode(modeString, size) == 1) {
//			S CASES
			if(generalGame.getScoredCell(row, col, size) == scoredCell.BLUE_S_SCORED) {
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
				if(generalGame.colUpBlueS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - (CELL_SIZE * 2) , x2 - SYMBOL_SIZE / 2, y2 );
				}
			}
//			O CASES
			if(generalGame.getScoredCell(row, col, size) == scoredCell.BLUE_O_SCORED) {
				if(generalGame.rowBlueO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
				}
				if(generalGame.colBlueO(row, col, size) ) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - CELL_SIZE, x2 - SYMBOL_SIZE / 2, y2 + CELL_SIZE);
				}
				if(generalGame.diagDownBlueO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE, y1 - CELL_SIZE, x2 + CELL_SIZE, y2 + CELL_SIZE);
				}
				if(generalGame.diagUpBlueO(row, col, size) ) {
					g2d.drawLine(x2 + CELL_SIZE, y1 - CELL_SIZE, x1 - CELL_SIZE, y2 + CELL_SIZE);
				}
			}
		}
//		SIMPLE GAME
		if(board.setMode(modeString, size) == 2) {
//			S CASES
			if(simpleGame.getScoredCell(row, col, size) == scoredCell.BLUE_S_SCORED) {
				if(simpleGame.rowRightSideBlueS(row, col, size)) {
					g2d.drawLine(x1, y1 + SYMBOL_SIZE / 2, x2 + (CELL_SIZE * 2), y2 - SYMBOL_SIZE / 2);
				}
				if(simpleGame.rowLeftSideBlueS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 + SYMBOL_SIZE / 2 , x2, y2 - SYMBOL_SIZE / 2);
				}
				if(simpleGame.rightSideDiagDownBlueS(row, col, size)) {
					g2d.drawLine(x2 + (CELL_SIZE * 2), y2 + (CELL_SIZE * 2), x1, y1);
				}
				if(simpleGame.rightSideDiagUpBlueS(row, col, size)) {
					g2d.drawLine(x1, y2, x2 + (CELL_SIZE * 2), y1 - (CELL_SIZE * 2));
				}
				if(simpleGame.leftSideDiagDownBlueS(row, col, size)) {
					g2d.drawLine(x1 - (CELL_SIZE * 2), y1 - (CELL_SIZE * 2), x2, y2);
				}
				if(simpleGame.leftSideDiagUpBlueS(row, col, size)) {
					g2d.drawLine(x2, y1, x1 - (CELL_SIZE * 2), y2 + (CELL_SIZE * 2));
				}
				if(simpleGame.colDownBlueS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1, x2 - SYMBOL_SIZE / 2, y2 + (CELL_SIZE * 2));
				}
				if(simpleGame.colUpBlueS(row, col, size)) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - (CELL_SIZE * 2) , x2 - SYMBOL_SIZE / 2, y2 );
				}
			}
//			O CASES
			if(simpleGame.getScoredCell(row, col, size) == scoredCell.BLUE_O_SCORED) {
				if(simpleGame.rowBlueO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE , y1 + SYMBOL_SIZE / 2, x2 + CELL_SIZE, y2 - SYMBOL_SIZE / 2);
				}
				if(simpleGame.colBlueO(row, col, size) ) {
					g2d.drawLine(x1 + SYMBOL_SIZE / 2, y1 - CELL_SIZE, x2 - SYMBOL_SIZE / 2, y2 + CELL_SIZE);
				}
				if(simpleGame.diagDownBlueO(row, col, size) ) {
					g2d.drawLine(x1 - CELL_SIZE, y1 - CELL_SIZE, x2 + CELL_SIZE, y2 + CELL_SIZE);
				}
				if(simpleGame.diagUpBlueO(row, col, size) ) {
					g2d.drawLine(x2 + CELL_SIZE, y1 - CELL_SIZE, x1 - CELL_SIZE, y2 + CELL_SIZE);
				}
			}
		}
	}
	
	
	//CHANGES POINTS AND GAMESTATUSBAR BASED ON SCORING
	private void checkScore() {
		if(generalGame.getGameScore() == GameStateGeneral.RED_SCORES ) {
			gameStatusBar.setText("            Red Scores! Red's Turn Again");
		}
		if(generalGame.getGameScore() == GameStateGeneral.BLUE_SCORES) {
			gameStatusBar.setText("            Blue Scores! Blue's Turn Again");
		}
		
	}
	
	//CHANGES GAMESTATUSBAR BASED ON LOGIC
	private void playerStatus(){
//		GENERAL GAME
		if(board.setMode(modeString, size) == 1) {
			checkScore();
			if (generalGame.getTurn() == 'R') {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("            Red's Turn");
			} 
			else{
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("            Blue's Turn");
			}
			if (generalGame.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.BLACK);
				gameStatusBar.setText("            Game is a Draw");
				redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
				bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
			}
			if (generalGame.getGameState() == GameState.RED_WINS) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("            Red Wins");
				redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
				bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
			}
			if (generalGame.getGameState() == GameState.BLUE_WINS) {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("            Blue Wins");
				redPlayerPoints.setText(String.valueOf(generalGame.getPointRed()));
				bluePlayerPoints.setText(String.valueOf(generalGame.getPointBlue()));
			}
		}
//		SIMPLE GAME
		else if (board.setMode(modeString, size) == 2){
			if (simpleGame.getTurn() == 'R') {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("            Red's Turn");
			} 
			else {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("            Blue's Turn");
			}
			if (simpleGame.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.BLACK);
				gameStatusBar.setText("            Game is a Draw");
			}
			if (simpleGame.getGameState() == GameState.RED_WINS) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("            Red Wins");
			}
			if (simpleGame.getGameState() == GameState.BLUE_WINS) {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("            Blue Wins");
			}
		}
		
	}

}
